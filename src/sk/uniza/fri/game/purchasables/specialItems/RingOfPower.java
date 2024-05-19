package sk.uniza.fri.game.purchasables.specialItems;

import sk.uniza.fri.game.ImageObject;
import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.map.BlockType;

/**
 * Represents a special item that gives the player a bigger power over blocks.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class RingOfPower extends SpecialItem {

    public RingOfPower(int amount) {
        super(amount);
    }

    /**
     * Method that gives the player a bigger power over blocks.
     */
    @Override
    public void use() {
        Game.getInstance().getAstronaut().setDamage(this.getAmount());
        this.setEquipped(true);
    }

    @Override
    public ImageObject getImage() {
        return new ImageObject("assets/items/RingOfPower small.png");
    }

    @Override
    public String getName() {
        return "Ring Of Power";
    }

    @Override
    public String getDescription() {
        return "Gives you bigger power over blocks";
    }

    @Override
    public int getDamage() {
        return this.getAmount();
    }

    @Override
    public int getPrice() {
        return this.getAmount() * 2;
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.GOLD;
    }
}
