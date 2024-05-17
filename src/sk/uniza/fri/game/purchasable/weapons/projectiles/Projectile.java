package sk.uniza.fri.game.purchasable.weapons.projectiles;

import sk.uniza.fri.ImageLoader;
import sk.uniza.fri.ImageObject;

public abstract class Projectile {
    private ImageLoader imageLoader;
    private ImageObject projectileImage;
    private int damage;

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