package sk.uniza.fri.game.enemies.melee;

import java.util.Random;

/**
 * Represents an enemy that flies and attacks the player on close range.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class Flyer extends MeleeEnemy {

    /**
     * Constructor for the Flyer class.
     * The instance of this class is created by EnemyWaveGenerator
     *
     * @param health - Integer value representing the health of the enemy.
     * @param damage - Integer value representing the damage that enemy deals.
     */
    public Flyer(int health, int damage) {
        super(health, damage, "assets/enemies/flyer/move", 110, 150);
    }

    @Override
    public void charge() {
        super.charge(80, 40, 150, 4, "assets/enemies/flyer/attack");
    }

    /**
     * Method that is responsible for random spawn of the enemy.
     */
    @Override
    public void randomSpawn() {
        var random = new Random();
        if (random.nextBoolean()) {
            this.getEnemyImage().changePosition(-75, random.nextInt(-10, 160));
        } else {
            this.getEnemyImage().changePosition(980, random.nextInt(-10, 160));
        }
    }
}
