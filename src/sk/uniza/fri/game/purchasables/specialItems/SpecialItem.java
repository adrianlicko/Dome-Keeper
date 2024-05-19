package sk.uniza.fri.game.purchasables.specialItems;

import sk.uniza.fri.game.purchasables.PurchasableItem;

/**
 * Abstract class that represents a special item.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public abstract class SpecialItem implements PurchasableItem {
    private final int amount;
    private boolean isEquipped;
    private boolean isPurchased;

    public SpecialItem(int amount) {
        this.amount = amount;
        this.isEquipped = false;
        this.isPurchased = false;
    }

    public abstract void use();

    public int getAmount() {
        return this.amount;
    }

    public boolean isEquipped() {
        return this.isEquipped;
    }

    public void setEquipped(boolean equipped) {
        this.isEquipped = equipped;
    }

    public boolean isPurchased() {
        return this.isPurchased;
    }

    public void setPurchased(boolean purchased) {
        this.isPurchased = purchased;
    }
}
