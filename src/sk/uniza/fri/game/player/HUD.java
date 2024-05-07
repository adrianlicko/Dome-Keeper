package sk.uniza.fri.game.player;

import fri.shapesge.FontStyle;
import fri.shapesge.Image;
import fri.shapesge.Rectangle;
import fri.shapesge.TextBlock;
import sk.uniza.fri.game.map.BlockType;

import java.util.HashMap;
import java.util.Map;

public class HUD {
    private static HUD instance;
    private Rectangle hudBackground;
    private Map<BlockType, Image> coinImages;
    private Map<BlockType, TextBlock> coinText;
    private Rectangle domeHealthIndicatorBackground;
    private Rectangle domeHealthIndicator;
    private TextBlock domeHealthText;

    private HUD() {
        this.hudBackground = new Rectangle(0, 715);
        this.hudBackground.changeSize(1008, 35);
        this.hudBackground.changeColor("black");
        this.hudBackground.makeVisible();

        // Astronaut inventory
        this.coinText = new HashMap<>();
        this.coinImages = new HashMap<>();
        for (BlockType blockType : BlockType.values()) {
            this.coinText.put(blockType, new TextBlock("" + Astronaut.getInstance().getInventory().get(blockType), 950 - (blockType.ordinal() * 100), 742));
            this.coinText.get(blockType).changeFont("Arial", FontStyle.BOLD, 30);
            this.coinText.get(blockType).changeColor("white");
            this.coinText.get(blockType).makeVisible();

            this.coinImages.put(blockType, new Image(blockType.getCoinImagePath(), 920 - (blockType.ordinal() * 100), 712));
            this.coinImages.get(blockType).makeVisible();
        }

        // Dome health indicator
        this.domeHealthIndicatorBackground = new Rectangle(15, 721);
        this.domeHealthIndicatorBackground.changeSize(100, 22);
        this.domeHealthIndicatorBackground.changeColor("white");
        this.domeHealthIndicatorBackground.makeVisible();

        this.domeHealthIndicator = new Rectangle(19, 724);
        this.domeHealthIndicator.changeSize(92, 16);
        this.domeHealthIndicator.changeColor("red");
        this.domeHealthIndicator.makeVisible();

        // Dome health text
        this.domeHealthText = new TextBlock((int)Dome.getInstance().getHealth() + "/" + (int)Dome.getInstance().getInitialHealth(), 40, 739);
        this.domeHealthText.changeColor("black");
        this.domeHealthText.changeFont("Arial", FontStyle.BOLD, 20);
        this.domeHealthText.makeVisible();
    }

    /**
     * This method updates the HUD of the player with the amount of coins he has.
     */
    public void updateHudOfPlayersCoin(BlockType blockType) {
        this.coinText.get(blockType).changeText("" + (Astronaut.getInstance().getInventory().get(blockType)));
    }

    /**
     * This method updates the dome health indicator.
     */
    public void updateDomeHealth() {
        this.domeHealthIndicator.changeSize((int)(92 * (Dome.getInstance().getHealth() / Dome.getInstance().getInitialHealth())), 16);
        this.domeHealthText.changeText((int)Dome.getInstance().getHealth() + "/" + (int)Dome.getInstance().getInitialHealth());
    }

    public static HUD getInstance() {
        if (instance == null) {
            instance = new HUD();
        }
        return instance;
    }
}
