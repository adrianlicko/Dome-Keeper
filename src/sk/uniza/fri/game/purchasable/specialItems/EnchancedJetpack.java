package sk.uniza.fri.game.purchasable.specialItems;

import sk.uniza.fri.ImageObject;
import sk.uniza.fri.game.player.Astronaut;

public class EnchancedJetpack extends SpecialItem {

    public EnchancedJetpack(int amount) {
        super(amount);
    }

    @Override
    public void use() {
        Astronaut.getInstance().setMovementSpeed(this.getAmount());
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
        return 10;
    }
}
