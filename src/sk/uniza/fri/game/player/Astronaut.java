package sk.uniza.fri.game.player;

import sk.uniza.fri.game.ImageLoader;
import sk.uniza.fri.game.ImageObject;
import sk.uniza.fri.game.actions.player.ActionMine;
import sk.uniza.fri.game.map.Block;
import sk.uniza.fri.game.map.BlockType;
import sk.uniza.fri.game.map.GameMap;

import java.util.*;

/**
 * Class that represents the astronaut which the player controls.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class Astronaut {
    private ImageLoader imageLoader;
    private ImageObject astronautImage;
    private GameMap gameMap;
    private HashMap<BlockType, Integer> inventory;
    private ActionMine mining;
    private int damage;
    private int movementSpeed;
    private boolean isAbleToEnterDome;
    private boolean isInDome;
    private Timer idleTimer;

    /**
     * Constructor for the Astronaut class.
     *
     * @param gameMap - GameMap object representing the game map.
     */
    public Astronaut(GameMap gameMap) {
        this.imageLoader = new ImageLoader("assets/Astronaut/idle");
        this.astronautImage = new ImageObject(this.imageLoader.getNextImage(), 486, 325, 40, 49);
        this.gameMap = gameMap;
        this.astronautImage.makeVisible();
        this.mining = new ActionMine();
        this.inventory = new HashMap<>();
        for (BlockType blockType : BlockType.values()) {
            this.inventory.put(blockType, 0);
        }
        this.idleTimer = new Timer();
    }

    /**
     * Method for changing the image directory of the astronaut.
     *
     * @param direction - String value representing the direction of the astronaut.
     */
    private void changeImageDirectory(String direction) {
        if (this.idleTimer != null) {
            this.idleTimer.cancel();
        }
        this.idleTimer = new Timer();
        this.imageLoader = new ImageLoader("assets/Astronaut/" + direction);
        this.idleTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Astronaut.this.imageLoader = new ImageLoader("assets/Astronaut/idle");
            }
        }, 500L);
    }

    /**
     * Method for entering or exiting the dome.
     * This method is managed by the manager and can be called by ENTER key.
     */
    public void enterOrExitDome() {
        if (!this.isInDome && this.isAbleToEnterDome) {
            this.astronautImage.makeInvisible();
            this.isInDome = true;
        } else if (this.isInDome) {
            this.astronautImage.makeVisible();
            this.isInDome = false;
        }
    }

    /**
     * Method for moving the astronaut up.
     * This method is managed by the manager and can be called by UP ARROW key.
     */
    public void moveUp() {
        if (this.isInDome) {
            return;
        }
        this.isAbleToEnterDome = false;
        // this.x < 480 || this.x > 494, this is for upper part of the map excluding the middle part
        // this.y <= 369, if this would not be there, the astronaut would never move up
        // this.y <= 321, this is for upper part of the map in the middle block
        if ((this.astronautImage.getX() < 480 || this.astronautImage.getX() > 494) && this.astronautImage.getY() <= 369) { // for correct dimensions this.x >= 480 && this.x <= 494
            return;
        } else if (this.astronautImage.getY() <= 321) {
            this.isAbleToEnterDome = true;
            return;
        }

        Optional<Block> minedBlock = this.gameMap.isInBlock(this.astronautImage.getHitX(), this.astronautImage.getHitY() - (this.astronautImage.getImageHeight() / 2) + 5);
        this.changeImageDirectory("up");
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), this.damage);
        } else {
            this.astronautImage.moveVertical(-this.movementSpeed);
        }
    }

    /**
     * Method for moving the astronaut down.
     * This method is managed by the manager and can be called by DOWN ARROW key.
     */
    public void moveDown() {
        if (this.isInDome) {
            return;
        }
        this.isAbleToEnterDome = false;
        // this.y + 35 >= 702, this is for lower part of the map
        if (this.astronautImage.getY() + this.astronautImage.getImageHeight() - 5 >= 702) {
            return;
        }

        Optional<Block> minedBlock = this.gameMap.isInBlock(this.astronautImage.getHitX(), this.astronautImage.getHitY() + (this.astronautImage.getImageHeight() / 2) - 5);
        this.changeImageDirectory("down");
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), this.damage);
        } else {
            this.astronautImage.moveVertical(this.movementSpeed);
        }
    }

    /**
     * Method for moving the astronaut left.
     * This method is managed by the manager and can be called by LEFT ARROW key.
     */
    public void moveLeft() {
        if (this.isInDome) {
            return;
        }
        this.isAbleToEnterDome = false;
        // this.x <= 48, this is for left part of the map
        // this.y < 369 && this.x <= 494, this is for upper part of the map in the middle block
        if (this.astronautImage.getX() <= 48) {
            return;
        } else if (this.astronautImage.getY() < 369 && this.astronautImage.getX() <= 494) {
            return;
        }

        Optional<Block> minedBlock = this.gameMap.isInBlock(this.astronautImage.getHitX() - (this.astronautImage.getImageWidth() / 2), this.astronautImage.getHitY());
        if (minedBlock.isPresent()) {
            this.changeImageDirectory("drillLeft");
            this.mining.mine(minedBlock.get(), this.damage);
        } else {
            this.changeImageDirectory("left");
            this.astronautImage.moveHorizontal(-this.movementSpeed);
        }
    }

    /**
     * Method for moving the astronaut right.
     * This method is managed by the manager and can be called by RIGHT ARROW key.
     */
    public void moveRight() {
        if (this.isInDome) {
            return;
        }
        this.isAbleToEnterDome = false;
        // this.x + 35 >= 960, this is for right part of the map
        // this.y < 369 && this.x >= 480, this is for upper part of the map in the middle block
        if (this.astronautImage.getX() + this.astronautImage.getImageWidth() - 5 >= 960) {
            return;
        } else if (this.astronautImage.getY() < 369 && this.astronautImage.getX() >= 480) {
            return;

        }

        Optional<Block> minedBlock = this.gameMap.isInBlock(this.astronautImage.getHitX() + (this.astronautImage.getImageWidth() / 2) - 5, this.astronautImage.getHitY());
        if (minedBlock.isPresent()) {
            this.changeImageDirectory("drillRight");
            this.mining.mine(minedBlock.get(), this.damage);
        } else {
            this.changeImageDirectory("right");
            this.astronautImage.moveHorizontal(this.movementSpeed);
        }
    }

    /**
     * Method for setting the damage of the astronaut.
     *
     * @param damage - Integer value representing the damage of the astronaut.
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Method for setting the movement speed of the astronaut.
     *
     * @param movementSpeed - Integer value representing the movement speed of the astronaut.
     */
    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    /**
     * Method for looping the astronaut images.
     */
    public void loopAstronautImages() {
        this.astronautImage.changeImage(this.imageLoader.getNextImage());
    }

    public Map<BlockType, Integer> getInventory() {
        return Collections.unmodifiableMap(this.inventory);
    }

    public void addCoinToInventory(BlockType blockType) {
        this.inventory.replace(blockType, this.inventory.get(blockType) + blockType.getCoinValue());
    }

    public void removeCoinFromInventory(BlockType blockType, int amount) {
        this.inventory.replace(blockType, this.inventory.get(blockType) - amount);
    }

    /**
     * Method for spawning the astronaut at the default position.
     */
    public void spawnAtSpawnpoint() {
        this.astronautImage.changePosition(486, 325);
    }

    public boolean isInDome() {
        return this.isInDome;
    }
}
