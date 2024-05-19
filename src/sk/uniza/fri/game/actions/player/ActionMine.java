package sk.uniza.fri.game.actions.player;

import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.map.Block;

/**
 * Class for mining blocks.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class ActionMine {

    /**
     * Method mine provides mining of blocks and adding coins to the inventory of the player.
     *
     * @param minedBlock
     * @param damage
     */
    public static void mine(Block minedBlock, int damage) {
        if (!minedBlock.receiveDamage(damage)) {
            Game.getInstance().getGameMap().breakBlock(minedBlock);
            Game.getInstance().getAstronaut().addCoinToInventory(minedBlock.getType());
            Game.getInstance().getHUD().updateHudOfPlayerCoin(minedBlock.getType());
        }
    }
}
