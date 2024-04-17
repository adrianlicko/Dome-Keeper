package sk.uniza.fri.weapons;

import fri.shapesge.Image;
import sk.uniza.fri.weapons.player.WeaponType;

public abstract class Weapon {
    private Image weaponImage;
    private int damage;
    private int weaponAngle;
    private boolean isReversed;
    private int x;
    private int y;

    public Weapon(WeaponType weaponType, int damage, int x, int y) {
        this.weaponImage = new Image(weaponType.getImagePath(), x, y);
        this.weaponImage.makeVisible();
        this.damage = damage;
        this.x = x;
        this.y = y;
    }

    public abstract void shoot();

    public int getDamage() {
        return this.damage;
    }

    public void changePosition(int x, int y) {
        this.weaponImage.changePosition(x, y);
        this.x = x;
        this.y = y;
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

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
