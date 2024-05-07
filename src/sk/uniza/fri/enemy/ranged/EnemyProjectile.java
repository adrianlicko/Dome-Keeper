package sk.uniza.fri.enemy.ranged;

import sk.uniza.fri.ImageLoader;
import sk.uniza.fri.ImageObject;
import sk.uniza.fri.player.Dome;

public class EnemyProjectile {
    private ImageObject bulletImage;
    private ImageLoader imageLoader;

    public EnemyProjectile(int x, int y, String directoryPath) {
        this.imageLoader = new ImageLoader(directoryPath);
        this.bulletImage = new ImageObject(this.imageLoader.getNextImage(), x, y, 50, 50);
    }

    public void move() {
        // Get the position of the Dome
        double domeX = Dome.getInstance().getDomeImage().getHitX();
        double domeY = Dome.getInstance().getDomeImage().getHitY();

        // Calculate the direction vector from the projectile to the Dome
        double dirX = domeX - this.bulletImage.getHitX();
        double dirY = domeY - this.bulletImage.getHitY();

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
