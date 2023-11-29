package rpg.ui;

import rpg.game.Map;
import rpg.game.destructible.Destructible;
import rpg.game.destructible.Monster;
import rpg.game.destructible.Obstacle;
import rpg.game.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DialogBoxAttackDestructible {

    private static JDialog d;

    public DialogBoxAttackDestructible(JFrame parentFrame, Player player, Destructible destructible, Map map, int xDestructible, int yDestructible) {

        d = new JDialog(parentFrame, "Combat", true);
        d.setSize(700, 500);
        d.setLocationRelativeTo(null);
        d.setLayout(new GridBagLayout());

        // Define MenuCombat
        MenuCombat menuCombat = new MenuCombat(player, destructible);

        d.add(menuCombat, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(50, 0, 30, 0), 0, 0));

        JLabel buyLabel = new JLabel("<APPUYEZ SUR ENTRER POUR ATTAQUER OU ECHAP POUR QUITTER LE COMBAT>");
        d.add(buyLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 100), 0, 0));

        menuCombat.updateInfos();

        // Gestion de la fermeture avec ENTER et ESCAPE
        int condition = JPanel.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = ((JPanel) d.getContentPane()).getInputMap(condition);
        ActionMap actionMap = ((JPanel) d.getContentPane()).getActionMap();

        // ENTER
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
        actionMap.put("enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.attackDestructible(destructible);

                if (destructible instanceof Monster) {
                    ((Monster) destructible).attackPlayer(player);
                }

                menuCombat.updateInfos();

                if (player.getPv() <= 0) {
                    JOptionPane.showMessageDialog(d, "Dommage " + player.getName() + ", tu es mort ! Le jeu est terminé !", "Jeu Terminé", JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                }

                if (destructible.getPv() <= 0) {
                    JOptionPane.showMessageDialog(d, "Bravo ! Vous avez tuer un " + destructible.getName() + " !");

                    // Ajout de l'XP au joueur
                    player.addXp(120, true);

                    if (player.getPv() > 0) {
                        if (destructible instanceof Monster) {
                            // Ajout des PV au joueur en fonction de son niveau
                            double winPv = 20;
                            // Pour chaque niveau du joueur il gagne 5 PV en plus
                            for (int i = 0; i < player.getLevel(); i++) {
                                if (i <= 1) {
                                    winPv += 5;
                                } else if (i <= 3) {
                                    winPv += 2.5;
                                } else if (i <= 5) {
                                    winPv += 1.75;
                                } else {
                                    winPv += 1;
                                }
                            }
                            player.addPv(winPv);

                            player.addMoney(20, true);
                        }

                        map.updateMap(xDestructible, yDestructible, 0);
                    }

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
