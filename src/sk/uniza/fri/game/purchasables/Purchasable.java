package sk.uniza.fri.game.purchasables;

import sk.uniza.fri.game.ImageObject;
import sk.uniza.fri.game.map.BlockType;

/**
 * Represents an item that can be purchased in store.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public interface Purchasable {
    ImageObject getImage();
    String getName();
    String getDescription();
    int getDamage();
    int getPrice();
    boolean isEquipped();
    boolean isPurchased();
    void setPurchased(boolean purchased);
    BlockType getBlockType();
}
