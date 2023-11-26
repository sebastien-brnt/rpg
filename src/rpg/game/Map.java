package rpg.game;

import rpg.game.destructible.Monster;
import rpg.game.destructible.Obstacle;
import rpg.game.store.WeaponStore;

public class Map {
    // Map
    static final int SIZE = 10;

    static final int mapWall = 1;

    static final int mapFinish = 3;

    static final WeaponStore weaponStore = new WeaponStore();

    private Object[][] MAP_TEMPLATE_DEFAULT = {
            {mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall},
            {mapWall, 0, mapWall, 0, 0, 0, 0, 0, 0, mapWall},
            {mapWall, 0, mapWall, mapWall, 0, 0, 0, 0, 0, mapWall},
            {mapWall, 0, 0, mapWall, 0, 0, mapWall, mapWall, 0, mapWall},
            {mapWall, mapWall, 0, mapWall, 0, 0, mapWall, 0, 0, mapWall},
            {mapWall, 0, 0, mapWall, 0, mapWall, mapWall, 0, 0, mapWall},
            {mapWall, 0, 0, mapWall, 0, mapWall, 0, 0, 0, mapWall},
            {mapWall, 0, 0, 0, 0, mapWall, 0, mapWall, mapWall, mapWall},
            {mapWall, 0, mapWall, 0, weaponStore, mapWall, 0, 0, 0, 0},
            {mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapFinish}
    };


    private Object[][] map;

    private int playerX, playerY;

    private Object buffer = 0;

    public Map() {
        this.map = MAP_TEMPLATE_DEFAULT;
        this.populateMap();
    }

    public Object[][] getMap() {
        return this.map;
    }

    public void updateMap(int x, int y, Object value) {
        this.map[x][y] = value;
    }

    public void setPlayer(int x, int y) {
        this.map[x][y] = 2;
        this.playerX = x;
        this.playerY = y;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public Object getBuffer() {
        return buffer;
    }
    public int getMapSize() {
        return SIZE;
    }

    public void setBuffer(int x, int y) {
        buffer = map[x][y];
    }

    public void setBuffer(int value) {
        buffer = value;
    }

    public boolean inTheMap(int x, int y) {
        return x >= 0 && x < getMapSize() && y >= 0 && y < getMapSize();
    }

    public void populateMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (this.map[i][j] instanceof Integer && (Integer) this.map[i][j] == 0) {
                    map[i][j] = getRandomElement();
                }
            }
        }
    }

    private Object getRandomElement() {
        int randomNum = (int)(Math.random() * 20);

        if (randomNum < 6) { // 30% de chance d'obtenir un Obstacle
            return new Obstacle("Rocher");
        } else if (randomNum < 10) { // 20% de chance d'obtenir un Monstre
            return new Monster("Monstre", 150);
        } else if (randomNum < 12) { // 10% de chance d'obtenir de l'argent
            return 7;
        } else {
            return 0; // 45% de chance de laisser la case vide
        }
    }

    public boolean getPresenceAround(int x, int y, Class<?> type) {
        return (inTheMap(x + 1, y) && type.isInstance(this.map[x + 1][y])) ||
                (inTheMap(x - 1, y) && type.isInstance(this.map[x - 1][y])) ||
                (inTheMap(x, y + 1) && type.isInstance(this.map[x][y + 1])) ||
                (inTheMap(x, y - 1) && type.isInstance(this.map[x][y - 1]));
    }
}
