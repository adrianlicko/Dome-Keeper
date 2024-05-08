package sk.uniza.fri.game.weapons;

import sk.uniza.fri.ImageObject;

public class HomingProjectile extends Projectile {
    private ImageObject target;

    public HomingProjectile(int x, int y, int damage, String directoryPath, ImageObject target) {
        super(x, y, damage, directoryPath);
        this.target = target;
    }

    @Override
    public void move() {
        // Calculate the direction vector from the projectile to the Dome
        double dirX = this.target.getHitX() - this.getProjectileImage().getHitX();
        double dirY = this.target.getHitY() - this.getProjectileImage().getHitY();

        // Normalize the direction vector
        double length = Math.sqrt(dirX * dirX + dirY * dirY);
        dirX /= length;
        dirY /= length;

        // Move the projectile in the direction of the Dome
        this.getProjectileImage().addX((int)(dirX * 2));
        this.getProjectileImage().addY((int)(dirY * 2));

        // Update the image and position of the projectile
        this.getProjectileImage().changeImage(this.getImageLoader().getNextImage());
        this.getProjectileImage().changePosition(this.getProjectileImage().getX(), this.getProjectileImage().getY());
    }
}