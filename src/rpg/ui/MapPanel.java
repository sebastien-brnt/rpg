package rpg.ui;

import rpg.game.Map;
import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    static final int cellSize = 24;

    private int[][] mapGrid;

    private Image playerImage, finishImage, storeImage;


    public MapPanel(Map map) {
        this.mapGrid = map.getMap();

        // Récupération des images
        ImageIcon imgPlayer = new ImageIcon("src/rpg/images/link.png");
        ImageIcon imgFinish = new ImageIcon("src/rpg/images/finish-flag.png");
        ImageIcon imgStore = new ImageIcon("src/rpg/images/shop.png");

        // Définissions des variables d'images
        this.playerImage = imgPlayer.getImage();
        this.finishImage = imgFinish.getImage();
        this.storeImage = imgStore.getImage();
    }

    public Image getPlayerImage() {
        return this.playerImage;
    }

    public Image getFinishImage() {
        return this.finishImage;
    }

    public Image getStoreImage() {
        return this.storeImage;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Calculer la largeur totale de la carte
        int mapWidth = this.mapGrid[0].length * cellSize;

        // Obtenir la largeur du MapPanel
        int panelWidth = getWidth();

        // Calculer CoordX pour centrer la carte horizontalement
        int CoordX = (panelWidth - mapWidth) / 2;
        int CoordY = 50;

        // Dessin de la carte
        for (int row = 0; row < this.mapGrid.length; row++) {
            for (int col = 0; col < this.mapGrid[0].length; col++) {
                Color color;
                switch (this.mapGrid[row][col]) {
                    case 1 : color = Color.BLACK; break;
                    case 2 : color = Color.CYAN; break;
                    case 3 : color = Color.GREEN; break;
                    case 4 : color = Color.PINK; break;
                    default : color = Color.WHITE;
                }
                g.setColor(color);
                g.fillRect(CoordX + cellSize * col, CoordY + cellSize * row, cellSize, cellSize);
                if (this.mapGrid[row][col] == 2) {
                    g.drawImage(this.getPlayerImage(), CoordX + cellSize * col, CoordY + cellSize * row, null);
                }
                if (this.mapGrid[row][col] == 3) {
                    g.drawImage(this.getFinishImage(), CoordX + cellSize * col, CoordY + cellSize * row, null);
                }
                if (this.mapGrid[row][col] == 4) {
                    g.drawImage(this.getStoreImage(), CoordX + cellSize * col, CoordY + cellSize * row, null);
                }
                g.setColor(Color.BLACK); // contours
                g.drawRect(CoordX + cellSize * col, CoordY + cellSize * row, cellSize, cellSize);
            }
        }
    }

    public void repaintMap() {
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 300);
    }
}
