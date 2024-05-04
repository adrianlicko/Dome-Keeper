package sk.uniza.fri.weapons;

import fri.shapesge.Image;
import sk.uniza.fri.ImageObject;

public class Bullet {
    private ImageObject bulletImage;
    private Weapon weapon;
    private int speed;
    private double angle;

    public Bullet(Weapon weapon, int x, int y, int speed) {
        this.bulletImage = new ImageObject("assets/weapons/Pistol bullet small.png", x, y);
        this.bulletImage.makeVisible();
        this.weapon = weapon;
        this.speed = speed;
        if (this.weapon.isReversed()) {
            this.angle = Math.toRadians(180) + Math.toRadians(this.weapon.getAngle());
        } else {
            this.angle = Math.toRadians(this.weapon.getAngle());
        }
    }

    /**
     * Moves the bullet in the direction of the angle.
     */
    public void move() {
        this.bulletImage.addX((int)(this.speed * Math.cos(this.angle)));
        this.bulletImage.addY((int)(this.speed * Math.sin(this.angle)));
        this.bulletImage.changePosition(this.bulletImage.getX(), this.bulletImage.getY());
    }

    public int getDamage() {
        return this.weapon.getDamage();
    }

    public ImageObject getBulletImage() {
        return this.bulletImage;
    }
}