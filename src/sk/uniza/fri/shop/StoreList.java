package sk.uniza.fri.shop;

import sk.uniza.fri.Menu;
import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.map.BlockType;
import sk.uniza.fri.game.purchasables.Purchasable;
import sk.uniza.fri.game.purchasables.specialItems.SpecialItem;
import sk.uniza.fri.game.purchasables.weapons.Weapon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Represents the store list where the player can buy items.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class StoreList {
    private JFrame frame;
    private JButton backToTheGameButton;
    private JPanel mainPanel;
    private JPanel storePanel;
    private JPanel coinPanel;

    /**
     * Creates a new store list and initializes the main panel.
     *
     * @param frame - the frame where the store list will be displayed
     */
    public StoreList(JFrame frame) {
        this.frame = frame;
        this.storePanel.setLayout(new GridLayout(0, 3)); // Zmena na FlowLayout
        this.coinPanel.setLayout(new FlowLayout());

        this.frame.add(this.mainPanel);

        this.backToTheGameButton.setBackground(new Color(93, 95, 87));
        this.backToTheGameButton.setForeground(new Color(255, 255, 255));
        this.backToTheGameButton.setFont(new Font("Sans-serif", Font.BOLD, 12));
        this.backToTheGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.getInstance().closeShop();
            }
        });

        this.updateCoinPanel();
    }

    /**
     * Updates the coin panel in the store.
     */
    public void updateCoinPanel() {
        this.coinPanel.removeAll(); // remove all existing panels

        Map<BlockType, Integer> inventory = Game.getInstance().getAstronaut().getInventory();
        for (Map.Entry<BlockType, Integer> entry : inventory.entrySet()) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));

            JLabel coinImageLabel = new JLabel();
            coinImageLabel.setIcon(new ImageIcon(entry.getKey().getCoinImagePath()));
            itemPanel.add(coinImageLabel);

            JLabel coinCountLabel = new JLabel(String.valueOf(entry.getValue()));
            coinCountLabel.setFont(new Font(coinCountLabel.getFont().getName(), Font.BOLD, coinCountLabel.getFont().getSize()));
            itemPanel.add(coinCountLabel);

            this.coinPanel.add(itemPanel);
        }

        this.coinPanel.repaint();
        this.coinPanel.revalidate();
    }

    /**
     * Adds a purchasable item to the store with all necessary information and buttons.
     *
     * @param item - the item to be added to the store
     */
    public void addToStore(Purchasable item) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));



        

        JLabel imageLabel = new JLabel(new ImageIcon(item.getImage().getImagePath()));
        imageLabel.setFont(new Font(imageLabel.getFont().getName(), Font.BOLD, imageLabel.getFont().getSize()));

        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setFont(new Font(nameLabel.getFont().getName(), Font.BOLD, nameLabel.getFont().getSize()));

        JLabel descriptionLabel = new JLabel(item.getDescription());
        descriptionLabel.setFont(new Font(descriptionLabel.getFont().getName(), Font.BOLD, descriptionLabel.getFont().getSize()));

        JLabel damageLabel = new JLabel("Damage: " + item.getDamage()); // for weapons
        damageLabel.setFont(new Font(damageLabel.getFont().getName(), Font.BOLD, damageLabel.getFont().getSize()));

        JLabel priceLabel = new JLabel("Price: " + item.getPrice());
        priceLabel.setFont(new Font(priceLabel.getFont().getName(), Font.BOLD, priceLabel.getFont().getSize()));




        ImageIcon buyIcon = new ImageIcon(item.getBlockType().getCoinImagePath());
        JLabel buyIconLabel = new JLabel(buyIcon);

        JButton buyButton = new JButton("Buy");
        buyButton.addActionListener(e -> {
            if (item.isPurchased()) {
                JOptionPane.showMessageDialog(this.frame, "This item is already bought.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (Game.getInstance().getAstronaut().getInventory().get(item.getBlockType()) < item.getPrice()) {
                JOptionPane.showMessageDialog(this.frame, "You don't have enough coins.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                item.setPurchased(true);
                Game.getInstance().getAstronaut().removeCoinFromInventory(item.getBlockType(), item.getPrice());
                this.updateCoinPanel();
            }
        });

        JButton equipButton = new JButton();
        if (item instanceof Weapon) {
            equipButton.setText("Equip");
        } else if (item instanceof SpecialItem) {
            equipButton.setText("Use");
        }

        equipButton.addActionListener(e -> {
            if (!item.isPurchased()) {
                JOptionPane.showMessageDialog(this.frame, "You need to buy this item first.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (item instanceof Weapon weaponItem) {
                if (item.isEquipped()) {
                    JOptionPane.showMessageDialog(this.frame, "This item is already equipped.", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    Game.getInstance().getDome().equipWeapon(weaponItem);
                }

            } else if (item instanceof SpecialItem specialItem) {
                if (item.isEquipped()) {
                    JOptionPane.showMessageDialog(this.frame, "This item is already used.", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    specialItem.use();
                }

            }
        });

        JPanel buyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buyPanel.add(buyButton);
        buyPanel.add(buyIconLabel);

        itemPanel.add(imageLabel);
        itemPanel.add(nameLabel);
        itemPanel.add(descriptionLabel);
        itemPanel.add(damageLabel);
        itemPanel.add(priceLabel);
        itemPanel.add(buyPanel);
        itemPanel.add(equipButton);

        this.storePanel.repaint();
        this.storePanel.add(itemPanel);
        this.storePanel.revalidate();
    }
}