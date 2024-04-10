package sk.uniza.fri.action;

import sk.uniza.fri.map.Block;
import sk.uniza.fri.map.BlockType;
import sk.uniza.fri.map.GameMap;
import sk.uniza.fri.player.Astronaut;
import sk.uniza.fri.player.HUD;

public class ActionMine {
    public void mine(Block minedBlock, int damage) {
        if (!minedBlock.receiveDamage(damage)) {
            GameMap.getInstance().breakBlock(minedBlock);
            Astronaut.getInstance().addToInventory(minedBlock.getType());
            HUD.getInstance().updateHudOfPlayersCoin(minedBlock.getType());
        }
    }
}
