package sk.uniza.fri.shop;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import sk.uniza.fri.game.purchasables.weapons.AK47;
import sk.uniza.fri.game.purchasables.weapons.MagicWand;
import sk.uniza.fri.game.purchasables.weapons.RocketLauncher;
import sk.uniza.fri.game.purchasables.weapons.Shotgun;

import sk.uniza.fri.game.purchasables.specialItems.BlockReviver;
import sk.uniza.fri.game.purchasables.specialItems.EnchancedJetpack;
import sk.uniza.fri.game.purchasables.specialItems.HealthPotion;
import sk.uniza.fri.game.purchasables.specialItems.HealingRing;
import sk.uniza.fri.game.purchasables.specialItems.RingOfPower;


/**
 * Represents the store where the player can buy items.
 * The store contains a list of items that can be purchased.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class Store extends JFrame {
    private final StoreList storeList;

    /**
     * Creates a new store window and initializes the store list.
     */
    public Store() {
        super("Dome Keeper Store");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("Error setting the look and feel of the store window.");
        }

        this.storeList = new StoreList(this);

        this.storeList.addToStore(new AK47());
        this.storeList.addToStore(new Shotgun());
        this.storeList.addToStore(new MagicWand());
        this.storeList.addToStore(new RocketLauncher());
        this.storeList.addToStore(new HealthPotion(5));
        this.storeList.addToStore(new HealthPotion(10));
        this.storeList.addToStore(new HealingRing(1, 20, 5));
        this.storeList.addToStore(new HealingRing(2, 12, 4));
        this.storeList.addToStore(new RingOfPower(2));
        this.storeList.addToStore(new RingOfPower(3));
        this.storeList.addToStore(new EnchancedJetpack(4));
        this.storeList.addToStore(new BlockReviver(10));
        this.storeList.addToStore(new BlockReviver(20));

        this.pack();
        this.setVisible(true);
    }

    /**
     * Updates the coin panel in the store.
     */
    public void updateCoinPanel() {
        this.storeList.updateCoinPanel();
    }
}
