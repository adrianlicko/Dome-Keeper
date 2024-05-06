package sk.uniza.fri.action.player;

import sk.uniza.fri.Game;
import sk.uniza.fri.enemy.Enemy;
import sk.uniza.fri.weapons.Bullet;
import sk.uniza.fri.weapons.Weapon;

public class ActionAttackEnemy {
    public static void shoot (Weapon weapon, Bullet bullet) {
        Enemy enemyToRemove = null;
        for (Enemy enemy : Game.getEnemies()) {

            if (Math.sqrt(Math.pow(bullet.getBulletImage().getHitX() - enemy.getEnemyImage().getHitX(), 2) + Math.pow(bullet.getBulletImage().getHitY() - enemy.getEnemyImage().getHitY(), 2)) <= 10) {
                weapon.removeBullet(bullet);

                if (!enemy.receiveDamage(bullet.getDamage())) {
                    enemyToRemove = enemy;
                }
                System.out.println("Enemy hit");
            }
        }

        if (enemyToRemove != null) {
            Game.removeEnemy(enemyToRemove);
        }
    }
}
