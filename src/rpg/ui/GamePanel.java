package rpg.ui;


import rpg.game.Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private MapPanel mapPanel;


    public GamePanel(Game game) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        // Add MapPanel to GamePanel
        this.mapPanel = new MapPanel(game.getMap());
        this.add(mapPanel, BorderLayout.NORTH);

        //TODO: Add PlayerInfoPanel to GamePanel
        this.add(new PlayerInfoPanel(game.getPlayer()), BorderLayout.CENTER);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 500);
    }
}
