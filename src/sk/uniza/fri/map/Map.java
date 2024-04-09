package sk.uniza.fri.map;

import fri.shapesge.Image;

import java.util.Optional;

public class Map {
    private static Map instance;
    private int blocksWidth;
    private int blocksHeight;
    private Block[][] blocks;
    private Image upperMapBackground;
    private Image lowerMapBackground;

    private Map() {
        this.blocksWidth = 21;
        this.blocksHeight = 9;
        this.blocks = new Block[this.blocksHeight][this.blocksWidth];

        this.upperMapBackground = new Image("assets/Podzemie/abyss background.png", 0, -100);
        this.upperMapBackground.makeVisible();
    }

    public void createBlocks() {
        int positionYFirstBlock = 270; // (750 - 432) - 48

        for (int i = 0; i < this.blocksHeight; i++) {
            positionYFirstBlock = positionYFirstBlock + 48;
            int positionXFirstBlock = -48;
            for (int j = 0; j < this.blocksWidth; j++) {
                positionXFirstBlock = positionXFirstBlock + 48;

                if (i == 0 && j == 10) {
                    continue;
                }
                this.blocks[i][j] = new Block(BlockType.STONE, positionXFirstBlock, positionYFirstBlock);
            }
        }
    }

    public Optional<Block> isInBlock(int x, int y) {
        for (int i = 0; i < this.blocksHeight; i++) {
            for (int j = 0; j < this.blocksWidth; j++) {
                if (this.blocks[i][j] == null) {
                    continue;
                }
                int blockX = this.blocks[i][j].getX();
                int blockY = this.blocks[i][j].getY();
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
                    this.blocks[i][j].makeInvisible();
                    this.blocks[i][j] = null;
                }
            }
        }
    }

    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }
}
