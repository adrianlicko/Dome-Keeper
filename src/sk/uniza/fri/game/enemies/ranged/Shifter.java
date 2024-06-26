package sk.uniza.fri.game.enemies.ranged;

import java.util.Random;

/**
 * Represents an enemy that shifts and attacks the player from a distance.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class Shifter extends RangedEnemy {

    /**
     * Constructor for the Shifter class.
     * The instance of this class is created by EnemyWaveGenerator
     *
     * @param health - Integer value representing the health of the enemy.
     * @param damage - Integer value representing the damage that enemy deals.
     */
    public Shifter(int health, int damage) {
        super(health, damage, "assets/enemies/shifter/appear", 96, 80);
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
                this.getImageLoader().changeDirectory("assets/enemies/shifter/appear" + this.getSide());
                break;
            case IDLE:
                if (this.getTimeToIdle() == 0) {
                    this.setTimeToIdle(this.getRandom().nextInt(40, 80));
                    this.setIdleCount(0);
                }

                if (this.getIdleCount() < this.getTimeToIdle()) {
                    this.getImageLoader().changeDirectory("assets/enemies/shifter/idle" + this.getSide());
                    this.getEnemyImage().changeImage(this.getImageLoader().getNextImage());
                    this.setIdleCount(this.getIdleCount() + 1);
                } else {
                    this.setTimeToIdle(0);
                    this.setIdleCount(0);
                    this.setState(State.ATTACK);
                }
                return;
            case ATTACK:
                this.getImageLoader().changeDirectory("assets/enemies/shifter/attack" + this.getSide());
                break;
            case DISAPPEAR:
                this.getImageLoader().changeDirectory("assets/enemies/shifter/disappear" + this.getSide());
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
                    this.addProjectile("assets/enemies/shifter/bullet", -40, 30, 5, 106, 100);

                    if (this.getRandom().nextInt(3) == 0) {
                        this.setState(State.IDLE);
                    } else {
                        this.setState(State.DISAPPEAR);
                    }
                    break;
                case DISAPPEAR:
                    this.setState(State.UNVISIBLE);
                    this.setInvisibleCount(1);
                    break;
                case UNVISIBLE:
                    this.setInvisibleCount(this.getInvisibleCount() - 1);
                    if (this.getInvisibleCount() == 0) {
                        int side = this.getRandom().nextInt(2);
                        int randomSpawnX;
                        if (side == 0) {
                            randomSpawnX = this.getRandom().nextInt(50, 320);
                        } else {
                            randomSpawnX = this.getRandom().nextInt(570, 850);
                        }

                        int randomSpawnY = this.getRandom().nextInt(0, 240);

                        this.getEnemyImage().changePosition(randomSpawnX, randomSpawnY);
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
            this.getEnemyImage().changePosition(random.nextInt(150, 425), random.nextInt(100, 150));
        } else {
            this.getEnemyImage().changePosition(random.nextInt(490, 770), random.nextInt(100, 150));
        }
    }
}
