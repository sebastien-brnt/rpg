package rpg.ui;


import rpg.game.GameInputs;
import rpg.game.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DialogBoxStore {

    private static JDialog d;

    public DialogBoxStore(GameInputs gameInputs, Player player) {
        JFrame f = new JFrame();
        d = new JDialog(f, "Boutique d'armes", true);
        d.setSize(500, 500);
        d.setLocationRelativeTo(null);
        d.setLayout(new GridBagLayout());

        // Define MenuStartPanel where you can define your character (name, cast, etc.)
        MenuStorePanel menuStore = new MenuStorePanel(gameInputs, player);

        d.add(menuStore, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(50, 0, 30, 0), 0, 0));

        JLabel buyLabel = new JLabel("<PRESS ENTER TO BUY WEAPON>");
        d.add(buyLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 100), 0, 0));

        // Close JDialog when pressing key "ENTER"
        int condition = JPanel.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = ((JPanel) d.getContentPane()).getInputMap(condition);
        ActionMap actionMap = ((JPanel) d.getContentPane()).getActionMap();
        String escape = "escape";
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), escape);
        actionMap.put(escape, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d.dispose();
            }
        });

        d.pack();
        d.setVisible(true);
    }
}
