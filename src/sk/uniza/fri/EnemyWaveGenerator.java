package sk.uniza.fri;

import sk.uniza.fri.game.enemy.Enemy;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EnemyWaveGenerator {
    private List<Class<? extends Enemy>> enemyClasses = new ArrayList<>();

    public EnemyWaveGenerator() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            List<String> packageNames = Arrays.asList("sk.uniza.fri.game.enemy.melee", "sk.uniza.fri.game.enemy.ranged");
            for (String packageName : packageNames) {
                String finalPackageName = packageName.replace(".", "/");
                java.util.Enumeration<java.net.URL> resources = classLoader.getResources(finalPackageName);
                while (resources.hasMoreElements()) {
                    java.net.URL resource = resources.nextElement();
                    java.nio.file.Path directoryPath = java.nio.file.Paths.get(resource.toURI());
                    java.nio.file.Files.walk(directoryPath)
                            .filter(java.nio.file.Files::isRegularFile)
                            .filter(file -> file.toString().endsWith(".class"))
                            .filter(file -> !file.toString().contains("$")) // Exclude inner classes
                            .forEach(file -> {
                                String className = finalPackageName.replace("/", ".") + '.' + file.getFileName().toString().replace(".class", "");
                                try {
                                    Class<? extends Enemy> enemyClass = (Class<? extends Enemy>) Class.forName(className);
                                    if (!Modifier.isAbstract(enemyClass.getModifiers()) && !enemyClass.isEnum() && !this.enemyClasses.contains(enemyClass)) {
                                        this.enemyClasses.add(enemyClass);
                                    }
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Found " + this.enemyClasses.size() + " enemy classes");
    }

    public Enemy getRandomEnemy(int health, int damage) {
        var random = new Random();
        try {
            Class<? extends Enemy> enemyClass = this.enemyClasses.get(random.nextInt(0, this.enemyClasses.size()));
            // Get the constructor that takes two integers as parameters
            java.lang.reflect.Constructor<? extends Enemy> constructor = enemyClass.getDeclaredConstructor(int.class, int.class);
            // Create a new instance using the constructor
            return constructor.newInstance(health, damage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}