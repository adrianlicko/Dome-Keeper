package sk.uniza.fri.player;

import fri.shapesge.Image;
import sk.uniza.fri.action.ActionMine;
import sk.uniza.fri.map.Block;
import sk.uniza.fri.map.Map;

import java.util.Optional;

public class Astronaut {
    private Image astronaut;
    private int x;
    private int y;
    private ActionMine mining;

    public Astronaut(int x, int y) {
        this.astronaut = new Image("assets/Astronaut/Astronaut dole 3.png", x, y);
        this.astronaut.makeVisible();
        this.x = x;
        this.y = y;
        this.mining = new ActionMine();
    }

    public void moveUp() {
        Optional<Block> minedBlock = Map.getInstance().isInBlock(this.x, this.y);
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), 2);
        } else {
            this.astronaut.moveVertical(-2);
            this.y -= 2;
        }
    }

    public void moveDown() {
        Optional<Block> minedBlock = Map.getInstance().isInBlock(this.x, this.y + 35);
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), 2);
        } else {
            this.astronaut.moveVertical(2);
            this.y += 2;
        }
    }

    public void moveLeft() {
        Optional<Block> minedBlock = Map.getInstance().isInBlock(this.x, this.y);
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), 2);
        } else {
            this.astronaut.moveHorizontal(-2);
            this.x -= 2;
        }
    }

    public void moveRight() {
        Optional<Block> minedBlock = Map.getInstance().isInBlock(this.x + 35, this.y);
        if (minedBlock.isPresent()) {
            this.mining.mine(minedBlock.get(), 2);
        } else {
            this.astronaut.moveHorizontal(2);
            this.x += 2;
        }
    }
}
