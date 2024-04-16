package sk.uniza.fri.player;

import fri.shapesge.Image;
import sk.uniza.fri.action.player.ActionMine;
import sk.uniza.fri.map.Block;
import sk.uniza.fri.map.BlockType;
import sk.uniza.fri.map.GameMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Astronaut {
    private static Astronaut instance;
    private Image astronaut;
    private HashMap<BlockType, Integer> inventory;
    private int x;
    private int y;
    private ActionMine mining;
    private boolean isAbleToEnterDome;
    private boolean isInDome;

    private Astronaut() {
        this.x = 486;
        this.y = 325;
        this.astronaut = new Image("assets/Astronaut/Astronaut dole 3.png", this.x, this.y);
        this.astronaut.makeVisible();
        this.mining = new ActionMine();
        this.inventory = new HashMap<>();
        for (BlockType blockType : BlockType.values()) {
            this.inventory.put(blockType, 0);
        }
    }

    public void enterOrExitDome() {
        if (!this.isInDome && this.isAbleToEnterDome) {
            this.astronaut.makeInvisible();
            this.isInDome = true;
        } else if (this.isInDome) {
            this.astronaut.makeVisible();
            this.isInDome = false;
        }
    }

    public void moveUp() {
        if (this.isInDome) {
            return;
        }
        this.isAbleToEnterDome = false;
        // this.x < 480 || this.x > 494, this is for upper part of the map excluding the middle part
        // this.y <= 369, if this would not be there, the astronaut would never move up
        // this.y <= 321, this is for upper part of the map in the middle block
        if ((this.x < 480 || this.x > 494) && this.y <= 369) { // for correct dimensions this.x >= 480 && this.x <= 494
            return;
        } else if (this.y <= 321) {
            this.isAbleToEnterDome = true;
            return;
        }

        Optional<Block> minedBlock = GameMap.getInstance().isInBlock(this.x, this.y);
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), 2);
        } else {
            this.astronaut.moveVertical(-2);
            this.y -= 2;
        }
    }

    public void moveDown() {
        if (this.isInDome) {
            return;
        }
        this.isAbleToEnterDome = false;
        // this.y + 35 >= 702, this is for lower part of the map
        if (this.y + 35 >= 702) {
            return;
        }

        Optional<Block> minedBlock = GameMap.getInstance().isInBlock(this.x, this.y + 35);
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), 2);
        } else {
            this.astronaut.moveVertical(2);
            this.y += 2;
        }
    }

    public void moveLeft() {
        if (this.isInDome) {
            return;
        }
        this.isAbleToEnterDome = false;
        // this.x <= 48, this is for left part of the map
        // this.y < 369 && this.x <= 494, this is for upper part of the map in the middle block
        if (this.x <= 48) {
            return;
        } else if (this.y < 369 && this.x <= 494) {
            return;
        }

        Optional<Block> minedBlock = GameMap.getInstance().isInBlock(this.x, this.y);
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), 2);
        } else {
            this.astronaut.moveHorizontal(-2);
            this.x -= 2;
        }
    }

    public void moveRight() {
        if (this.isInDome) {
            return;
        }
        this.isAbleToEnterDome = false;
        // this.x + 35 >= 960, this is for right part of the map
        // this.y < 369 && this.x >= 480, this is for upper part of the map in the middle block
        if (this.x + 35 >= 960) {
            return;
        } else if (this.y < 369 && this.x >= 480) {
            return;

        }

        Optional<Block> minedBlock = GameMap.getInstance().isInBlock(this.x + 35, this.y);
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), 2);
        } else {
            this.astronaut.moveHorizontal(2);
            this.x += 2;
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
