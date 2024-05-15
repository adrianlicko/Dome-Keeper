package sk.uniza.fri.shop;

import sk.uniza.fri.game.weapons.player.MagicWand;
import sk.uniza.fri.game.weapons.player.Shotgun;

import javax.swing.*;

public class Store extends JFrame {
    private StoreList storeList;

    public Store() {
        super("Dome Keeper Store");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.storeList = new StoreList(this);

        this.storeList.addToStore(new Shotgun());
        this.storeList.addToStore(new MagicWand());

        this.setVisible(true);
        this.pack();
    }
}
