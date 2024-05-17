package sk.uniza.fri.game;

import fri.shapesge.Manager;
import sk.uniza.fri.Menu;
import sk.uniza.fri.game.enemy.Enemy;
import sk.uniza.fri.game.enemy.ranged.Shifter;
import sk.uniza.fri.game.map.GameMap;
import sk.uniza.fri.game.player.Astronaut;
import sk.uniza.fri.game.player.Dome;
import sk.uniza.fri.game.player.HUD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private static Game instance;
    private GameMap gameMap;
    private Astronaut astronaut;
    private Dome dome;
    private HUD hud;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Manager manager = new Manager();

    private Game() {
        this.gameMap = new GameMap();
        this.gameMap.createBlocks();

        this.astronaut = new Astronaut(this.gameMap);
        this.astronaut.setDamage(1);
        this.astronaut.setMovementSpeed(2);

        this.dome = new Dome(this.astronaut);
        this.dome.setHealth(60);

        this.hud = new HUD(this.astronaut, this.dome);
        this.startGame();
    }

    public void startGame() {
//        this.enemies.add(new Walker(40, 5, 0, 250));
//        this.enemies.add(new Walker(50, 10, 1030, 250));
//        this.enemies.add(new Flyer(100, 7, 100, 10)); // y: range from -whatever to 150
//        this.enemies.add(new Flyer(100, 7, 900, 10));
//        this.enemies.add(new Worm(100, 7, 50, 240));
        this.enemies.add(new Shifter(20, 7, 300, 100));
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
        this.manager.manageObject(this.astronaut);
        this.manager.manageObject(this.dome);
        for (var enemy : this.enemies) {
            this.manager.manageObject(enemy);
        }
    }

    public void stopManagingObjects() {
        this.manager.stopManagingObject(this.astronaut);
        this.manager.stopManagingObject(this.dome);
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

    public GameMap getGameMap() {
        return this.gameMap;
    }

    public Astronaut getAstronaut() {
        return this.astronaut;
    }

    public Dome getDome() {
        return this.dome;
    }

    public HUD getHUD() {
        return this.hud;
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
}
