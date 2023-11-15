package rpg.ui;


import rpg.game.GameInputs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DialogBoxStart {

    private static JDialog d;

    public DialogBoxStart(GameInputs gameInputs) {
        JFrame f = new JFrame();
        d = new JDialog(f, "Welcome to my RPG", true);
        d.setSize(500, 500);
        d.setLocationRelativeTo(null);
        d.setLayout(new GridBagLayout());

        // Define MenuStartPanel where you can define your character (name, cast, etc.)
        MenuStartPanel menuStart = new MenuStartPanel(gameInputs);

        d.add(menuStart, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(50, 0, 30, 0), 0, 0));

        JLabel startLabel = new JLabel("<PRESS ENTER TO START THE GAME>");
        d.add(startLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 100), 0, 0));

        // Close JDialog when pressing key "ENTER"
        int condition = JPanel.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = ((JPanel) d.getContentPane()).getInputMap(condition);
        ActionMap actionMap = ((JPanel) d.getContentPane()).getActionMap();
        String enter = "enter";
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
        actionMap.put(enter, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d.dispose();
            }
        });

        d.pack();
        d.setVisible(true);
    }
}
