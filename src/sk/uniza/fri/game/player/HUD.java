package sk.uniza.fri.game.player;

import fri.shapesge.FontStyle;
import fri.shapesge.Image;
import fri.shapesge.Rectangle;
import fri.shapesge.TextBlock;
import sk.uniza.fri.game.map.BlockType;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents the HUD.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class HUD {
    private final Astronaut astronaut;
    private final Dome dome;
    private final Map<BlockType, TextBlock> coinText;
    private final Rectangle domeHealthIndicator;
    private final TextBlock domeHealthText;

    /**
     * Constructor for the HUD class.
     * When the HUD instance is created, it creates the background of the HUD, the inventory of the player and the dome health indicator.
     *
     * @param astronaut - Astronaut object representing the player.
     * @param dome - Dome object representing the dome.
     */
    public HUD(Astronaut astronaut, Dome dome) {
        this.astronaut = astronaut;
        this.dome = dome;

        // HUD background
        Rectangle hudBackground = new Rectangle(0, 715);
        hudBackground.changeSize(1008, 35);
        hudBackground.changeColor("black");
        hudBackground.makeVisible();

        // Astronaut inventory
        this.coinText = new HashMap<>();
        Map<BlockType, Image> coinImages = new HashMap<>();
        for (BlockType blockType : BlockType.values()) {
            this.coinText.put(blockType, new TextBlock("" + this.astronaut.getInventory().get(blockType), 950 - (blockType.ordinal() * 100), 742));
            this.coinText.get(blockType).changeFont("Arial", FontStyle.BOLD, 30);
            this.coinText.get(blockType).changeColor("white");
            this.coinText.get(blockType).makeVisible();

            coinImages.put(blockType, new Image(blockType.getCoinImagePath(), 920 - (blockType.ordinal() * 100), 712));
            coinImages.get(blockType).makeVisible();
        }

        // Dome health indicator
        Rectangle domeHealthIndicatorBackground = new Rectangle(15, 721);
        domeHealthIndicatorBackground.changeSize(100, 22);
        domeHealthIndicatorBackground.changeColor("white");
        domeHealthIndicatorBackground.makeVisible();

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
     * This method updates the HUD of the player with the amount of coins he has of the specific block type.
     *
     * @param blockType - BlockType value representing the type of the block.
     */
    public void updateHudOfPlayerCoin(BlockType blockType) {
        this.coinText.get(blockType).changeText("" + (this.astronaut.getInventory().get(blockType)));
    }

    /**
     * This method updates the HUD of the player with the amount of coins he has of all block types.
     */
    public void updateHudOfAllPlayerCoins() {
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

    /**
     * This method increases the health of the dome by the amount.
     *
     * @param amount - Integer value representing the amount of health that the dome receives.
     */
    public void increaseDomeHealth(int amount) {
        this.dome.increaseHealth(amount);
        this.updateDomeHealth();
    }
}
