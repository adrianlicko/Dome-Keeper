package sk.uniza.fri.game.action.enemy;

import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.enemy.ranged.RangedEnemy;
import sk.uniza.fri.game.player.Dome;
import sk.uniza.fri.game.player.HUD;
import sk.uniza.fri.game.weapons.HomingProjectile;

public class ActionAttackDome {
    public static void attackDome(int damage) {
        if (Dome.getInstance().receiveDamage(damage)) {
            System.out.println("Dome is under attack");
            HUD.getInstance().updateDomeHealth();
        } else {
            System.out.println("Dome is destroyed");
            Game.getInstance().endGame();
        }
    }

    public static void shootDome(RangedEnemy enemy, HomingProjectile projectile) {
        if (Math.sqrt(Math.pow(projectile.getProjectileImage().getHitX() - Dome.getInstance().getDomeImage().getHitX(), 2) + Math.pow(projectile.getProjectileImage().getHitY() - Dome.getInstance().getDomeImage().getHitY(), 2)) <= 50) {
            enemy.removeProjectile(projectile);
            if (Dome.getInstance().receiveDamage(enemy.getDamage())) {
                System.out.println("Dome is under attack");
                HUD.getInstance().updateDomeHealth();
            } else {
                System.out.println("Dome is destroyed");
                Game.getInstance().endGame();
            }
        }
    }
}
