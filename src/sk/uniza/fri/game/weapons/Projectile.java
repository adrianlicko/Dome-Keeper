package sk.uniza.fri.game.weapons;

import sk.uniza.fri.ImageLoader;
import sk.uniza.fri.ImageObject;

public abstract class Projectile {
    private ImageLoader imageLoader;
    private ImageObject projectileImage;
    private int damage;

    public Projectile(int x, int y, int damage, String directoryPath) {
        this.imageLoader = new ImageLoader(directoryPath);
        this.projectileImage = new ImageObject(this.imageLoader.getNextImage(), x, y, 50, 50);
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