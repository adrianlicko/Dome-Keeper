package sk.uniza.fri.game.purchasables.specialItems;

import sk.uniza.fri.game.ImageObject;
import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.map.BlockType;

/**
 * Represents a special item that revives all blocks on the map.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class BlockReviver extends SpecialItem {

    public BlockReviver(int price) {
        super(price);
    }

    /**
     * Method that revives all blocks on the map.
     */
    @Override
    public void use() {
        Game.getInstance().getAstronaut().spawnAtSpawnpoint();
        Game.getInstance().getGameMap().createBlocks();
        this.setEquipped(true);
    }

    @Override
    public ImageObject getImage() {
        return new ImageObject("assets/items/BlockReviver small.png");
    }

    @Override
    public String getName() {
        return "Block Reviver";
    }

    @Override
    public String getDescription() {
        return "Recreate all blocks on map";
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public int getPrice() {
        return this.getAmount();
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.STONE;
    }
}
