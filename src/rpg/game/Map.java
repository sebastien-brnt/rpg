package rpg.game;

public class Map {
    // Map
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

    public void setBuffer(int x, int y) {
        buffer = map[x][y];
    }
}
