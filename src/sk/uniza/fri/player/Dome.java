package sk.uniza.fri.player;

import fri.shapesge.Image;

public class Dome {
    private static Dome instance;
    private Image dome;
    private double initialHealth;
    private double health;
    private int x;
    private int y;

    private Dome() {
        this.x = 457;
        this.y = 260;
        this.dome = new Image("assets/Nadzemie/Dome small.png", this.x, this.y);
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

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public static Dome getInstance() {
        if (instance == null) {
            instance = new Dome();
        }
        return instance;
    }
}
