package sk.uniza.fri.game.action.player;

import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.map.Block;

public class ActionMine {
    public static void mine(Block minedBlock, int damage) {
        if (!minedBlock.receiveDamage(damage)) {
            Game.getInstance().getGameMap().breakBlock(minedBlock);
            Game.getInstance().getAstronaut().addCoinToInventory(minedBlock.getType());
            Game.getInstance().getHUD().updateHudOfPlayersCoin(minedBlock.getType());
        }
    }
}
