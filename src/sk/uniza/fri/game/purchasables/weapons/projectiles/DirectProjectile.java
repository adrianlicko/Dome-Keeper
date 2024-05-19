package sk.uniza.fri.game.purchasables.weapons.projectiles;

import sk.uniza.fri.game.purchasables.weapons.Weapon;

/**
 * Represents a direct projectile that moves in a straight line.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class DirectProjectile extends Projectile {
    private Weapon weapon;
    private int speed;
    private double angle;

    /**
     * Constructor for the DirectProjectile class.
     *
     * @param x - Integer value representing the x coordinate of the projectile.
     * @param y - Integer value representing the y coordinate of the projectile.
     * @param damage - Integer value representing the damage of the projectile.
     * @param directoryPath - String value representing the directory path of the projectile images.
     * @param projectileWidth - Integer value representing the width of the projectile image.
     * @param projectileHeight - Integer value representing the height of the projectile image.
     * @param weapon - Weapon object representing the weapon that the projectile is shot from. Needed for the angle that the projectile will move in.
     * @param speed - Integer value representing the speed of the projectile.
     */
    public DirectProjectile(int x, int y, int damage, String directoryPath, int projectileWidth, int projectileHeight, Weapon weapon, int speed) {
        super(x, y, damage, directoryPath, projectileWidth, projectileHeight);
        this.weapon = weapon;
        this.speed = speed;
        if (this.weapon.isReversed()) {
            this.angle = Math.toRadians(180) + Math.toRadians(this.weapon.getAngle());
        } else {
            this.angle = Math.toRadians(this.weapon.getAngle());
        }
    }

    /**
     * Moves the projectile in the direction of the angle.
     */
    public void move() {
        this.getProjectileImage().addX((int)(this.speed * Math.cos(this.angle)));
        this.getProjectileImage().addY((int)(this.speed * Math.sin(this.angle)));
        this.getProjectileImage().changePosition(this.getProjectileImage().getX(), this.getProjectileImage().getY());
    }
}

