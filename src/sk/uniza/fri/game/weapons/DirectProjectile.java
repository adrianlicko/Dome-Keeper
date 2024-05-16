package sk.uniza.fri.game.weapons;

import sk.uniza.fri.ImageObject;
import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.enemy.Enemy;

public class DirectProjectile extends Projectile {
    private Weapon weapon;
    private int speed;
    private double angle;

    public DirectProjectile(int x, int y, int damage, String directoryPath, Weapon weapon, int speed) {
        super(x, y, damage, directoryPath);
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
        this.getProjectileImage().addX((int)(this.speed * Math.cos(this.angle)));
        this.getProjectileImage().addY((int)(this.speed * Math.sin(this.angle)));
        this.getProjectileImage().changePosition(this.getProjectileImage().getX(), this.getProjectileImage().getY());
    }
}

