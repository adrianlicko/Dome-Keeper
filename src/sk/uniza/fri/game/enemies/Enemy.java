package sk.uniza.fri.game.enemies;

import sk.uniza.fri.game.ImageLoader;
import sk.uniza.fri.game.ImageObject;
import sk.uniza.fri.game.actions.enemy.ActionAttackDome;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class for creating enemies.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public abstract class Enemy {
    private final ImageObject enemyImage;
    private int health;
    private final int damage;
    private final ImageLoader imageLoader;
    private String side;
    private final Timer timer;
    private boolean isAttacking;

    /**
     * Constructor for the Enemy class.
     *
     * @param health - Integer value representing the health of the enemy.
     * @param damage - Integer value representing the damage that enemy deals.
     * @param enemyImageDirectory - String value representing the directory of the enemy images.
     * @param imageWidth - Integer value representing the width of the enemy image.
     * @param imageHeight - Integer value representing the height of the enemy image.
     */
    public Enemy(int health, int damage, String enemyImageDirectory, int imageWidth, int imageHeight) {
        this.imageLoader = new ImageLoader(enemyImageDirectory + "/right");
        this.enemyImage = new ImageObject(this.imageLoader.getNextImage(), 0, 0, imageWidth, imageHeight);
        this.randomSpawn();
        if (this.getEnemyImage().getX() < 450) {
            this.side = "/right";
        } else {
            this.side = "/left";
        }
        this.imageLoader.changeDirectory(enemyImageDirectory + this.side);
        this.enemyImage.makeVisible();

        this.health = health;
        this.damage = damage;
        this.timer = new Timer();
    }

    /**
     * Abstract method that is responsible for enemy moving and attacking the Dome.
     * This method is managed by manager, so it's called constantly.
     */
    public abstract void charge();

    /**
     * Method that is responsible for receiving damage from the player.
     * @param damage - Integer value representing the damage that the enemy receives.
     * @return - Boolean value representing if the enemy is still alive.
     */
    public abstract boolean receiveDamage(int damage);

    /**
     * Method that is responsible for attacking the Dome.
     *
     * @param speedInSeconds - Integer value representing the speed of the attack.
     */
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

    /**
     * Method that is responsible for random spawn of the enemy.
     */
    public abstract void randomSpawn();

    public int getDamage() {
        return this.damage;
    }

    public int getHealth() {
        return this.health;
    }

    protected void decreaseHealth(int health) {
        this.health -= health;
    }

    public ImageObject getEnemyImage() {
        return this.enemyImage;
    }

    protected ImageLoader getImageLoader() {
        return this.imageLoader;
    }

    /**
     * Method that is responsible for getting the side of the enemy based on the position.
     *
     * @return - String value representing the side of the enemy.
     */
    protected String getSide() {
        if (this.enemyImage.getX() < 450) {
            this.side = "/right";
        } else {
            this.side = "/left";
        }
        return this.side;
    }
}
