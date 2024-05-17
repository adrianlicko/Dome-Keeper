package sk.uniza.fri.game.enemy.ranged;

import sk.uniza.fri.ImageLoader;
import sk.uniza.fri.ImageObject;

public class EnemyProjectile {
    private ImageObject bulletImage;
    private ImageLoader imageLoader;
    private ImageObject target;

    public EnemyProjectile(int x, int y, ImageObject target, String directoryPath) {
        this.imageLoader = new ImageLoader(directoryPath);
        this.bulletImage = new ImageObject(this.imageLoader.getNextImage(), x, y, 50, 50);
        this.target = target;
    }

    public void move() {
        // Get the position of the Dome
        double targetX = this.target.getHitX();
        double targetY = this.target.getHitY();

        // Calculate the direction vector from the projectile to the Dome
        double dirX = targetX - this.bulletImage.getHitX();
        double dirY = targetY - this.bulletImage.getHitY();

        // Normalize the direction vector
        double length = Math.sqrt(dirX * dirX + dirY * dirY);
        dirX /= length;
        dirY /= length;

        // Move the projectile in the direction of the Dome
        this.bulletImage.addX((int)(dirX * 2));
        this.bulletImage.addY((int)(dirY * 2));

        // Update the image and position of the projectile
        this.bulletImage.changeImage(this.imageLoader.getNextImage());
        this.bulletImage.changePosition(this.bulletImage.getX(), this.bulletImage.getY());
    }

    public ImageObject getBulletImage() {
        return this.bulletImage;
    }
}
