package sk.uniza.fri.player;

import fri.shapesge.Image;
import sk.uniza.fri.ImageObject;
import sk.uniza.fri.action.player.ActionMine;
import sk.uniza.fri.map.Block;
import sk.uniza.fri.map.BlockType;
import sk.uniza.fri.map.GameMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Astronaut extends ImageObject {
    private static Astronaut instance;
    private HashMap<BlockType, Integer> inventory;
    private ActionMine mining;
    private boolean isAbleToEnterDome;
    private boolean isInDome;

    private Astronaut() {
        super("assets/Astronaut/Astronaut dole 3.png", 486, 325);
        super.makeVisible();
        this.mining = new ActionMine();
        this.inventory = new HashMap<>();
        for (BlockType blockType : BlockType.values()) {
            this.inventory.put(blockType, 0);
        }
    }

    /**
     * Method for entering or exiting the dome.
     * This method is managed by the manager and can be called by a specific key.
     */
    public void enterOrExitDome() {
        if (!this.isInDome && this.isAbleToEnterDome) {
            this.makeInvisible();
            this.isInDome = true;
        } else if (this.isInDome) {
            this.makeVisible();
            this.isInDome = false;
        }
    }

    /**
     * Method for moving the astronaut up.
     * This method is managed by the manager and can be called by a specific key.
     */
    @Override
    public void moveUp() {
        if (this.isInDome) {
            return;
        }
        this.isAbleToEnterDome = false;
        // this.x < 480 || this.x > 494, this is for upper part of the map excluding the middle part
        // this.y <= 369, if this would not be there, the astronaut would never move up
        // this.y <= 321, this is for upper part of the map in the middle block
        if ((this.getX() < 480 || this.getX() > 494) && this.getY() <= 369) { // for correct dimensions this.x >= 480 && this.x <= 494
            return;
        } else if (this.getY() <= 321) {
            this.isAbleToEnterDome = true;
            return;
        }

        Optional<Block> minedBlock = GameMap.getInstance().isInBlock(this.getX(), this.getY());
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), 2);
        } else {
            this.moveVertical(-2);
        }
    }

    /**
     * Method for moving the astronaut down.
     * This method is managed by the manager and can be called by a specific key.
     */
    @Override
    public void moveDown() {
        if (this.isInDome) {
            return;
        }
        this.isAbleToEnterDome = false;
        // this.y + 35 >= 702, this is for lower part of the map
        if (this.getY() + 35 >= 702) {
            return;
        }

        Optional<Block> minedBlock = GameMap.getInstance().isInBlock(this.getX(), this.getY() + 35);
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), 2);
        } else {
            this.moveVertical(2);
        }
    }

    /**
     * Method for moving the astronaut left.
     * This method is managed by the manager and can be called by a specific key.
     */
    @Override
    public void moveLeft() {
        if (this.isInDome) {
            return;
        }
        this.isAbleToEnterDome = false;
        // this.x <= 48, this is for left part of the map
        // this.y < 369 && this.x <= 494, this is for upper part of the map in the middle block
        if (this.getX() <= 48) {
            return;
        } else if (this.getY() < 369 && this.getX() <= 494) {
            return;
        }

        Optional<Block> minedBlock = GameMap.getInstance().isInBlock(this.getX(), this.getY());
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), 2);
        } else {
            this.moveHorizontal(-2);
        }
    }

    /**
     * Method for moving the astronaut right.
     * This method is managed by the manager and can be called by a specific key.
     */
    @Override
    public void moveRight() {
        if (this.isInDome) {
            return;
        }
        this.isAbleToEnterDome = false;
        // this.x + 35 >= 960, this is for right part of the map
        // this.y < 369 && this.x >= 480, this is for upper part of the map in the middle block
        if (this.getX() + 35 >= 960) {
            return;
        } else if (this.getY() < 369 && this.getX() >= 480) {
            return;

        }

        Optional<Block> minedBlock = GameMap.getInstance().isInBlock(this.getX() + 35, this.getY());
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), 2);
        } else {
            this.moveHorizontal(2);
        }
    }

    public Map<BlockType, Integer> getInventory() {
        return Collections.unmodifiableMap(this.inventory);
    }

    public void addToInventory(BlockType blockType) {
        this.inventory.replace(blockType, this.inventory.get(blockType) + blockType.getCoinValue());
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
