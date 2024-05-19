package sk.uniza.fri.game.purchasables.specialItems;

import sk.uniza.fri.game.purchasables.Item;

/**
 * Abstract class that represents a special item.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public abstract class SpecialItem extends Item {
    private final int amount;

    public SpecialItem(int amount) {
        this.amount = amount;
    }

    public abstract void use();

    public int getAmount() {
        return this.amount;
    }
}
