package sk.uniza.fri.shop;

import sk.uniza.fri.Menu;
import sk.uniza.fri.game.player.Dome;
import sk.uniza.fri.game.weapons.Weapon;
import sk.uniza.fri.game.weapons.player.Purchasable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreList {
    private JFrame frame;
    private JButton backToTheGameButton;
    private JPanel mainPanel;
    private JPanel storePanel;

    public StoreList(JFrame frame) {
        this.frame = frame;
        this.storePanel.setLayout(new BoxLayout(this.storePanel, BoxLayout.Y_AXIS));


        this.frame.add(this.mainPanel);

        this.backToTheGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.getInstance().closeShop();
            }
        });
    }

    public void addToStore(Purchasable item) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));

        JLabel imageLabel = new JLabel(new ImageIcon(item.getImage().getImagePath()));
        JLabel nameLabel = new JLabel(item.getName());
        JLabel descriptionLabel = new JLabel(item.getDescription());
        JLabel damageLabel = new JLabel("Damage: " + item.getDamage()); // for weapons
        JLabel priceLabel = new JLabel("Price: " + item.getPrice());

        JButton buyButton = new JButton("Buy");
        buyButton.addActionListener(e -> {
            if (item.isPurchased()) {
                JOptionPane.showMessageDialog(this.frame, "This item is already bought.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                item.setPurchased(true);
            }
        });

        JButton equipButton = new JButton("Equip");
        equipButton.addActionListener(e -> {
            if (!item.isPurchased()) {
                JOptionPane.showMessageDialog(this.frame, "You need to buy this item first.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (item.isEquipped()) {
                JOptionPane.showMessageDialog(this.frame, "This item is already equipped.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                if (item instanceof Weapon weaponItem) {
                    Dome.getInstance().equipWeapon(weaponItem);
                }
            }
        });

        itemPanel.add(imageLabel);
        itemPanel.add(nameLabel);
        itemPanel.add(descriptionLabel);
        itemPanel.add(damageLabel); // for weapons
        itemPanel.add(priceLabel);
        itemPanel.add(buyButton);
        itemPanel.add(equipButton);

        this.storePanel.repaint();
        this.storePanel.add(itemPanel);
        this.storePanel.revalidate();
    }
}
