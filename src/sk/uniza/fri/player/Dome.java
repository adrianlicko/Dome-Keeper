package sk.uniza.fri.player;

import fri.shapesge.Image;

public class Dome {
    private static Dome instance;
    private Image dome;
    private double initialHealth;
    private double health;

    private Dome() {
        this.dome = new Image("assets/Nadzemie/Dome small.png", 457, 260);
        this.dome.makeVisible();
    }

    public boolean receiveDamage(double damage) {
        this.health -= damage;
        return this.health > 0;
    }

    public void setHealth(double health) {
        if (this.initialHealth > 0) {
            throw new IllegalStateException("Health can be set only once");
        }
        this.initialHealth = health;
        this.health = health;
    }

    public void increaseHealth(double health) {
        this.health += health;
    }

    public double getInitialHealth() {
        return this.initialHealth;
    }

    public double getHealth() {
        return this.health;
    }

    public static Dome getInstance() {
        if (instance == null) {
            instance = new Dome();
        }
        return instance;
    }
}
