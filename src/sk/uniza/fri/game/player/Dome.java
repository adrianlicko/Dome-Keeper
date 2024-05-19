package sk.uniza.fri.game.player;

import sk.uniza.fri.game.ImageObject;
import sk.uniza.fri.game.purchasables.weapons.AK47;
import sk.uniza.fri.game.purchasables.weapons.Weapon;

import java.util.HashMap;

/**
 * Represents the dome that the player is defending.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class Dome {
    private final ImageObject domeImage;
    private final Astronaut astronaut;
    private double initialHealth;
    private double health;
    private int weaponSliderX;
    private Weapon weapon;
    // 70 is the length of the circle diameter
    // this 70 integer numbers are all possible positions that weapon can be in
    // second hashmap has the key of position x and value of position y
    private final HashMap<Integer, HashMap<Integer, Integer>> weaponPosition;

    /**
     * Constructor for the Dome class.
     *
     * @param astronaut - Astronaut object representing the player.
     */
    public Dome(Astronaut astronaut) {
        this.domeImage = new ImageObject("assets/aboveground/Dome small.png", 457, 260, 96, 95);
        this.domeImage.makeVisible();

        this.astronaut = astronaut;

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

        this.weapon = new AK47();
        this.weapon.setPurchased(true);
        this.equipWeapon(this.weapon);
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
        // position of the weapon on spawn
        this.weapon.getImage().changePosition(448 + 70 - 0, 251 + this.weaponPosition.get(0).get(70 - 0));
        this.weapon.getImage().makeVisible();
    }

    /**
     * Method for shooting the weapon.
     * This method is managed by the manager and can be called by a specific key.
     */
    public void shoot() {
        if (this.astronaut.isInDome()) {
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
     * This method is managed by the manager and can be called by a key RIGHT ARROW.
     */
    public void moveWeaponRight() {
        if (!this.astronaut.isInDome()) {
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
     * This method is managed by the manager and can be called by a key LEFT ARROW.
     */
    public void moveWeaponLeft() {
        if (!this.astronaut.isInDome()) {
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
        this.health = health;
    }

    public void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }

    public void increaseHealth(double health) {
        this.health += health;
        if (this.health > this.initialHealth) {
            this.health = this.initialHealth;
        }
    }

    public double getInitialHealth() {
        return this.initialHealth;
    }

    public double getHealth() {
        return this.health;
    }

    public ImageObject getDomeImage() {
        return this.domeImage;
    }
}
