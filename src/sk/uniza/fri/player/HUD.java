package sk.uniza.fri.player;

import fri.shapesge.FontStyle;
import fri.shapesge.Image;
import fri.shapesge.Rectangle;
import fri.shapesge.TextBlock;
import sk.uniza.fri.map.Block;
import sk.uniza.fri.map.BlockType;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HUD {
    private static HUD instance;
    private Rectangle hudBackground;
    private Map<BlockType, Image> coinImages;
    private Map<BlockType, TextBlock> coinText;

    private HUD() {
        this.hudBackground = new Rectangle(0, 715);
        this.hudBackground.changeSize(1008, 35);
        this.hudBackground.changeColor("black");
        this.hudBackground.makeVisible();

        this.coinText = new HashMap<>();
        this.coinImages = new HashMap<>();
        for (BlockType blockType : BlockType.values()) {
            this.coinText.put(blockType, new TextBlock("" + Astronaut.getInstance().getInventory().get(blockType), 30 + blockType.ordinal() * 50, 720));
            this.coinText.get(blockType).changeFont("Arial", FontStyle.BOLD, 40);
            this.coinText.get(blockType).changeColor("white");
            this.coinText.get(blockType).makeVisible();
            //this.coinImages.put(blockType, new Image(blockType.getImagePath(), 10 + blockType.ordinal() * 50, 720));
        }
    }

    public void updateHudOfPlayersCoin(BlockType blockType) {
        this.coinText.get(blockType).changeText("" + (Astronaut.getInstance().getInventory().get(blockType)));
    }

    public static HUD getInstance () {
        if (instance == null) {
            instance = new HUD();
        }
        return instance;
    }
}
