package sk.uniza.fri;

import fri.shapesge.Manager;
import sk.uniza.fri.enemy.Enemy;
import sk.uniza.fri.enemy.melee.Walker;
import sk.uniza.fri.map.GameMap;
import sk.uniza.fri.player.Astronaut;
import sk.uniza.fri.player.Dome;
import sk.uniza.fri.player.HUD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private static ArrayList<Enemy> enemies = new ArrayList<>();
    private static Manager manager = new Manager();

    public static void startGame() {
        GameMap.getInstance().createBlocks();

        Astronaut.getInstance();
        //var manager = new Manager();
        manager.manageObject(Astronaut.getInstance());
        Dome.getInstance().setHealth(20);
        manager.manageObject(Dome.getInstance());
        HUD.getInstance();

        //var enemies = new ArrayList<Enemy>();
//        enemies.add(new Walker(100, 5, 0, 265));
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

    public static List<Enemy> getEnemies() {
        return Collections.unmodifiableList(enemies);
    }

    public static void removeEnemy(Enemy enemy) {
        enemy.makeInvisible();
        manager.stopManagingObject(enemy);
        enemies.remove(enemy);
    }
}
