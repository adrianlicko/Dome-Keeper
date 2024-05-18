package sk.uniza.fri.game;

import fri.shapesge.Manager;
import sk.uniza.fri.EnemyWaveGenerator;
import sk.uniza.fri.Menu;
import sk.uniza.fri.game.enemy.Enemy;
import sk.uniza.fri.game.enemy.melee.Flyer;
import sk.uniza.fri.game.enemy.melee.Walker;
import sk.uniza.fri.game.enemy.ranged.RangedEnemy;
import sk.uniza.fri.game.enemy.ranged.Shifter;
import sk.uniza.fri.game.enemy.ranged.Worm;
import sk.uniza.fri.game.map.GameMap;
import sk.uniza.fri.game.player.Astronaut;
import sk.uniza.fri.game.player.Dome;
import sk.uniza.fri.game.player.HUD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game {
    private static Game instance;
    private GameMap gameMap;
    private Astronaut astronaut;
    private Dome dome;
    private HUD hud;
    private EnemyWaveGenerator enemyWaveGenerator;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Manager manager = new Manager();
    private int wave;
    private boolean canSpawnEnemy;
    private boolean isManagingObjects;
    private Random random;

    private Game() {
        this.wave = 0;
        this.random = new Random();
        this.canSpawnEnemy = true;

        this.gameMap = new GameMap();
        this.gameMap.createBlocks();

        this.astronaut = new Astronaut(this.gameMap);
        this.astronaut.setDamage(1);
        this.astronaut.setMovementSpeed(2);

        this.dome = new Dome(this.astronaut);
        this.dome.setHealth(60);

        this.hud = new HUD(this.astronaut, this.dome);

        this.enemyWaveGenerator = new EnemyWaveGenerator();

        this.startGame();
    }

    public void startGame() {
//        this.enemies.add(new Walker(40, 5));
//        this.enemies.add(new Walker(50, 10));
//        this.enemies.add(new Flyer(100, 7)); // y: range from -whatever to 150
//        this.enemies.add(new Flyer(100, 7));
//        this.enemies.add(new Worm(100, 7));
//        this.enemies.add(new Shifter(20, 7));

//        this.enemies.add(this.enemyWaveGenerator.getRandomEnemy( 5, 3));
//        this.enemies.add(this.enemyWaveGenerator.getRandomEnemy( 5, 3));
//        this.enemies.add(this.enemyWaveGenerator.getRandomEnemy( 5, 3));
//        this.enemies.add(this.enemyWaveGenerator.getRandomEnemy(this.random.nextInt(1, 5) * 10, this.random.nextInt(1, 5) * 3));
    }

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
                20000 // delay in milliseconds
        );
    }

    public void randomlySpawnEnemy() {
        if (this.canSpawnEnemy && this.isManagingObjects) {
            this.enemies.add(this.enemyWaveGenerator.getRandomEnemy((this.wave * 2) + this.random.nextInt(1, 5) * 10, (this.wave * 2) + this.random.nextInt(1, 5) * 3));
            this.manager.manageObject(this.enemies.get(this.enemies.size() - 1));
        }
    }




    public void stopOrStartGame() {
        Menu.getInstance().openShop();
    }

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

    public void manageObjects() {
        this.isManagingObjects = true;
        this.manager.manageObject(this.astronaut);
        this.manager.manageObject(this.dome);
        for (var enemy : this.enemies) {
            this.manager.manageObject(enemy);
        }
    }

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

    public int getWave() {
        return this.wave;
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
}
