package sk.uniza.fri.enemy.ranged;

import sk.uniza.fri.ImageLoader;
import sk.uniza.fri.ImageObject;

public class EnemyProjectile {
    private ImageObject bulletImage;
    private ImageLoader imageLoader;
    private String side;

    public EnemyProjectile(int x, int y, String side) {
        this.imageLoader = new ImageLoader("assets/enemies/worm/bullet");
        this.bulletImage = new ImageObject(this.imageLoader.getNextImage(), x, y, 50, 50);
        this.side = side;
    }

    public void move() {
        if (this.side.equals("/right")) {
            this.bulletImage.addX(2);
        } else {
            this.bulletImage.addX(-2);
        }
        this.bulletImage.changeImage(this.imageLoader.getNextImage());
        this.bulletImage.changePosition(this.bulletImage.getX(), this.bulletImage.getY());
    }

    public ImageObject getBulletImage() {
        return this.bulletImage;
    }
}
