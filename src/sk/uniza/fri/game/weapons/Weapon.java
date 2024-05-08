package sk.uniza.fri.game.weapons;

import sk.uniza.fri.ImageObject;
import sk.uniza.fri.game.action.player.ActionAttackEnemy;
import sk.uniza.fri.game.weapons.player.WeaponType;

import java.util.ArrayList;
import java.util.List;

public abstract class Weapon {
    private ImageObject weaponImage;
    private int damage;
    private int weaponAngle;
    private boolean isReversed;
    private List<DirectProjectile> directProjectiles;

    public Weapon(WeaponType weaponType, int damage, int x, int y) {
        this.weaponImage = new ImageObject(weaponType.getImagePath(), x, y);
        this.weaponImage.makeVisible();
        this.damage = damage;
        this.directProjectiles = new ArrayList<>();
    }

    public abstract void shoot();

    public void moveBullets() {
        for (int i = 0; i < this.directProjectiles.size(); i++) {
            this.directProjectiles.get(i).move();
            if (this.directProjectiles.get(i).getProjectileImage().getX() < -50 || this.directProjectiles.get(i).getProjectileImage().getX() > 1024 || this.directProjectiles.get(i).getProjectileImage().getY() < 0) {
                this.removeBullet(this.directProjectiles.get(i));
                continue;
            }
            ActionAttackEnemy.shoot(this, this.directProjectiles.get(i));
        }
    }

    public void changeAngle(int posOrNegNum, int weaponSliderX) {
        int sliderX = -weaponSliderX * 2;
        this.weaponAngle += posOrNegNum * 2;

        if (sliderX == -70) {
            if (!this.isReversed) {
                this.weaponAngle += 140;
                this.isReversed = true;
                this.weaponImage.changeImage(WeaponType.SHOTGUN.getReverseImagePath());
            } else {
                this.weaponAngle -= 140;
                this.isReversed = false;
                this.weaponImage.changeImage(WeaponType.SHOTGUN.getImagePath());
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
        this.directProjectiles.add(directProjectile);
    }

    public void removeBullet(DirectProjectile directProjectile) {
        directProjectile.getProjectileImage().makeInvisible();
        this.directProjectiles.remove(directProjectile);
    }

    public ImageObject getWeaponImage() {
        return this.weaponImage;
    }
}
