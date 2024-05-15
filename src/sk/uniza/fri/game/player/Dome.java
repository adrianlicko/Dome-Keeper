package sk.uniza.fri.game.player;

import sk.uniza.fri.ImageObject;
import sk.uniza.fri.game.weapons.Weapon;
import sk.uniza.fri.game.weapons.player.MagicWand;

import java.util.HashMap;

public class Dome {
    private ImageObject domeImage;
    private static Dome instance;
    private double initialHealth;
    private double health;
    private int weaponSliderX;
    private Weapon weapon;
    // 70 is the length of the circle diameter
    // this 70 integer numbers are all possible positions that weapon can be in
    // second hashmap has the key of position x and value of position y
    private HashMap<Integer, HashMap<Integer, Integer>> weaponPosition;

    private Dome() {
        this.domeImage = new ImageObject("assets/Nadzemie/Dome small.png", 457, 260, 96, 95);
        this.domeImage.makeVisible();

        this.weaponPosition = new HashMap<>();
        // 70 is the length of the circle diameter
        for (int i = 70; i >= 0; i--) {
            this.weaponPosition.put(i, new HashMap<>());
            // pythagorean theorem, calculates the y position of the weapon
            int pythagorean = (int)Math.sqrt(Math.pow(35, 2) - Math.pow(35 - i, 2));
            // puts the x and y position of the weapon in the hashmap
            this.weaponPosition.get(i).put((70 - i), (35 - pythagorean));

            // prints out all co-ordinates of the half circle
//            System.out.println("" + (70 - i) + " " + this.weaponPosition.get(i).get(70 - i));

            // visual representation of the circle in dome
//            var circle = new Circle(467 + (70 - i), 271 + this.weaponPosition.get(i).get(70 - i));
//            circle.changeColor("blue");
//            circle.changeSize(5);
//            circle.makeVisible();
        }

        // position of the weapon on spawn
        //this.weapon = new Shotgun(448 + 70 - 0, 251 + this.weaponPosition.get(0).get(70 - 0));
        this.equipWeapon(new MagicWand());
    }

    public void equipWeapon(Weapon weapon) {
        if (this.weapon != null) {
            this.weapon.setEquipped(false);
            this.weapon.getImage().makeInvisible();
            this.removeBullets();
        }
        this.weaponSliderX = 0;
        this.weapon = weapon;
        this.weapon.setEquipped(true);
        this.weapon.getImage().changePosition(448 + 70 - 0, 251 + this.weaponPosition.get(0).get(70 - 0));
        this.weapon.getImage().makeVisible();
    }

    /**
     * Method for shooting the weapon.
     * This method is managed by the manager and can be called by a specific key.
     */
    public void shoot() {
        if (Astronaut.getInstance().isInDome()) {
            this.weapon.shoot();
        }
    }

    /**
     * Method for moving the bullets.
     * This method is managed by the manager and is constantly called.
     */
    public void moveBullets() {
        this.weapon.moveBullets();
    }

    public void removeBullets() {
        this.weapon.removeBullets();
    }

    /**
     * Method for moving the weapon right.
     * This method is managed by the manager and can be called by a specific key.
     */
    public void moveWeaponRight() {
        if (!Astronaut.getInstance().isInDome()) {
            return;
        }
        if (this.weaponSliderX > 0 && this.weaponSliderX <= 70) {
            this.weapon.changeAngle(1, this.weaponSliderX);
            this.weaponSliderX--;
            this.weapon.getImage().changePosition(448 + 70 - this.weaponSliderX, 251 + this.weaponPosition.get(this.weaponSliderX).get(70 - this.weaponSliderX));
        }
    }

    /**
     * Method for moving the weapon left.
     * This method is managed by the manager and can be called by a specific key.
     */
    public void moveWeaponLeft() {
        if (!Astronaut.getInstance().isInDome()) {
            return;
        }
        if (this.weaponSliderX >= 0 && this.weaponSliderX < 70) {
            this.weapon.changeAngle(-1, this.weaponSliderX);
            this.weaponSliderX++;
            this.weapon.getImage().changePosition(448 + 70 - this.weaponSliderX, 251 + this.weaponPosition.get(this.weaponSliderX).get(70 - this.weaponSliderX));
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

    public static Dome getInstance() {
        if (instance == null) {
            instance = new Dome();
        }
        return instance;
    }

    public ImageObject getDomeImage() {
        return this.domeImage;
    }
}
