package rpg.ui;


import rpg.game.Map;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    static final int cellSize = 24;

    private int[][] mapGrid;

    private Image playerImage;

    public MapPanel(Map map) {
        this.mapGrid = map.getMap();
        ImageIcon img = new ImageIcon("/Users/sebastien/Documents/Sorbonne/Cours/Java/Devoirs maison/rpg/src/rpg/link.png");
        this.playerImage = img.getImage();
    }
    
    public Image getPlayerImage() {
        return this.playerImage;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int CoordX = 200;
        int CoordY = 50;

        for (int row = 0; row < this.mapGrid.length; row++) {
            for (int col = 0; col < this.mapGrid[0].length; col++) {
                Color color;
                switch (this.mapGrid[row][col]) {
                    case 1 : color = Color.BLACK; break;
                    case 2 : color = Color.CYAN; break;
                    case 3 : color = Color.GREEN; break;
                    default : color = Color.WHITE;
                }
                g.setColor(color);
                g.fillRect(CoordX + cellSize * col, CoordY + cellSize * row, cellSize, cellSize);
                if (this.mapGrid[row][col] == 2) {
                    g.drawImage(this.getPlayerImage(), CoordX + cellSize * col, CoordY + cellSize * row, null);
                }
                g.setColor(Color.BLACK); // contours
                g.drawRect(CoordX + cellSize * col, CoordY + cellSize * row, cellSize, cellSize);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 300);
    }

}
