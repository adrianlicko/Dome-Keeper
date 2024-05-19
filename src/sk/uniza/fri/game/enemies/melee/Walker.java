package sk.uniza.fri.game.enemies.melee;

import java.util.Random;

/**
 * Represents an enemy that walks and attacks the player on close range.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class Walker extends MeleeEnemy {

    /**
     * Constructor for the Walker class.
     * The instance of this class is created by EnemyWaveGenerator
     *
     * @param health - Integer value representing the health of the enemy.
     * @param damage - Integer value representing the damage that enemy deals.
     */
    public Walker(int health, int damage) {
        super(health, damage, "assets/enemies/walker/move", 124, 65);
    }

    @Override
    public void charge() {
        super.charge(100, 20, 0, 4, "assets/enemies/walker/attack");
    }

    /**
     * Method that is responsible for random spawn of the enemy.
     */
    @Override
    public void randomSpawn() {
        var random = new Random();
        if (random.nextBoolean()) {
            this.getEnemyImage().changePosition(-100, 250);
        } else {
            this.getEnemyImage().changePosition(1000, 250);
        }
    }
}
