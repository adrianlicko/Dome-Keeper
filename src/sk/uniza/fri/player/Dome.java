package sk.uniza.fri.player;

import fri.shapesge.Circle;
import fri.shapesge.Image;
import sk.uniza.fri.weapons.Weapon;
import sk.uniza.fri.weapons.player.Shotgun;

import java.util.HashMap;

public class Dome {
    private static Dome instance;
    private Image dome;
    private double initialHealth;
    private double health;
    private int x;
    private int y;
    private int weaponSliderX;
    private Weapon weapon;
    // first integer in slider is from 0 to 70
    // second hashmap has key of surX and value of surY
    private HashMap<Integer, HashMap<Integer, Integer>> weaponPosition;

    private Dome() {
        this.x = 457;
        this.y = 260;
        this.dome = new Image("assets/Nadzemie/Dome small.png", this.x, this.y);
        this.dome.makeVisible();

        // 70 is the length of the circle diameter
        this.weaponPosition = new HashMap<>();
        for (int i = 70; i >= 0; i--) {
            this.weaponPosition.put(i, new HashMap<>());
            int pythagorean = (int)Math.sqrt(Math.pow(35, 2) - Math.pow(35 - i, 2));
            this.weaponPosition.get(i).put((70 - i), (35 - pythagorean));

            // prints out all co-ordinates of the half circle
//            System.out.println("" + (70 - i) + " " + this.weaponPosition.get(i).get(70 - i));

            // visual representation of the circle in dome
//            var circle = new Circle(467 + (70 - i), 271 + this.weaponPosition.get(i).get(70 - i));
//            circle.changeColor("blue");
//            circle.changeSize(5);
//            circle.makeVisible();
        }
        this.weapon = new Shotgun(448 + 70 - 0, 251 + this.weaponPosition.get(0).get(70 - 0));
    }

    public void moveWeaponRight() {
        if (!Astronaut.getInstance().isInDome()) {
            return;
        }
        if (this.weaponSliderX > 0 && this.weaponSliderX <= 70) {
            this.weapon.changeAngle(1, this.weaponSliderX);
            this.weaponSliderX--;
            this.weapon.changePosition(448 + 70 - this.weaponSliderX, 251 + this.weaponPosition.get(this.weaponSliderX).get(70 - this.weaponSliderX));
        }
    }

    public void moveWeaponLeft() {
        if (!Astronaut.getInstance().isInDome()) {
            return;
        }
        if (this.weaponSliderX >= 0 && this.weaponSliderX < 70) {
            this.weapon.changeAngle(-1, this.weaponSliderX);
            this.weaponSliderX++;
            this.weapon.changePosition(448 + 70 - this.weaponSliderX, 251 + this.weaponPosition.get(this.weaponSliderX).get(70 - this.weaponSliderX));
        }
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
