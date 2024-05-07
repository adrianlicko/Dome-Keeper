package sk.uniza.fri.game.action.player;

import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.enemy.Enemy;
import sk.uniza.fri.game.weapons.Bullet;
import sk.uniza.fri.game.weapons.Weapon;

public class ActionAttackEnemy {
    public static void shoot (Weapon weapon, Bullet bullet) {
        Enemy enemyToRemove = null;
        for (Enemy enemy : Game.getInstance().getEnemies()) {

            if (Math.sqrt(Math.pow(bullet.getBulletImage().getHitX() - enemy.getEnemyImage().getHitX(), 2) + Math.pow(bullet.getBulletImage().getHitY() - enemy.getEnemyImage().getHitY(), 2)) <= 15) {
                weapon.removeBullet(bullet);

                if (!enemy.receiveDamage(bullet.getDamage())) {
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
