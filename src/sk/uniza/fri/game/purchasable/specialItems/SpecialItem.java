package sk.uniza.fri.game.purchasable.specialItems;

import sk.uniza.fri.game.purchasable.Item;

/**
 * Abstract class that represents a special item.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
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
