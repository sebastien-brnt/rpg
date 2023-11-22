package rpg.game;

public class Map {
    // Map
    static final int SIZE = 10;
    static final int[][] MAP_TEMPLATE_DEFAULT = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 1, 1, 0, 1},
            {1, 1, 0, 1, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 1, 0, 1, 1, 0, 0, 1},
            {1, 0, 0, 1, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1, 1, 1},
            {1, 0, 1, 0, 4, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 3}
    };

    private int[][] map;

    private int playerX, playerY;

    private int buffer = 0;

    public Map() {
        this.map = MAP_TEMPLATE_DEFAULT;
        this.populateMap();
    }

    public int[][] getMap() {
        return this.map;
    }

    public void updateMap(int x, int y, int value) {
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

    public int getBuffer() {
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
                if (map[i][j] == 0) {
                    map[i][j] = getRandomElement();
                }
            }
        }
    }

    private int getRandomElement() {
        int randomNum = (int)(Math.random() * 20);

        if (randomNum < 6) { // 30% de chance d'obtenir un Obstacle
            return 5;
        } else if (randomNum < 10) { // 20% de chance d'obtenir un Monstre
            return 6;
        } else if (randomNum < 12) { // 10% de chance d'obtenir de l'argent
            return 7;
        } else {
            return 0; // 45% de chance de laisser la case vide
        }
    }
}
