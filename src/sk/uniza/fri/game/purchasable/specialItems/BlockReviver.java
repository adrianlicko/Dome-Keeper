package sk.uniza.fri.game.purchasable.specialItems;

import sk.uniza.fri.ImageObject;
import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.map.BlockType;

public class BlockReviver extends SpecialItem {

    public BlockReviver(int price) {
        super(price);
    }

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
