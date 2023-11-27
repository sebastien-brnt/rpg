package rpg.ui;

import rpg.game.GameInputs;
import rpg.game.player.Player;
import rpg.game.store.WeaponStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DialogBoxChangeWeapon {

        private static JDialog d;

        public DialogBoxChangeWeapon(JFrame parentFrame, GameInputs gameInputs, Player player) {
            d = new JDialog(parentFrame, "Changement d'armes", true);
            d.setSize(500, 500);
            d.setLocationRelativeTo(null);
            d.setLayout(new GridBagLayout());

            // Define MenuStorePanel
            MenuChangeWeaponPanel menuWeapons = new MenuChangeWeaponPanel(gameInputs, player);

            d.add(menuWeapons, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                    new Insets(50, 0, 30, 0), 0, 0));

            JLabel buyLabel = new JLabel("<APPUYEZ SUR ENTRER POUR CHANGER L'ARME OU ECHAP POUR QUITTER>");
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
                    if (menuWeapons.isWeaponValid()) {
                        d.dispose();
                    }
                }
            });

            // ESCAPE
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");
            actionMap.put("escape", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        d.dispose();
                    }
                });

            d.pack();
            d.setVisible(true);
        }
}
