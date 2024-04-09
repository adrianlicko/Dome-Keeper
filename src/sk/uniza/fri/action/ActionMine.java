package sk.uniza.fri.action;

import sk.uniza.fri.map.Block;
import sk.uniza.fri.map.Map;

public class ActionMine {
    public void mine(Block minedBlock, int damage) {
        if (!minedBlock.receiveDamage(damage)) {
            Map.getInstance().breakBlock(minedBlock);
        }
    }
}
