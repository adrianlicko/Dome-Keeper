package sk.uniza.fri.map;

import fri.shapesge.Image;

public class Block {
    private Image block;
    private int health;
    private int x;
    private int y;

    public Block(BlockType block, int x, int y) {
        this.block = new Image(block.getImagePath(), x, y);
        this.block.makeVisible();
        this.health = block.getHealth();
        this.x = x;
        this.y = y;
    }

    public boolean receiveDamage(int damage) {
        this.health -= damage;
        return this.health > 0;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void makeInvisible() {
        this.block.makeInvisible();
    }
}
