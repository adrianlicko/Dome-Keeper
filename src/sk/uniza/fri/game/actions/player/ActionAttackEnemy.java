package sk.uniza.fri.game.actions.player;

import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.enemies.Enemy;
import sk.uniza.fri.game.purchasables.weapons.projectiles.Projectile;
import sk.uniza.fri.game.purchasables.weapons.Weapon;

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
     * @param weapon - Weapon object representing the weapon that the projectile is shot from.
     * @param projectile - Projectile object representing the projectile that is shot from the weapon.
     */
    public static void shoot(Weapon weapon, Projectile projectile) {
        Enemy enemyToRemove = null;
        for (Enemy enemy : Game.getInstance().getEnemies()) {

            if (Math.sqrt(Math.pow(projectile.getProjectileImage().getHitX() - enemy.getEnemyImage().getHitX(), 2) + Math.pow(projectile.getProjectileImage().getHitY() - enemy.getEnemyImage().getHitY(), 2)) <= 15) {
                weapon.removeBullet(projectile);

                if (!enemy.receiveDamage(projectile.getDamage())) {
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
