package sk.uniza.fri.game.map;

import sk.uniza.fri.ImageObject;

public class Block {
    private ImageObject blockImage;
    private BlockType blockType;
    private int health;

    public Block(BlockType block, int x, int y) {
        this.blockImage = new ImageObject(block.getImagePath(), x, y);
        this.blockImage.makeVisible();
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

    public ImageObject getBlockImage() {
        return this.blockImage;
    }
}
