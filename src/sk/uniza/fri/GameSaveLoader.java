package sk.uniza.fri;

import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.map.BlockType;
import sk.uniza.fri.game.player.Astronaut;
import sk.uniza.fri.game.player.Dome;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.Map;

/**
 * Class for loading
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class GameSaveLoader {
    private static final String SAVE_PATH = "./saves/game_save.ser";

    /**
     * Method for saving the game.
     *
     * @param game - Game object representing the game.
     * @param astronaut - Astronaut object representing the player.
     * @param dome - Dome object representing the dome.
     */
    public void saveGame(Game game, Astronaut astronaut, Dome dome) {
        GameSave gameSave = new GameSave(game.getWave(), astronaut.getInventory(), dome.getHealth());

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH))) {
            oos.writeObject(gameSave);
        } catch (IOException e) {
            System.err.println("Error saving game!");
        }
    }

    /**
     * Method for loading the game.
     *
     * @return - GameSave object representing the saved game.
     */
    public GameSave loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_PATH))) {
            return (GameSave)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading game!");
        }
        return null;
    }

    /**
     * Method for loading the inventory.
     *
     * @return - Map object representing the inventory of the player.
     */
    public Map<BlockType, Integer> loadInventory() {
        GameSave gameSave = this.loadGame();
        if (gameSave != null) {
            return gameSave.getInventory();
        }
        return null;
    }
}