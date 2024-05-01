package sk.uniza.fri.weapons;

import fri.shapesge.Image;
import sk.uniza.fri.ImageObject;

public class Bullet extends ImageObject {
    private Weapon weapon;
    private int speed;
    private double angle;

    public Bullet(Weapon weapon, int x, int y, int speed) {
        super("assets/weapons/Pistol bullet small.png", x, y);
        super.makeVisible();
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
        super.addX((int)(this.speed * Math.cos(this.angle)));
        super.addY((int)(this.speed * Math.sin(this.angle)));
        super.changePosition(super.getX(), super.getY());
    }

    public int getDamage() {
        return this.weapon.getDamage();
    }
}