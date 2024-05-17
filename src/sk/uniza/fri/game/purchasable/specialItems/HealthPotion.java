package sk.uniza.fri.game.purchasable.specialItems;

import sk.uniza.fri.ImageObject;
import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.map.BlockType;

public class HealthPotion extends SpecialItem {
    public HealthPotion(int amount) {
        super(amount);
    }

    @Override
    public void use() {
        Game.getInstance().getHUD().increaseDomeHealth(this.getAmount());
        this.setEquipped(true);
    }

    @Override
    public ImageObject getImage() {
        return new ImageObject("assets/items/HealthPotion small.png");
    }

    @Override
    public String getName() {
        return "Health Potion";
    }

    @Override
    public String getDescription() {
        return "Heals you by " + this.getAmount() + " hp";
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public int getPrice() {
        return 5 * this.getAmount();
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.STONE;
    }
}
