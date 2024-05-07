package sk.uniza.fri.game.action.player;

import sk.uniza.fri.game.map.Block;
import sk.uniza.fri.game.map.GameMap;
import sk.uniza.fri.game.player.Astronaut;
import sk.uniza.fri.game.player.HUD;

public class ActionMine {
    public void mine(Block minedBlock, int damage) {
        if (!minedBlock.receiveDamage(damage)) {
            GameMap.getInstance().breakBlock(minedBlock);
            Astronaut.getInstance().addToInventory(minedBlock.getType());
            HUD.getInstance().updateHudOfPlayersCoin(minedBlock.getType());
        }
    }
}
