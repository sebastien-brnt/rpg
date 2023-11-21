package rpg;

import rpg.game.Game;
import rpg.game.GameInputs;
import rpg.ui.DialogBoxStart;
import rpg.ui.DialogBoxStore;
import rpg.ui.GamePanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainGUI {

    public static void main(String[] args) {
        // Define the GUI main window
        JFrame window = new JFrame();
        // set window size (width, height)
        window.setSize(800, 500);
        // forbid the resizing of the window by the user
        window.setResizable(false);
        // place the window in the center of the screen
        window.setLocationRelativeTo(null);
        // exit the application when the user close the frame
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set the window title
        window.setTitle("RPG");
        // show the window
        window.setVisible(true);

        // display start menu where to define your character and set game inputs variables
        GameInputs gameInputs = new GameInputs();
        new DialogBoxStart(gameInputs);
        System.out.println(gameInputs.getPlayerName());
        System.out.println(gameInputs.getPlayerCast());

        // create game
        Game game = new Game(gameInputs);

        // display store where to define your first weapon
        new DialogBoxStore(gameInputs, game.getPlayer());

        // Display the game rules
        JOptionPane.showMessageDialog(window, game.getPlayer().getName() + ", " + game.getTarget());

        // Create the main panel in which graphical components will be defined
        GamePanel gamePanel = new GamePanel(game);

        window.add(gamePanel);

        // Écouteur d'évènement
        KeyAdapter keyAdapter = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (!game.gameIsFinish()) {
                    game.right(e);
                    game.left(e);
                    game.top(e);
                    game.bottom(e);
                    gamePanel.getMapPanel().repaintMap();
                }
            }
        };

        window.addKeyListener(keyAdapter);

        // Timer pour vérifier l'état du jeu
        new Timer(100, e -> {
            if (game.getMap().getBuffer() == 3) {
                game.getMap().updateMap(game.getMap().getPlayerX(), game.getMap().getPlayerY(), 3);
                game.setGameFinish(true);
                window.removeKeyListener(keyAdapter);
                int option = JOptionPane.showConfirmDialog(window, "Félicitation " + game.getPlayer().getName() + ", tu as fini le jeu !\nVoulez-vous recommencer ?", "Jeu Terminé", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // Recommencer le jeu
                    window.dispose();
                    main(new String[]{});
                    game.getMap().setBuffer(0);
                } else {
                    // Fermer l'application
                    System.exit(0);
                }
            } else if (game.getMap().getBuffer() == 4) {
                new DialogBoxStore(gameInputs, game.getPlayer());
            }
        }).start();


        window.pack();
        //window.validate();

    }
}
