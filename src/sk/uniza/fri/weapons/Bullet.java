package sk.uniza.fri.weapons;

import fri.shapesge.Image;

public class Bullet {
    private Image bulletImage;
    private Weapon weapon;
    private double x;
    private double y;
    private int speed;
    private double angle;

    public Bullet(Weapon weapon, int x, int y, int speed) {
        this.bulletImage = new Image("assets/weapons/Pistol bullet small.png", x, y);
        this.bulletImage.makeVisible();
        this.weapon = weapon;
        this.x = x;
        this.y = y;
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
        this.x += this.speed * Math.cos(this.angle);
        this.y += this.speed * Math.sin(this.angle);
        this.bulletImage.changePosition((int)this.x, (int)this.y);
    }

    public int getDamage() {
        return this.weapon.getDamage();
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void makeInvisible() {
        this.bulletImage.makeInvisible();
    }
}