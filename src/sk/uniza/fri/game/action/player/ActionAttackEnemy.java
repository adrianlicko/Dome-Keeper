package sk.uniza.fri.game.action.player;

import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.enemy.Enemy;
import sk.uniza.fri.game.purchasable.weapons.projectiles.Projectile;
import sk.uniza.fri.game.purchasable.weapons.Weapon;

/**
 * Class for attacking enemies.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class ActionAttackEnemy {

    /**
     * Method shoot.
     * Provides shooting at enemies.
     * Calculates the distance between the projectile and the enemy and if it's in range, it deals damage to the enemy.
     *
     * @param weapon
     * @param directProjectile
     */
    public static void shoot (Weapon weapon, Projectile directProjectile) {
        Enemy enemyToRemove = null;
        for (Enemy enemy : Game.getInstance().getEnemies()) {

            if (Math.sqrt(Math.pow(directProjectile.getProjectileImage().getHitX() - enemy.getEnemyImage().getHitX(), 2) + Math.pow(directProjectile.getProjectileImage().getHitY() - enemy.getEnemyImage().getHitY(), 2)) <= 15) {
                weapon.removeBullet(directProjectile);

                if (!enemy.receiveDamage(directProjectile.getDamage())) {
                    enemyToRemove = enemy;
                }
                System.out.println("Enemy hit");
            }
        }

        if (enemyToRemove != null) {
            Game.getInstance().removeEnemy(enemyToRemove);
        }
    }
}
