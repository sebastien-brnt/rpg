package rpg.game;

public class Map {
    // Map
    static final int[][] MAP_TEMPLATE_DEFAULT = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 1, 1, 0, 1},
            {1, 1, 0, 1, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 1, 0, 1, 1, 0, 0, 1},
            {1, 0, 0, 1, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1, 1, 1},
            {1, 0, 1, 0, 0, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 0}
    };

    private int[][] map;

    public Map() {
        this.map = MAP_TEMPLATE_DEFAULT;
    }

    public int[][] getMap() {
        return this.map;
    }


}
