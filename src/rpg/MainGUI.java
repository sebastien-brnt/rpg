package rpg;

import rpg.game.Game;
import rpg.game.GameInputs;
import rpg.game.store.WeaponStore;
import rpg.ui.DialogBoxStart;
import rpg.ui.DialogBoxStore;
import rpg.ui.GamePanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainGUI {

    private static boolean storeOpened = false;

    private static WeaponStore store = new WeaponStore();

    public static void main(String[] args) {
        // Define the GUI main window
        JFrame window = new JFrame();
        // set window size (width, height)
        window.setSize(800, 600);
        // forbid the resizing of the window by the user
        window.setResizable(false);
        // place the window in the center of the screen
        window.setLocationRelativeTo(null);
        // exit the application when the user close the frame
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set the window title
        window.setTitle("RPG by Sébastien B.");
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
        new DialogBoxStore(window, gameInputs, game.getPlayer(), store, false);

        // Display the game rules
        JOptionPane.showMessageDialog(window, game.getPlayer().getName() + ", " + game.getTarget());

        // Create the main panel in which graphical components will be defined
        GamePanel gamePanel = new GamePanel(game);

        window.add(gamePanel);

        // Logique de jeu
        KeyAdapter keyAdapter = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (!game.gameIsFinish()) {
                    game.right(e);
                    game.left(e);
                    game.top(e);
                    game.bottom(e);
                    game.getMoney(e);
                    game.attackObstacle(e, window);
                    game.attackMonster(e, window);
                    game.openInventory(e, window);
                    gamePanel.getMapPanel().repaintMap();
                    gamePanel.getPlayerInfoPanel().updatePlayerInfo();
                }
            }
        };

        window.addKeyListener(keyAdapter);

        // Timer pour vérifier l'état du jeu
        new Timer(100, e -> {
            if (game.getMap().getBuffer() instanceof Integer && (Integer) game.getMap().getBuffer() == 3) {
                System.out.println("fin du jeu");
                game.getMap().updateMap(game.getMap().getPlayerX(), game.getMap().getPlayerY(), 3);
                game.setGameFinish(true);
                window.removeKeyListener(keyAdapter);
                JOptionPane.showMessageDialog(window, "Félicitation " + game.getPlayer().getName() + " ! Tu as réussi ta mission, le jeu est terminé !", "Jeu Terminé", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            } else if (game.getMap().getBuffer() instanceof WeaponStore && !storeOpened) {
                storeOpened = true;
                new DialogBoxStore(window, gameInputs, game.getPlayer(), (WeaponStore) game.getMap().getBuffer(), true);
                System.out.println("boutique");
            } else if (!(game.getMap().getBuffer() instanceof WeaponStore)) {
                storeOpened = false;
            } else if (game.getPlayer().getPv() <= 0) {
                JOptionPane.showMessageDialog(window, "Dommage " + game.getPlayer().getName() + ", tu es mort ! Le jeu est terminé !", "Jeu Terminé", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
        }).start();

        window.pack();
        //window.validate();
    }
}
