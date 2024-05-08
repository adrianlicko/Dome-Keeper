package sk.uniza.fri.shop;

import sk.uniza.fri.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreGui extends JFrame {
    private JButton backToTheGameButton;
    private JPanel mainPanel;
    private JPanel storeList;

    public StoreGui() {
        super("Dome Keeper Store");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(this.mainPanel);
        this.setVisible(true);
        this.pack();

        this.backToTheGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.getInstance().closeShop();
            }
        });
    }
}
