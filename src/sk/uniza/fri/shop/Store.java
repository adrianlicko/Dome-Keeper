package sk.uniza.fri.shop;

import sk.uniza.fri.game.map.BlockType;
import sk.uniza.fri.game.purchasable.specialItems.HealthPotion;
import sk.uniza.fri.game.purchasable.weapons.AK47;
import sk.uniza.fri.game.purchasable.weapons.MagicWand;
import sk.uniza.fri.game.purchasable.weapons.RocketLauncher;
import sk.uniza.fri.game.purchasable.weapons.Shotgun;

import javax.swing.*;

public class Store extends JFrame {
    private StoreList storeList;

    public Store() {
        super("Dome Keeper Store");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        this.storeList = new StoreList(this);

        this.storeList.addToStore(new Shotgun(), BlockType.GOLD);
        this.storeList.addToStore(new MagicWand(), BlockType.DIAMOND);
        this.storeList.addToStore(new AK47(), BlockType.STONE);
        this.storeList.addToStore(new RocketLauncher(), BlockType.STONE);
        this.storeList.addToStore(new HealthPotion(5), BlockType.STONE);

        this.pack();
        this.setVisible(true);
    }

    public void updateCoinPanel() {
        this.storeList.updateCoinPanel();
    }
}
