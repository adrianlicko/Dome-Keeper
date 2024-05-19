package sk.uniza.fri;

import fri.shapesge.Manager;
import sk.uniza.fri.game.Game;
import sk.uniza.fri.shop.Store;

import javax.swing.JFrame;
import java.awt.Window;

public class Menu {
    private static Menu instance;
    private JFrame gameFrame;
    private Store store;
    private boolean alreadyHaveFrame;
    private Manager manager;

    private Menu() {

    }

    public void startGame() {
        if (this.manager == null) {
            this.manager = Game.getInstance().getManager();
            this.manager.manageObject(Game.getInstance());
        }

        Game.getInstance().manageObjects();
        Game.getInstance().getHUD().updateHudOfAllPlayerCoins();

        if (!this.alreadyHaveFrame) {
            for (Window window : Window.getWindows()) {
                if (window instanceof JFrame frame) {
                    this.gameFrame = frame;
                    this.alreadyHaveFrame = true;
                }
            }
        }

        this.gameFrame.setVisible(true);
    }

    public void openShop() {
        Game.getInstance().stopManagingObjects();
        this.gameFrame.setVisible(false);
        if (this.store == null) {
            this.store = new Store();
        } else {
            this.store.setVisible(true);
        }
        this.store.updateCoinPanel();
    }

    public void closeShop() {
        this.store.setVisible(false);
        this.store.dispose();
        this.startGame();
    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }
}
