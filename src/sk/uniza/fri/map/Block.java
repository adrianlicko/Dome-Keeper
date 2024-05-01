package sk.uniza.fri.map;

import fri.shapesge.Image;
import sk.uniza.fri.ImageObject;

public class Block extends ImageObject {
    private BlockType blockType;
    private int health;

    public Block(BlockType block, int x, int y) {
        super(block.getImagePath(), x, y);
        super.makeVisible();
        this.blockType = block;
        this.health = block.getHealth();
    }

    public boolean receiveDamage(int damage) {
        this.health -= damage;
        return this.health > 0;
    }

    public BlockType getType() {
        return this.blockType;
    }
}
