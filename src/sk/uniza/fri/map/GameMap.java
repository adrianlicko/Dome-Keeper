package sk.uniza.fri.map;

import fri.shapesge.Image;

import java.util.Optional;
import java.util.Random;

public class GameMap {
    private static GameMap instance;
    private int blocksWidth;
    private int blocksHeight;
    private Block[][] blocks;
    private Image upperMapBackground;
    private Image lowerMapBackground;

    private GameMap() {
        this.blocksWidth = 21;
        this.blocksHeight = 9;
        this.blocks = new Block[this.blocksHeight][this.blocksWidth];

        this.upperMapBackground = new Image("assets/Podzemie/abyss background.png", 0, -100);
        this.upperMapBackground.makeVisible();

        this.lowerMapBackground = new Image("assets/Podzemie/Underground background.png", 0, 318);
        this.lowerMapBackground.makeVisible();
    }

    /**
     * Create blocks for the game map.
     */
    public void createBlocks() {
        int positionYFirstBlock = 318; // (height of the map) - ((size of block) * (number of blocks in height)), 750 - 432

        for (int i = 1; i < this.blocksHeight - 1; i++) {
            positionYFirstBlock = positionYFirstBlock + 48;
            int positionXFirstBlock = 0;
            for (int j = 1; j < this.blocksWidth - 1; j++) {
                positionXFirstBlock = positionXFirstBlock + 48;

                Random random = new Random();
                int randomInt = random.nextInt(12);
                if (randomInt == 0 || randomInt == 1) {
                    this.blocks[i][j] = new Block(BlockType.GOLD, positionXFirstBlock, positionYFirstBlock);
                    continue;
                } else if (randomInt == 2) {
                    this.blocks[i][j] = new Block(BlockType.DIAMOND, positionXFirstBlock, positionYFirstBlock);
                    continue;
                }
                this.blocks[i][j] = new Block(BlockType.STONE, positionXFirstBlock, positionYFirstBlock);
            }
        }
    }

    /**
     * Checks if the player (given position) is in the block.
     */
    public Optional<Block> isInBlock(int x, int y) {
        for (int i = 0; i < this.blocksHeight; i++) {
            for (int j = 0; j < this.blocksWidth; j++) {
                if (this.blocks[i][j] == null) {
                    continue;
                }
                int blockX = this.blocks[i][j].getBlockImage().getX();
                int blockY = this.blocks[i][j].getBlockImage().getY();
                if (x >= blockX && x <= blockX + 48 && y >= blockY && y <= blockY + 48) {
                    return Optional.of(this.blocks[i][j]);
                }
            }
        }
        return Optional.empty();
    }

    public void breakBlock(Block block) {
        for (int i = 1; i < this.blocksHeight - 1; i++) {
            for (int j = 1; j < this.blocksWidth - 1; j++) {
                if (this.blocks[i][j] == null) {
                    continue;
                }
                if (this.blocks[i][j] == block) {
                    this.blocks[i][j].getBlockImage().makeInvisible();
                    this.blocks[i][j] = null;
                }
            }
        }
    }

    public static GameMap getInstance() {
        if (instance == null) {
            instance = new GameMap();
        }
        return instance;
    }
}
