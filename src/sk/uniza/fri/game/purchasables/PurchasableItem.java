package sk.uniza.fri.game.purchasables;

import sk.uniza.fri.game.ImageObject;
import sk.uniza.fri.game.map.BlockType;

/**
 * Represents an item that can be equipped and purchased.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public interface PurchasableItem {

    boolean isEquipped();
    void setEquipped(boolean equipped);
    boolean isPurchased();
    void setPurchased(boolean purchased);
    ImageObject getImage();
    String getName();
    String getDescription();
    int getDamage();
    int getPrice();
    BlockType getBlockType();
}
