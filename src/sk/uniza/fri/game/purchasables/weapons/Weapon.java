package sk.uniza.fri.game.purchasables.weapons;

import sk.uniza.fri.game.ImageObject;
import sk.uniza.fri.game.actions.player.ActionAttackEnemy;
import sk.uniza.fri.game.purchasables.PurchasableItem;
import sk.uniza.fri.game.purchasables.weapons.projectiles.Projectile;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a weapon that can be used by the player.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public abstract class Weapon implements PurchasableItem {
    private final ImageObject weaponImage;
    private final WeaponType weaponType;
    private final int damage;
    private int weaponAngle;
    private boolean isReversed;
    private final List<Projectile> projectiles;
    private final Timer timer;
    private boolean isShooting;
    private final int fireRate;
    private int previousSliderX;
    private boolean isEquipped;
    private boolean isPurchased;

    /**
     * Constructor for the Weapon class.
     *
     * @param weaponType - WeaponType object representing the type of the weapon.
     * @param damage - Integer value representing the damage that the weapon deals.
     * @param fireRate - Integer value representing the fire rate of the weapon.
     */
    public Weapon(WeaponType weaponType, int damage, int fireRate) {
        this.weaponImage = new ImageObject(weaponType.getImagePath());
        this.weaponType = weaponType;
        this.damage = damage;
        this.projectiles = new ArrayList<>();
        this.timer = new Timer();
        this.isShooting = false;
        this.fireRate = fireRate;
        this.previousSliderX = 0;
        this.isEquipped = false;
        this.isPurchased = false;
    }

    public abstract void fire();

    /**
     * Shoots the weapon with the speed of the fire rate.
     */
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

    /**
     * Moves the bullets that are currently on the screen.
     */
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

    /**
     * Removes all the bullets from the screen.
     */
    public void removeBullets() {
        ArrayList<Projectile> bulletsToRemove = new ArrayList<>();
        for (var projectile : this.projectiles) {
            projectile.getProjectileImage().makeInvisible();
            bulletsToRemove.add(projectile);
        }
        this.projectiles.removeAll(bulletsToRemove);
    }

    /**
     * Changes the angle of the weapon.
     *
     * @param posOrNegNum - Integer value representing the direction of the change.
     * @param weaponSliderX - Integer value representing the x coordinate of the weapon slider.
     */
    public void changeAngle(int posOrNegNum, int weaponSliderX) {
        int sliderX = -weaponSliderX * 2;
        this.weaponAngle += posOrNegNum * 2;

        if (sliderX == -70) {
            if (this.previousSliderX > sliderX) {
                if (!this.isReversed) {
                    this.weaponAngle += 140;
                    this.isReversed = true;
                    this.weaponImage.changeImage(this.weaponType.getReverseImagePath());
                }
            } else if (this.previousSliderX < sliderX) {
                if (this.isReversed) {
                    this.weaponAngle -= 140;
                    this.isReversed = false;
                    this.weaponImage.changeImage(this.weaponType.getImagePath());
                }
            }
        }
        this.weaponImage.changeAngle(this.weaponAngle);
        this.previousSliderX = sliderX;
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
}
