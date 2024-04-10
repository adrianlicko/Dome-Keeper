package sk.uniza.fri.player;

import fri.shapesge.Image;
import sk.uniza.fri.action.ActionMine;
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
    private GameMap gameMap;
    private int x;
    private int y;
    private ActionMine mining;

    private Astronaut() {
        this.x = 450;
        this.y = 250;
        this.astronaut = new Image("assets/Astronaut/Astronaut dole 3.png", this.x, this.y);
        this.astronaut.makeVisible();
        this.mining = new ActionMine();
        this.inventory = new HashMap<>();
        for (BlockType blockType : BlockType.values()) {
            this.inventory.put(blockType, 0);
        }
    }

    public void moveUp() {
        Optional<Block> minedBlock = GameMap.getInstance().isInBlock(this.x, this.y);
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), 2);
        } else {
            this.astronaut.moveVertical(-2);
            this.y -= 2;
        }
    }

    public void moveDown() {
        Optional<Block> minedBlock = GameMap.getInstance().isInBlock(this.x, this.y + 35);
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), 2);
        } else {
            this.astronaut.moveVertical(2);
            this.y += 2;
        }
    }

    public void moveLeft() {
        Optional<Block> minedBlock = GameMap.getInstance().isInBlock(this.x, this.y);
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), 2);
        } else {
            this.astronaut.moveHorizontal(-2);
            this.x -= 2;
        }
    }

    public void moveRight() {
        Optional<Block> minedBlock = GameMap.getInstance().isInBlock(this.x + 35, this.y);
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), 2);
        } else {
            this.astronaut.moveHorizontal(2);
            this.x += 2;
        }
    }

    //return unmodifiable hashmap of inventory
    public Map<BlockType, Integer> getInventory() {
        return Collections.unmodifiableMap(this.inventory);
    }

    //add block to inventory
    public void addToInventory(BlockType blockType) {
        this.inventory.replace(blockType, this.inventory.get(blockType) + blockType.getCoinValue());
    }

    public static Astronaut getInstance() {
        if (instance == null) {
            instance = new Astronaut();
        }
        return instance;
    }
}
