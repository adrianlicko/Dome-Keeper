package sk.uniza.fri.game.action.player;

import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.enemy.Enemy;
import sk.uniza.fri.game.weapons.DirectProjectile;
import sk.uniza.fri.game.weapons.Projectile;
import sk.uniza.fri.game.weapons.Weapon;

public class ActionAttackEnemy {
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
