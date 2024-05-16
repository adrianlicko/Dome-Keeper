package sk.uniza.fri.game.purchasable;

public abstract class Item implements Purchasable {
    private boolean isEquipped;
    private boolean isPurchased;

    public Item() {
        this.isEquipped = false;
        this.isPurchased = false;
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
