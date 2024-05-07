package sk.uniza.fri.enemy;

import sk.uniza.fri.ImageLoader;
import sk.uniza.fri.ImageObject;
import sk.uniza.fri.action.enemy.ActionAttackDome;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Enemy {
    private ImageObject enemyImage;
    private int health;
    private int damage;
    private ImageLoader imageLoader;
    private String side;
    private Timer timer;
    private boolean isAttacking;

    public Enemy(int health, int damage, int x, int y, String enemyImageDirectory, int imageWidth, int imageHeight) {
        if (x < (1008 / 2)) {
            this.side = "/right";
        } else {
            this.side = "/left";
        }
        this.imageLoader = new ImageLoader(enemyImageDirectory + this.side);
        this.enemyImage = new ImageObject(this.imageLoader.getNextImage(), x, y, imageWidth, imageHeight);
        this.enemyImage.makeVisible();
        this.health = health;
        this.damage = damage;
        this.timer = new Timer();
    }

    public abstract void charge();

    public abstract boolean receiveDamage(int damage);

    protected void attack(int speedInSeconds) {
        if (!this.isAttacking) {
            System.out.println("Enemy is attacking the dome");
            ActionAttackDome.attackDome(this.damage);
            this.isAttacking = true;
            this.timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Enemy.this.isAttacking = false;
                }
            }, speedInSeconds * 1000L);
        }
    }

    public int getDamage() {
        return this.damage;
    }

    public int getHealth() {
        return this.health;
    }

    public void decreaseHealth(int health) {
        this.health -= health;
    }

    public ImageObject getEnemyImage() {
        return this.enemyImage;
    }

    public ImageLoader getImageLoader() {
        return this.imageLoader;
    }

    public String getSide() {
        if (this.enemyImage.getX() < (1008 / 2)) {
            this.side = "/right";
        } else {
            this.side = "/left";
        }
        return this.side;
    }
}
