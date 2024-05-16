package sk.uniza.fri.game.purchasable.specialItems;

import sk.uniza.fri.game.purchasable.Item;
import sk.uniza.fri.game.purchasable.Purchasable;

public abstract class SpecialItem extends Item {
    private int amount;

    public SpecialItem(int amount) {
        this.amount = amount;
    }

    public abstract void use();

    public int getAmount() {
        return this.amount;
    }
}
