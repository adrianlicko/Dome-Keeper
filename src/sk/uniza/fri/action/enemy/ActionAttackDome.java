package sk.uniza.fri.action.enemy;

import sk.uniza.fri.Game;
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
}
