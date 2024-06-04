package sk.uniza.fri.game;

import fri.shapesge.Manager;
import sk.uniza.fri.Menu;
import sk.uniza.fri.game.enemies.Enemy;
import sk.uniza.fri.game.enemies.ranged.RangedEnemy;
import sk.uniza.fri.game.map.GameMap;
import sk.uniza.fri.game.player.Astronaut;
import sk.uniza.fri.game.player.Dome;
import sk.uniza.fri.game.player.HUD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class that represents the main class that starts and manages the game.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class Game {
    private static Game instance;
    private final GameMap gameMap;
    private final Astronaut astronaut;
    private final Dome dome;
    private final HUD hud;
    private final EnemyWaveGenerator enemyWaveGenerator;
    private final ArrayList<Enemy> enemies;
    private final Manager manager;
    private int wave;
    private boolean canSpawnEnemy;
    private boolean isManagingObjects;
    private final Random random;

    /**
     * Constructor for the Game class.
     * Creates a new game with the default values.
     */
    private Game() {
        this.manager = new Manager();
        this.enemies = new ArrayList<>();
        this.wave = 0;
        this.random = new Random();
        this.canSpawnEnemy = true;

        this.gameMap = new GameMap();
        this.gameMap.createBlocks();

        this.astronaut = new Astronaut(this.gameMap);
        this.astronaut.setDamage(1);
        this.astronaut.setMovementSpeed(2);

        this.dome = new Dome(this.astronaut);
        this.dome.setInitialHealth(60);
        this.dome.setHealth(60);

        this.hud = new HUD(this.astronaut, this.dome);

        this.enemyWaveGenerator = new EnemyWaveGenerator();
    }

    /**
     * Starts new wave of enemies.
     * When this method is called, it sets the canSpawnEnemy variable to false and after 20 seconds it sets it back to true.
     */
    public void startNewWave() {
        this.canSpawnEnemy = false;
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Game.this.canSpawnEnemy = true;
                        Game.this.wave++;
                    }
                },
                25000 // delay in milliseconds
        );
    }

    /**
     * Randomly spawns an enemy.
     * This method is called by manager every 10 seconds in sbge.ini config file.
     */
    public void randomlySpawnEnemy() {
        if (this.canSpawnEnemy && this.isManagingObjects) {
            this.enemies.add(this.enemyWaveGenerator.getRandomEnemy((this.wave * 2) + this.random.nextInt(1, 5) * 10, this.random.nextInt(1, (this.wave + 2)) * 2));
            this.manager.manageObject(this.enemies.getLast());
        }
    }

    /**
     * Stops or starts the game.
     * This method is called by a key P in sbge.ini config file, and it opens the shop.
     */
    public void stopOrStartGame() {
//        Menu.getInstance().openShop();
        Menu.getInstance().showMenu();
    }

    /**
     * Removes an enemy from the game.
     * When there are no enemies left, it starts a new wave.
     *
     * @param enemy - Enemy object that is going to be removed.
     */
    public void removeEnemy(Enemy enemy) {
        enemy.getEnemyImage().makeInvisible();
        if (enemy instanceof RangedEnemy rangedEnemy) {
            rangedEnemy.removeAllProjectiles();
        }
        this.manager.stopManagingObject(enemy);
        this.enemies.remove(enemy);
        if (this.enemies.isEmpty()) {
            this.startNewWave();
        }
    }

    public List<Enemy> getEnemies() {
        return Collections.unmodifiableList(this.enemies);
    }

    /**
     * Manages the objects in the game.
     */
    public void manageObjects() {
        this.isManagingObjects = true;
        this.manager.manageObject(this.astronaut);
        this.manager.manageObject(this.dome);
        for (var enemy : this.enemies) {
            this.manager.manageObject(enemy);
        }
    }

    /**
     * Stops managing the objects in the game.
     */
    public void stopManagingObjects() {
        this.isManagingObjects = false;
        this.manager.stopManagingObject(this.astronaut);
        this.manager.stopManagingObject(this.dome);
        for (var enemy : this.enemies) {
            this.manager.stopManagingObject(enemy);
        }
    }

    public Manager getManager() {
        return this.manager;
    }

    /**
     * Ends the game.
     */
    public void endGame() {
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

    public int getWave() {
        return this.wave;
    }

    public void setWave(int waveNumber) {
        this.wave = waveNumber;
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
}
