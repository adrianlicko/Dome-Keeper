package sk.uniza.fri.game;

import fri.shapesge.Manager;
import sk.uniza.fri.Menu;
import sk.uniza.fri.game.enemy.Enemy;
import sk.uniza.fri.game.enemy.melee.Flyer;
import sk.uniza.fri.game.enemy.melee.Walker;
import sk.uniza.fri.game.enemy.ranged.Shifter;
import sk.uniza.fri.game.enemy.ranged.Worm;
import sk.uniza.fri.game.map.GameMap;
import sk.uniza.fri.game.player.Astronaut;
import sk.uniza.fri.game.player.Dome;
import sk.uniza.fri.game.player.HUD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private static Game instance;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Manager manager = new Manager();

    private Game() {
        this.startGame();
    }

    public void startGame() {
        GameMap.getInstance().createBlocks();

        Astronaut.getInstance();
        Dome.getInstance().setHealth(60);
        HUD.getInstance();

//        this.enemies.add(new Walker(40, 5, 0, 250));
//        this.enemies.add(new Walker(50, 10, 1030, 250));
//        this.enemies.add(new Flyer(100, 7, 100, 10)); // y: range from -whatever to 150
//        this.enemies.add(new Flyer(100, 7, 900, 10));
//        this.enemies.add(new Worm(100, 7, 50, 240));
        this.enemies.add(new Shifter(100, 7, 300, 100));
    }

    public List<Enemy> getEnemies() {
        return Collections.unmodifiableList(this.enemies);
    }

    public void removeEnemy(Enemy enemy) {
        enemy.getEnemyImage().makeInvisible();
        this.manager.stopManagingObject(enemy);
        this.enemies.remove(enemy);
    }

    public void stopOrStartGame() {
        Menu.getInstance().openShop();
    }

    public void manageObjects() {
        this.manager.manageObject(Astronaut.getInstance());
        this.manager.manageObject(Dome.getInstance());
        for (var enemy : this.enemies) {
            this.manager.manageObject(enemy);
        }
    }

    public void stopManagingObjects() {
        this.manager.stopManagingObject(Astronaut.getInstance());
        this.manager.stopManagingObject(Dome.getInstance());
        for (var enemy : this.enemies) {
            this.manager.stopManagingObject(enemy);
        }
    }

    public Manager getManager() {
        return this.manager;
    }

    public void endGame() {
        System.out.println("Game over");
        System.exit(0);
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
}
