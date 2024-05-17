package sk.uniza.fri.game.player;

import fri.shapesge.FontStyle;
import fri.shapesge.Image;
import fri.shapesge.Rectangle;
import fri.shapesge.TextBlock;
import sk.uniza.fri.game.map.BlockType;

import java.util.HashMap;
import java.util.Map;

public class HUD {
    private Astronaut astronaut;
    private Dome dome;
    private Rectangle hudBackground;
    private Map<BlockType, Image> coinImages;
    private Map<BlockType, TextBlock> coinText;
    private Rectangle domeHealthIndicatorBackground;
    private Rectangle domeHealthIndicator;
    private TextBlock domeHealthText;

    public HUD(Astronaut astronaut, Dome dome) {
        this.astronaut = astronaut;
        this.dome = dome;

        this.hudBackground = new Rectangle(0, 715);
        this.hudBackground.changeSize(1008, 35);
        this.hudBackground.changeColor("black");
        this.hudBackground.makeVisible();

        // Astronaut inventory
        this.coinText = new HashMap<>();
        this.coinImages = new HashMap<>();
        for (BlockType blockType : BlockType.values()) {
            this.coinText.put(blockType, new TextBlock("" + this.astronaut.getInventory().get(blockType), 950 - (blockType.ordinal() * 100), 742));
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
        this.domeHealthText = new TextBlock((int)this.dome.getHealth() + "/" + (int)this.dome.getInitialHealth(), 40, 739);
        this.domeHealthText.changeColor("black");
        this.domeHealthText.changeFont("Arial", FontStyle.BOLD, 20);
        this.domeHealthText.makeVisible();
    }

    /**
     * This method updates the HUD of the player with the amount of coins he has.
     */
    public void updateHudOfPlayersCoin(BlockType blockType) {
        this.coinText.get(blockType).changeText("" + (this.astronaut.getInventory().get(blockType)));
    }

    public void refreshHudOfPlayersCoin() {
        for (BlockType blockType : BlockType.values()) {
            this.coinText.get(blockType).changeText("" + (this.astronaut.getInventory().get(blockType)));
        }
    }

    /**
     * This method updates the dome health indicator.
     */
    public void updateDomeHealth() {
        this.domeHealthIndicator.changeSize((int)(92 * (this.dome.getHealth() / this.dome.getInitialHealth())), 16);
        this.domeHealthText.changeText((int)this.dome.getHealth() + "/" + (int)this.dome.getInitialHealth());
    }

    public void increaseDomeHealth(int amount) {
        this.dome.increaseHealth(amount);
        this.updateDomeHealth();
    }
}
