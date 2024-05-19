package sk.uniza.fri;

import fri.shapesge.Manager;
import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.ImageObject;
import sk.uniza.fri.game.player.Astronaut;
import sk.uniza.fri.game.player.Dome;

/**
 * Class that represents the menu window.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class MenuWindow {
    private final ImageObject background;
    private final ImageObject playButton;
    private final ImageObject saveButton;
    private final ImageObject loadButton;
    private final ImageObject storeButton;
    private final Manager manager;

    /**
     * Constructor for the MenuWindow class.
     * @param manager - Manager object representing the manager of the game.
     */
    public MenuWindow(Manager manager) {
        this.manager = manager;

        this.background = new ImageObject("assets/backups/Dome Keeper background.jpg");

        this.playButton = new ImageObject("assets/backups/Play button small.png");
        this.playButton.changePosition(390, 0);

        this.saveButton = new ImageObject("assets/backups/Save button small.png");
        this.saveButton.changePosition(190, 660);

        this.loadButton = new ImageObject("assets/backups/Load button small.png");
        this.loadButton.changePosition(590, 660);

        this.storeButton = new ImageObject("assets/backups/Store button small.png");
        this.storeButton.changePosition(850, 660);
    }

    public void show() {
        this.background.makeVisible();
        this.playButton.makeVisible();
        this.saveButton.makeVisible();
        this.loadButton.makeVisible();
        this.storeButton.makeVisible();
        this.manager.manageObject(this);
    }

    public void hide() {
        this.background.makeInvisible();
        this.playButton.makeInvisible();
        this.saveButton.makeInvisible();
        this.loadButton.makeInvisible();
        this.storeButton.makeInvisible();
        this.manager.stopManagingObject(this);
    }

    /**
     * Method that handles the mouse click on the menu window.
     * This method is handled by the manager.
     *
     * @param x - x position of the mouse click
     * @param y - y position of the mouse click
     */
    public void menuWindowMouseClick(int x, int y) {
        if (x > 390 && x < 640 && y > 0 && y < 87) {
            // start game
            this.hide();
            Menu.getInstance().startGame();

        } else if (x > 190 && x < 440 && y > 660 && y < 749) {
            //save
            GameSaveLoader gameSaveLoader = new GameSaveLoader();
            Game game = Game.getInstance();
            Astronaut astronaut = game.getAstronaut();
            Dome dome = game.getDome();
            gameSaveLoader.saveGame(game, astronaut, dome);
            System.out.println("Game saved!");

        } else if (x > 590 && x < 840 && y > 660 && y < 749) {
            //load
            GameSaveLoader gameSaveLoader = new GameSaveLoader();
            Game game = Game.getInstance();
            Astronaut astronaut = game.getAstronaut();
            Dome dome = game.getDome();
            GameSave gameSave = gameSaveLoader.loadGame();
            if (gameSave != null) {
                game.setWave(gameSave.getWave());
                astronaut.loadInventoryFromSave(gameSaveLoader);
                dome.setHealth(gameSave.getHealth());
            }
            System.out.println("Game loaded!");
            Menu.getInstance().startGame();
            Game.getInstance().getHUD().updateDomeHealth();
            this.hide();

        } else if (x > 850 && x < 1000 && y > 660 && y < 739) {
            // open shop
            this.hide();
            Menu.getInstance().openShop();
        }
    }
}
