package rpg.ui;


import rpg.game.Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private MapPanel mapPanel;

    private PlayerInfoPanel playerInfoPanel;


    public GamePanel(Game game) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        // Add MapPanel to GamePanel
        this.mapPanel = new MapPanel(game.getMap(), game.getPlayer());
        this.add(mapPanel, BorderLayout.NORTH);

        // Add PlayerInfoPanel to GamePanel
        this.playerInfoPanel = new PlayerInfoPanel(game.getPlayer());
        this.add(playerInfoPanel, BorderLayout.CENTER);

    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }

    public PlayerInfoPanel getPlayerInfoPanel() {
        return playerInfoPanel;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 570);
    }
}
