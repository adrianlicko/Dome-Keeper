package sk.uniza.fri.game.purchasable.weapons;

import sk.uniza.fri.ImageObject;
import sk.uniza.fri.game.action.player.ActionAttackEnemy;
import sk.uniza.fri.game.purchasable.Item;
import sk.uniza.fri.game.purchasable.weapons.projectiles.Projectile;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Weapon extends Item {
    private ImageObject weaponImage;
    private WeaponType weaponType;
    private int damage;
    private int weaponAngle;
    private boolean isReversed;
    private List<Projectile> projectiles;
    private final Timer timer;
    private boolean isShooting;
    private int fireRate;

    public Weapon(WeaponType weaponType, int damage, int fireRate) {
        this.weaponImage = new ImageObject(weaponType.getImagePath());
        this.weaponType = weaponType;
        this.damage = damage;
        this.projectiles = new ArrayList<>();
        this.timer = new Timer();
        this.isShooting = false;
        this.fireRate = fireRate;
    }

    public abstract void fire();

    public void shoot() {
        if (!this.isShooting) {
            this.isShooting = true;
            System.out.println("Shooting");

            this.timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Weapon.this.isShooting = false;
                }
            }, this.fireRate * 100L);

            this.fire();
        }
    }

    public void moveBullets() {
        for (int i = 0; i < this.projectiles.size(); i++) {
            this.projectiles.get(i).move();
            if (this.projectiles.get(i).getProjectileImage().getX() < -50 || this.projectiles.get(i).getProjectileImage().getX() > 1024 || this.projectiles.get(i).getProjectileImage().getY() < 0) {
                this.removeBullet(this.projectiles.get(i));
                continue;
            }
            ActionAttackEnemy.shoot(this, this.projectiles.get(i));
        }
    }

    public void removeBullets() {
        ArrayList<Projectile> bulletsToRemove = new ArrayList<>();
        for (var projectile : this.projectiles) {
            projectile.getProjectileImage().makeInvisible();
            bulletsToRemove.add(projectile);
        }
        this.projectiles.removeAll(bulletsToRemove);
    }

    public void changeAngle(int posOrNegNum, int weaponSliderX) {
        int sliderX = -weaponSliderX * 2;
        this.weaponAngle += posOrNegNum * 2;

        if (sliderX == -70) {
            if (!this.isReversed) {
                this.weaponAngle += 140;
                this.isReversed = true;
                this.weaponImage.changeImage(this.weaponType.getReverseImagePath());
            } else {
                this.weaponAngle -= 140;
                this.isReversed = false;
                this.weaponImage.changeImage(this.weaponType.getImagePath());
            }
        }
        this.weaponImage.changeAngle(this.weaponAngle);
    }

    public int getAngle() {
        return this.weaponAngle;
    }

    public boolean isReversed() {
        return this.isReversed;
    }

    public int getDamage() {
        return this.damage;
    }

    protected void addBullet(Projectile projectile) {
        this.projectiles.add(projectile);
    }

    public void removeBullet(Projectile projectile) {
        projectile.getProjectileImage().makeInvisible();
        this.projectiles.remove(projectile);
    }

    public ImageObject getImage() {
        return this.weaponImage;
    }
}