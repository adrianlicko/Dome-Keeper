package sk.uniza.fri.game;

import sk.uniza.fri.game.enemies.Enemy;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class that generates random enemies from the enemy classes.
 *
 * @author Copilot
 * @version 1.0
 * @since 1.0
 */
public class EnemyWaveGenerator {
    private final List<Class<? extends Enemy>> enemyClasses;

    /**
     * Constructor for the EnemyWaveGenerator class.
     * Finds all enemy classes in the specified packages and stores them in a list.
     */
    public EnemyWaveGenerator() {
        this.enemyClasses = new ArrayList<>();

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            List<String> packageNames = Arrays.asList("sk.uniza.fri.game.enemies.melee", "sk.uniza.fri.game.enemies.ranged");
            for (String packageName : packageNames) {
                String finalPackageName = packageName.replace(".", "/");
                java.util.Enumeration<java.net.URL> resources = classLoader.getResources(finalPackageName);
                while (resources.hasMoreElements()) {
                    java.net.URL resource = resources.nextElement();
                    java.nio.file.Path directoryPath = java.nio.file.Paths.get(resource.toURI());
                    try (java.util.stream.Stream<java.nio.file.Path> stream = java.nio.file.Files.walk(directoryPath)) {
                        stream
                                .filter(java.nio.file.Files::isRegularFile)
                                .filter(file -> file.toString().endsWith(".class"))
                                .filter(file -> !file.toString().contains("$")) // Exclude inner classes
                                .forEach(file -> {
                                    String className = finalPackageName.replace("/", ".") + '.' + file.getFileName().toString().replace(".class", "");
                                    try {
                                        Class<?> clazz = Class.forName(className);
                                        if (Enemy.class.isAssignableFrom(clazz)) {
                                            Class<? extends Enemy> enemyClass = clazz.asSubclass(Enemy.class);
                                            if (!Modifier.isAbstract(enemyClass.getModifiers()) && !enemyClass.isEnum() && !this.enemyClasses.contains(enemyClass)) {
                                                this.enemyClasses.add(enemyClass);
                                            }
                                        }
                                    } catch (ClassNotFoundException e) {
                                        System.err.println("Class not found");
                                    }
                                });
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error while loading enemy classes");
        }
        System.out.println("Found " + this.enemyClasses.size() + " enemy classes");
    }

    /**
     * Returns a random enemy with the specified health and damage.
     *
     * @param health - the health of the enemy.
     * @param damage - the damage that the enemy deals.
     * @return - a random enemy
     */
    public Enemy getRandomEnemy(int health, int damage) {
        var random = new Random();
        try {
            Class<? extends Enemy> enemyClass = this.enemyClasses.get(random.nextInt(0, this.enemyClasses.size()));
            // Get the constructor that takes two integers as parameters
            java.lang.reflect.Constructor<? extends Enemy> constructor = enemyClass.getDeclaredConstructor(int.class, int.class);
            // Create a new instance using the constructor
            return constructor.newInstance(health, damage);
        } catch (Exception e) {
            System.err.println("Error while creating enemy");
        }
        return null;
    }



}