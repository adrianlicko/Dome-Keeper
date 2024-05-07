package sk.uniza.fri.action.enemy;

import sk.uniza.fri.Game;
import sk.uniza.fri.enemy.ranged.EnemyProjectile;
import sk.uniza.fri.enemy.ranged.RangedEnemy;
import sk.uniza.fri.player.Dome;
import sk.uniza.fri.player.HUD;

public class ActionAttackDome {
    public static void attackDome(int damage) {
        if (Dome.getInstance().receiveDamage(damage)) {
            System.out.println("Dome is under attack");
            HUD.getInstance().updateDomeHealth();
        } else {
            System.out.println("Dome is destroyed");
            Game.endGame();
        }
    }

    public static void shootDome(RangedEnemy enemy, EnemyProjectile projectile) {
        if (Math.sqrt(Math.pow(projectile.getBulletImage().getHitX() - Dome.getInstance().getDomeImage().getHitX(), 2) + Math.pow(projectile.getBulletImage().getHitY() - Dome.getInstance().getDomeImage().getHitY(), 2)) <= 50) {
            enemy.removeProjectile(projectile);
            if (Dome.getInstance().receiveDamage(enemy.getDamage())) {
                System.out.println("Dome is under attack");
                HUD.getInstance().updateDomeHealth();
            } else {
                System.out.println("Dome is destroyed");
                Game.endGame();
            }
        }
    }
}
