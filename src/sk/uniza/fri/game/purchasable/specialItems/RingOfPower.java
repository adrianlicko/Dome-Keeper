package sk.uniza.fri.game.purchasable.specialItems;

import sk.uniza.fri.ImageObject;
import sk.uniza.fri.game.map.BlockType;
import sk.uniza.fri.game.player.Astronaut;

public class RingOfPower extends SpecialItem {

    public RingOfPower(int amount) {
        super(amount);
    }

    @Override
    public void use() {
        Astronaut.getInstance().setDamage(this.getAmount());
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
        return 0;
    }

    @Override
    public int getPrice() {
        return 10;
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.GOLD;
    }
}
