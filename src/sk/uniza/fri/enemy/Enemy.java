package sk.uniza.fri.enemy;

import fri.shapesge.Image;
import sk.uniza.fri.action.enemy.ActionAttackDome;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Enemy {
    private Image enemyImage;
    private int health;
    private int damage;
    private int x;
    private int y;
    private Timer timer;
    private boolean isAttacking;

    public Enemy(int health, int damage, int x, int y, String enemyImagePath) {
        this.enemyImage = new Image(enemyImagePath, x, y);
        this.enemyImage.makeVisible();
        this.health = health;
        this.damage = damage;
        this.x = x;
        this.y = y;
        this.timer = new Timer();
    }

    public abstract boolean receiveDamage(int damage);

    public void attack(int speedInSeconds) {
        if (!this.isAttacking) {
            System.out.println("Walker is attacking the dome");
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

    public void changePosition(int x, int y) {
        this.enemyImage.changePosition(x, y);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void makeInvisible() {
        this.enemyImage.makeInvisible();
    }
}
