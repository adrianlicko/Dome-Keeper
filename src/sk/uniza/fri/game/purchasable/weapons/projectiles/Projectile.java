package sk.uniza.fri.game.purchasable.weapons.projectiles;

import sk.uniza.fri.ImageLoader;
import sk.uniza.fri.ImageObject;

/**
 * Represents a projectile that is shot from a weapon or by an enemy.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public abstract class Projectile {
    private ImageLoader imageLoader;
    private ImageObject projectileImage;
    private int damage;

    /**
     * Constructor for the Projectile class.
     *
     * @param x - Integer value representing the x coordinate of the projectile.
     * @param y - Integer value representing the y coordinate of the projectile.
     * @param damage - Integer value representing the damage of the projectile.
     * @param directoryPath - String value representing the directory path of the projectile images.
     * @param projectileWidth - Integer value representing the width of the projectile image.
     * @param projectileHeight - Integer value representing the height of the projectile image.
     */
    public Projectile(int x, int y, int damage, String directoryPath, int projectileWidth, int projectileHeight) {
        this.imageLoader = new ImageLoader(directoryPath);
        this.projectileImage = new ImageObject(this.imageLoader.getNextImage(), x + 10, y + 10, projectileWidth, projectileHeight);
        this.projectileImage.makeVisible();
        this.damage = damage;
    }

    public abstract void move();

    public int getDamage() {
        return this.damage;
    }

    public ImageLoader getImageLoader() {
        return this.imageLoader;
    }

    public ImageObject getProjectileImage() {
        return this.projectileImage;
    }
}