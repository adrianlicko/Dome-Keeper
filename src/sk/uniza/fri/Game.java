package sk.uniza.fri;

import fri.shapesge.Manager;
import sk.uniza.fri.enemy.Enemy;
import sk.uniza.fri.enemy.melee.Walker;
import sk.uniza.fri.map.GameMap;
import sk.uniza.fri.player.Astronaut;
import sk.uniza.fri.player.Dome;
import sk.uniza.fri.player.HUD;

import java.util.ArrayList;

public class Game {
    public static void startGame() {
        GameMap.getInstance().createBlocks();

        Astronaut.getInstance();
        var manager = new Manager();
        manager.manageObject(Astronaut.getInstance());
        Dome.getInstance().setHealth(20);
        HUD.getInstance();

        var enemies = new ArrayList<Enemy>();
        enemies.add(new Walker(100, 5, 0, 265));
        //enemies.add(new Walker(100, 10, 980, 265));

        for (var enemy : enemies) {
            if (enemy instanceof Walker walkingEnemy) {
                manager.manageObject(walkingEnemy);
            }
        }
    }

    public static void endGame() {
        System.out.println("Game over");
        System.exit(0);
    }
}
