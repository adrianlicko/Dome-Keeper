package sk.uniza.fri.game.weapons;

import sk.uniza.fri.ImageObject;
import sk.uniza.fri.game.action.player.ActionAttackEnemy;
import sk.uniza.fri.game.weapons.player.WeaponType;

import java.util.ArrayList;
import java.util.List;

public abstract class Weapon {
    private ImageObject weaponImage;
    private WeaponType weaponType;
    private int damage;
    private boolean isEquipped;
    private boolean isPurchased;
    private int weaponAngle;
    private boolean isReversed;
    private List<DirectProjectile> projectiles;

    public Weapon(WeaponType weaponType, int damage) {
        this.weaponImage = new ImageObject(weaponType.getImagePath());
        this.weaponType = weaponType;
        this.isEquipped = false;
        this.isPurchased = false;
        this.damage = damage;
        this.projectiles = new ArrayList<>();
    }

    public abstract void shoot();

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
        ArrayList<DirectProjectile> bulletsToRemove = new ArrayList<>();
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

    protected void addBullet(DirectProjectile directProjectile) {
        this.projectiles.add(directProjectile);
    }

    public void removeBullet(DirectProjectile directProjectile) {
        directProjectile.getProjectileImage().makeInvisible();
        this.projectiles.remove(directProjectile);
    }

    public boolean isEquipped() {
        return this.isEquipped;
    }

    public void setEquipped(boolean equipped) {
        this.isEquipped = equipped;
    }

    public boolean isPurchased() {
        return this.isPurchased;
    }

    public void setPurchased(boolean purchased) {
        this.isPurchased = purchased;
    }

    public ImageObject getImage() {
        return this.weaponImage;
    }
}
