package sk.uniza.fri.game.player;

import sk.uniza.fri.ImageLoader;
import sk.uniza.fri.ImageObject;
import sk.uniza.fri.game.action.player.ActionMine;
import sk.uniza.fri.game.map.Block;
import sk.uniza.fri.game.map.BlockType;
import sk.uniza.fri.game.map.GameMap;

import java.util.*;

public class Astronaut {
    private ImageLoader imageLoader;
    private ImageObject astronautImage;
    private static Astronaut instance;
    private HashMap<BlockType, Integer> inventory;
    private ActionMine mining;
    private int damage;
    private boolean isAbleToEnterDome;
    private boolean isInDome;
    private Timer idleTimer;

    private Astronaut() {
        this.imageLoader = new ImageLoader("assets/Astronaut/idle");
        this.astronautImage = new ImageObject(this.imageLoader.getNextImage(), 486, 325, 40, 49);
        this.astronautImage.makeVisible();
        this.mining = new ActionMine();
        this.inventory = new HashMap<>();
        for (BlockType blockType : BlockType.values()) {
            this.inventory.put(blockType, 0);
        }
        this.idleTimer = new Timer();
    }

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
     * This method is managed by the manager and can be called by a specific key.
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
     * This method is managed by the manager and can be called by a specific key.
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

        Optional<Block> minedBlock = GameMap.getInstance().isInBlock(this.astronautImage.getHitX(), this.astronautImage.getHitY() - (this.astronautImage.getImageHeight() / 2) + 5);
        this.changeImageDirectory("up");
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), this.damage);
        } else {
            this.astronautImage.moveVertical(-2);
        }
    }

    /**
     * Method for moving the astronaut down.
     * This method is managed by the manager and can be called by a specific key.
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

        Optional<Block> minedBlock = GameMap.getInstance().isInBlock(this.astronautImage.getHitX(), this.astronautImage.getHitY() + (this.astronautImage.getImageHeight() / 2) - 5);
        this.changeImageDirectory("down");
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), this.damage);
        } else {
            this.astronautImage.moveVertical(2);
        }
    }

    /**
     * Method for moving the astronaut left.
     * This method is managed by the manager and can be called by a specific key.
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

        Optional<Block> minedBlock = GameMap.getInstance().isInBlock(this.astronautImage.getHitX() - (this.astronautImage.getImageWidth() / 2), this.astronautImage.getHitY());
        if (minedBlock.isPresent()) {
            this.changeImageDirectory("drillLeft");
            this.mining.mine(minedBlock.get(), this.damage);
        } else {
            this.changeImageDirectory("left");
            this.astronautImage.moveHorizontal(-2);
        }
    }

    /**
     * Method for moving the astronaut right.
     * This method is managed by the manager and can be called by a specific key.
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

        Optional<Block> minedBlock = GameMap.getInstance().isInBlock(this.astronautImage.getHitX() + (this.astronautImage.getImageWidth() / 2) - 5, this.astronautImage.getHitY());
        if (minedBlock.isPresent()) {
            this.changeImageDirectory("drillRight");
            this.mining.mine(minedBlock.get(), this.damage);
        } else {
            this.changeImageDirectory("right");
            this.astronautImage.moveHorizontal(2);
        }
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

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

    public boolean isInDome() {
        return this.isInDome;
    }

    public static Astronaut getInstance() {
        if (instance == null) {
            instance = new Astronaut();
        }
        return instance;
    }
}
