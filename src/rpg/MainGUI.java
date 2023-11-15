package rpg;

import rpg.game.Game;
import rpg.game.GameInputs;
import rpg.ui.DialogBoxStart;
import rpg.ui.GamePanel;

import javax.swing.*;

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
        // Create the main panel in which graphical components will be defined
        GamePanel gamePanel = new GamePanel(game);

        window.add(gamePanel);
        window.pack();
        //window.validate();

    }
}
