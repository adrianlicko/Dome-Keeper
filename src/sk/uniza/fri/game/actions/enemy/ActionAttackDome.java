package sk.uniza.fri.game.actions.enemy;

import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.enemies.ranged.RangedEnemy;
import sk.uniza.fri.game.purchasables.weapons.projectiles.HomingProjectile;

public class ActionAttackDome {
    public static void attackDome(int damage) {
        if (Game.getInstance().getDome().receiveDamage(damage)) {
            System.out.println("Dome is under attack");
            Game.getInstance().getHUD().updateDomeHealth();
        } else {
            System.out.println("Dome is destroyed");
            Game.getInstance().endGame();
        }
    }

    public static void shootDome(RangedEnemy enemy, HomingProjectile projectile) {
        if (Math.sqrt(Math.pow(projectile.getProjectileImage().getHitX() - Game.getInstance().getDome().getDomeImage().getHitX(), 2) + Math.pow(projectile.getProjectileImage().getHitY() - Game.getInstance().getDome().getDomeImage().getHitY(), 2)) <= 50) {
            enemy.removeProjectile(projectile);
            if (Game.getInstance().getDome().receiveDamage(enemy.getDamage())) {
                System.out.println("Dome is under attack");
                Game.getInstance().getHUD().updateDomeHealth();
            } else {
                System.out.println("Dome is destroyed");
                Game.getInstance().endGame();
            }
        }
    }
}
