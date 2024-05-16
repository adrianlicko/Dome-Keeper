package sk.uniza.fri.game.map;

import sk.uniza.fri.ImageLoader;
import sk.uniza.fri.ImageObject;

import java.util.Timer;
import java.util.TimerTask;

public class Block {
    private ImageObject blockImage;
    private BlockType blockType;
    private int health;
    private ImageLoader imageLoader;
    private ImageObject miningImage;
    private Timer timer;
    private TimerTask currentTask;

    public Block(BlockType block, int x, int y) {
        this.blockImage = new ImageObject(block.getImagePath(), x, y);
        this.blockImage.makeVisible();
        this.blockType = block;
        this.health = block.getHealth();
        this.imageLoader = new ImageLoader("assets/Podzemie/Block mining animation");
        this.miningImage = new ImageObject(this.imageLoader.getNextImage(), x, y);
        this.timer = new Timer();
    }


    public boolean receiveDamage(int damage) {
        this.health -= damage;
        this.miningImage.makeInvisible();

        if (this.health > 0) {
            this.miningImage = new ImageObject(this.imageLoader.getNextImage(), this.blockImage.getX(), this.blockImage.getY());
            this.miningImage.makeVisible();
        }

        if (this.currentTask != null) {
            this.currentTask.cancel();
        }

        this.currentTask = new TimerTask() {
            @Override
            public void run() {
                Block.this.miningImage.makeInvisible();
            }
        };

        this.timer.schedule(this.currentTask, 500); // 0.5 seconds

        return this.health > 0;
    }

    public BlockType getType() {
        return this.blockType;
    }

    public ImageObject getBlockImage() {
        return this.blockImage;
    }
}
