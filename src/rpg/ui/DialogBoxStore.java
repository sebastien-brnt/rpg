package rpg.ui;

import rpg.game.GameInputs;
import rpg.game.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DialogBoxStore {

    private static JDialog d;

    private boolean allowExit;

    public DialogBoxStore(JFrame parentFrame, GameInputs gameInputs, Player player, boolean allowExit) {
        d = new JDialog(parentFrame, "Boutique d'armes", true);
        d.setSize(500, 500);
        d.setLocationRelativeTo(null);
        d.setLayout(new GridBagLayout());
        this.allowExit = allowExit;

        // Define MenuStorePanel
        MenuStorePanel menuStore = new MenuStorePanel(gameInputs, player);

        d.add(menuStore, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(50, 0, 30, 0), 0, 0));

        JLabel buyLabel = new JLabel("<APPUYEZ SUR ENTRER POUR ACHETER L'ARME>");
        d.add(buyLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 100), 0, 0));

        // Gestion de la fermeture avec ENTER et ESCAPE
        int condition = JPanel.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = ((JPanel) d.getContentPane()).getInputMap(condition);
        ActionMap actionMap = ((JPanel) d.getContentPane()).getActionMap();

        // ENTER
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
        actionMap.put("enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuStore.isWeaponValid()) {
                    d.dispose();
                }
            }
        });

        // ESCAPE
        if (allowExit) {
            buyLabel.setText("<APPUYEZ SUR ENTRER POUR ACHETER L'ARME OU ECHAP POUR QUITTER LA BOUTIQUE>");
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");
            actionMap.put("escape", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (allowExit) {
                        d.dispose();
                    }
                }
            });
        }

        d.pack();
        d.setVisible(true);
    }
}

