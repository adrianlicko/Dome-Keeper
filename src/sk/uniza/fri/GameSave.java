package sk.uniza.fri;

import sk.uniza.fri.game.map.BlockType;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * Class for saving the game.
 *
 * @author Adrian Licko, Copilot
 * @version 1.0
 * @since 1.0
 */
public class GameSave implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final int wave;
    private final Map<BlockType, Integer> inventory;
    private final double health;

    /**
     * Constructor for the GameSave class.
     *
     * @param wave - wave number.
     * @param inventory - inventory of the player.
     * @param health - health of the player.
     */
    public GameSave(int wave, Map<BlockType, Integer> inventory, double health) {
        this.wave = wave;
        this.inventory = inventory;
        this.health = health;
    }

    public int getWave() {
        return this.wave;
    }

    public Map<BlockType, Integer> getInventory() {
        return this.inventory;
    }

    public double getHealth() {
        return this.health;
    }
}