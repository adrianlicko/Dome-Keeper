package sk.uniza.fri.game.purchasables.specialItems;

import sk.uniza.fri.game.ImageObject;
import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.map.BlockType;

/**
 * Represents a special item that gives the player a better movement speed.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class EnchancedJetpack extends SpecialItem {

    public EnchancedJetpack(int amount) {
        super(amount);
    }

    /**
     * Method that gives the player a better movement speed.
     */
    @Override
    public void use() {
        Game.getInstance().getAstronaut().setMovementSpeed(this.getAmount());
        this.setEquipped(true);
    }

    @Override
    public ImageObject getImage() {
        return new ImageObject("assets/items/EnchancedJetpack small.png");
    }

    @Override
    public String getName() {
        return "Enchanced Jetpack";
    }

    @Override
    public String getDescription() {
        return "Gives you a better movement speed between blocks";
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public int getPrice() {
        return 2;
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.DIAMOND;
    }
}
