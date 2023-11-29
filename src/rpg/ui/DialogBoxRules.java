package rpg.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DialogBoxRules {

    private static JDialog d;

    public DialogBoxRules(JFrame parentFrame) {
        d = new JDialog(parentFrame, "Règles du jeu", true);
        d.setSize(600, 400);
        d.setLocationRelativeTo(null);
        d.setLayout(new GridBagLayout());
        d.setResizable(false);

        // Define MenuRulesPanel
        MenuRulesPanel menuRulesPanel = new MenuRulesPanel();

        d.add(menuRulesPanel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(50, 0, 30, 0), 0, 0));

        JLabel buyLabel = new JLabel("<APPUYEZ SUR ECHAP POUR QUITTER>");
        d.add(buyLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 100), 0, 0));

        // Gestion de la fermeture avec  ESCAPE
        int condition = JPanel.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = ((JPanel) d.getContentPane()).getInputMap(condition);
        ActionMap actionMap = ((JPanel) d.getContentPane()).getActionMap();

        // ESCAPE
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");
        actionMap.put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d.dispose();
            }
        });

        d.setVisible(true);
    }
}