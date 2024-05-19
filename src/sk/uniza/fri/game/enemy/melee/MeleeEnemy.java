package sk.uniza.fri.game.enemy.melee;

import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.enemy.Enemy;

/**
 * Abstract class for enemies that attacks the dome on close range.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public abstract class MeleeEnemy extends Enemy {

    /**
     * Constructor for the MeleeEnemy class.
     *
     * @param health - Integer value representing the health of the enemy.
     * @param damage - Integer value representing the damage of the enemy.
     * @param enemyImageDirectory - String value representing the directory of the enemy image.
     * @param imageWidth - Integer value representing the width of the enemy image.
     * @param imageHeight - Integer value representing the height of the enemy image.
     */
    public MeleeEnemy(int health, int damage, String enemyImageDirectory, int imageWidth, int imageHeight) {
        super(health, damage, enemyImageDirectory, imageWidth, imageHeight);
    }

    /**
     * Method that is responsible for enemy moving and attacking the Dome.
     *
     * @param enemyPositionCorrectionFromLeft - Integer value representing the correction of the enemy position from the left side.
     * @param enemyPositionCorrectionFromRight - Integer value representing the correction of the enemy position from the right side.
     * @param enemyPositionCorrectionFromTop - Integer value representing the correction of the enemy position from the top side.
     * @param attackSpeed - Integer value representing the speed of the attack.
     * @param attackImageDirectory - String value representing the directory of the attack images.
     */
    protected void charge(int enemyPositionCorrectionFromLeft, int enemyPositionCorrectionFromRight, int enemyPositionCorrectionFromTop, int attackSpeed, String attackImageDirectory) {
        // moves enemy if it is on the left side of the map
        if (this.getEnemyImage().getX() + enemyPositionCorrectionFromLeft < Game.getInstance().getDome().getDomeImage().getX()) {
            this.getEnemyImage().changePosition(this.getEnemyImage().getX() + 2, this.getEnemyImage().getY());

            // moves enemy if it is on the right side of the map
        } else if (this.getEnemyImage().getX() + enemyPositionCorrectionFromRight > Game.getInstance().getDome().getDomeImage().getX() + 96) {
            this.getEnemyImage().changePosition(this.getEnemyImage().getX() - 2, this.getEnemyImage().getY());

            // moves enemy if it is on the top far away from dome
        } else if (this.getEnemyImage().getY() < enemyPositionCorrectionFromTop) {
            this.getEnemyImage().changePosition(this.getEnemyImage().getX(), this.getEnemyImage().getY() + 1);

        } else {
            super.attack(attackSpeed);
            this.getImageLoader().changeDirectory(attackImageDirectory + this.getSide());
            this.getEnemyImage().changeImage(this.getImageLoader().getNextImage());
            return;
        }
        this.getEnemyImage().changeImage(this.getImageLoader().getNextImage());
    }

    /**
     * Method that is responsible for random spawn of the enemy.
     *
     * @param damage - Integer value representing the damage that the enemy receives.
     * @return - Boolean value representing if the enemy is still alive.
     */
    @Override
    public boolean receiveDamage(int damage) {
        this.decreaseHealth(damage);
        return this.getHealth() > 0;
    }
}
