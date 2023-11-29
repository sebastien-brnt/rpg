package rpg.ui;

import rpg.game.Map;
import rpg.game.destructible.Monster;
import rpg.game.destructible.Obstacle;
import rpg.game.player.Player;
import rpg.game.store.WeaponStore;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    static final int cellSize = 24;

    private Object[][] mapGrid;
    private Player player;

    private Image finishImage, storeImage, obstacleImage, monsterImage, moneyImage;


    public MapPanel(Map map, Player player) {
        // Récupération de la map
        this.mapGrid = map.getMap();

        // Récupération du joueur
        this.player = player;

        // Récupération des images
        ImageIcon imgFinish = new ImageIcon("src/rpg/images/finish-flag.png");
        ImageIcon imgStore = new ImageIcon("src/rpg/images/shop.png");
        ImageIcon imgObstacle = new ImageIcon("src/rpg/images/tree.png");
        ImageIcon imgMonster = new ImageIcon("src/rpg/images/monster.png");
        ImageIcon imgMoney = new ImageIcon("src/rpg/images/money.png");

        // Définissions des variables d'images
        this.finishImage = imgFinish.getImage();
        this.storeImage = imgStore.getImage();
        this.obstacleImage = imgObstacle.getImage();
        this.monsterImage = imgMonster.getImage();
        this.moneyImage = imgMoney.getImage();
    }

    public Image getFinishImage() {
        return this.finishImage;
    }
    public Image getStoreImage() {
        return this.storeImage;
    }
    public Image getObstacleImage() {
        return this.obstacleImage;
    }
    public Image getMonsterImage() {
        return this.monsterImage;
    }
    public Image getMoneyImage() {
        return this.moneyImage;
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
        int CoordY = 30;

        // Dessin de la carte
        for (int row = 0; row < this.mapGrid.length; row++) {
            for (int col = 0; col < this.mapGrid[0].length; col++) {
                Color color;
                if (mapGrid[row][col] instanceof Integer) {
                    int value = (Integer) mapGrid[row][col];
                    switch (value) {
                        case 1:
                            color = Color.BLACK;
                            break;
                        case 3:
                            color = Color.GREEN;
                            break;
                        default:
                            color = Color.WHITE;
                    }
                } else {
                    color = Color.WHITE;
                }
                g.setColor(color);
                g.fillRect(CoordX + cellSize * col, CoordY + cellSize * row, cellSize, cellSize);
                if (this.mapGrid[row][col] instanceof Integer && (Integer) this.mapGrid[row][col] == 2) {
                    g.drawImage(this.player.getPlayerImage(), CoordX + cellSize * col, CoordY + cellSize * row, null);
                }
                if (this.mapGrid[row][col] instanceof Integer && (Integer) this.mapGrid[row][col] == 3) {
                    g.drawImage(this.getFinishImage(), CoordX + cellSize * col, CoordY + cellSize * row, null);
                }
                if (this.mapGrid[row][col] instanceof WeaponStore) {
                    g.drawImage(this.getStoreImage(), CoordX + cellSize * col, CoordY + cellSize * row, null);
                }
                if (this.mapGrid[row][col] instanceof Obstacle) {
                    g.drawImage(this.getObstacleImage(), CoordX + cellSize * col, CoordY + cellSize * row, null);
                }
                if (this.mapGrid[row][col] instanceof Monster) {
                    g.drawImage(this.getMonsterImage(), CoordX + cellSize * col, CoordY + cellSize * row, null);
                }
                if (this.mapGrid[row][col] instanceof Integer && (Integer) this.mapGrid[row][col] == 7) {
                    g.drawImage(this.getMoneyImage(), CoordX + cellSize * col, CoordY + cellSize * row, null);
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
