package sk.uniza.fri.game.enemies.ranged;

import java.util.Random;

/**
 * Represents an enemy that attacks the player from a distance.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class Worm extends RangedEnemy {

    public Worm(int health, int damage) {
        super(health, damage, "assets/enemies/worm/appear", 132, 100);
    }

    /**
     * Method that is responsible for moving and attacking the Dome.
     * Also changes the image directory based on the state of the enemy.
     */
    @Override
    public void charge() {
        // Switch statement that changes the image directory based on the state of the enemy.
        switch (this.getState()) {
            case APPEAR:
                this.getEnemyImage().makeVisible();
                this.getImageLoader().changeDirectory("assets/enemies/worm/appear" + this.getSide());
                break;
            case IDLE:
                if (this.getTimeToIdle() == 0) {
                    this.setTimeToIdle(this.getRandom().nextInt(40, 80));
                    this.setIdleCount(0);
                }

                if (this.getIdleCount() < this.getTimeToIdle()) {
                    this.getImageLoader().changeDirectory("assets/enemies/worm/idle" + this.getSide());
                    this.getEnemyImage().changeImage(this.getImageLoader().getNextImage());
                    this.setIdleCount(this.getIdleCount() + 1);
                    return;
                } else {
                    this.setTimeToIdle(0);
                    this.setIdleCount(0);
                    this.setState(State.ATTACK);
                    return;
                }
            case ATTACK:
                this.getImageLoader().changeDirectory("assets/enemies/worm/attack" + this.getSide());
                break;
            case DISAPPEAR:
                this.getImageLoader().changeDirectory("assets/enemies/worm/disappear" + this.getSide());
                break;
            case UNVISIBLE:
                this.getEnemyImage().makeInvisible();
                break;
        }

        // Changes the image of the enemy based on the state of the enemy.
        String imagePath = this.getImageLoader().getNextImageWithoutLoop();
        if (imagePath != null) {
            this.getEnemyImage().changeImage(imagePath);
        } else {
            // Switch statement that changes the state of the enemy based on the current state.
            switch (this.getState()) {
                case APPEAR:
                    this.setState(State.IDLE);
                    break;
                case IDLE:
                    this.setState(State.ATTACK);
                    break;
                case ATTACK:
                    this.addProjectile("assets/enemies/worm/bullet", 0, 75, 25, 50, 50);

                    if (this.getRandom().nextInt(3) == 0) {
                        this.setState(State.IDLE);
                    } else {
                        this.setState(State.DISAPPEAR);
                    }
                    break;
                case DISAPPEAR:
                    this.setState(State.UNVISIBLE);
                    this.setInvisibleCount(this.getRandom().nextInt(1, 8));
                    break;
                case UNVISIBLE:
                    this.setInvisibleCount(this.getInvisibleCount() - 1);
                    if (this.getInvisibleCount() == 0) {
                        int side = this.getRandom().nextInt(2);
                        int randomSpawnX;
                        if (side == 0) {
                            randomSpawnX = this.getRandom().nextInt(100, 300);
                        } else {
                            randomSpawnX = this.getRandom().nextInt(600, 800);
                        }
                        this.getEnemyImage().changePosition(randomSpawnX, this.getEnemyImage().getY());
                        this.setState(State.APPEAR);
                    }
                    break;
            }
        }
    }

    @Override
    public void randomSpawn() {
        var random = new Random();
        if (random.nextBoolean()) {
            this.getEnemyImage().changePosition(random.nextInt(0, 330), 235);
        } else {
            this.getEnemyImage().changePosition(random.nextInt(540, 870), 235);
        }
    }
}
