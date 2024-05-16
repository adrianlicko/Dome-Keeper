package sk.uniza.fri.game.purchasable;

import sk.uniza.fri.ImageObject;

public interface Purchasable {
    ImageObject getImage();
    String getName();
    String getDescription();
    int getDamage();
    int getPrice();
    boolean isEquipped();
    boolean isPurchased();
    void setPurchased(boolean purchased);
}
