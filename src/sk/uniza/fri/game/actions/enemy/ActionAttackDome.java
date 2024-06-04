package sk.uniza.fri.game.actions.enemy;

import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.enemies.ranged.RangedEnemy;
import sk.uniza.fri.game.purchasables.weapons.projectiles.Projectile;

/**
 * Class for attacking the dome.
 * Provides methods for attacking the dome on close and on long range.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class ActionAttackDome {

    /**
     * Method for attacking the dome on close range.
     *
     * @param damage - damage to be dealt to the dome.
     */
    public static void attackDome(int damage) {
        if (Game.getInstance().getDome().receiveDamage(damage)) {
            Game.getInstance().getHUD().updateDomeHealth();
        } else {
            Game.getInstance().endGame();
        }
    }

    /**
     * Method for attacking the dome on long range.
     *
     * @param enemy - enemy that is shooting at the dome.
     * @param projectile - projectile that is shot at the dome.
     */
    public static void shootDome(RangedEnemy enemy, Projectile projectile) {
        if (Math.sqrt(Math.pow(projectile.getProjectileImage().getHitX() - Game.getInstance().getDome().getDomeImage().getHitX(), 2) + Math.pow(projectile.getProjectileImage().getHitY() - Game.getInstance().getDome().getDomeImage().getHitY(), 2)) <= 50) {
            enemy.removeProjectile(projectile);
            if (Game.getInstance().getDome().receiveDamage(enemy.getDamage())) {
                Game.getInstance().getHUD().updateDomeHealth();
            } else {
                Game.getInstance().endGame();
            }
        }
    }
}
