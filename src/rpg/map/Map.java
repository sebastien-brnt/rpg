package rpg.map;

import rpg.player.Player;
import rpg.destructible.Monster;
import rpg.destructible.Obstacle;
import java.util.Random;

public class Map {

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_BLACK = "\u001B[30m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_BLUE = "\u001B[34m";
    private final String ANSI_PURPLE = "\u001B[35m";
    private final String ANSI_CYAN = "\u001B[36m";
    private final String ANSI_WHITE = "\u001B[37m";

    private final int MAP_SIZE = 10;
    // Éléments de map
    private final String mapPlayer = "[" + ANSI_BLUE + "X" + ANSI_RESET + "]";
    private final String mapMoney = "[" + ANSI_GREEN + "$" + ANSI_RESET + "]";
    private final String mapObstacle = "[" + ANSI_YELLOW + "O" + ANSI_RESET + "]";
    private final String mapMonster = "[" + ANSI_RED + "M" + ANSI_RESET + "]";
    private final String mapFinish = "[" + ANSI_CYAN + "#" + ANSI_RESET + "]";
    private final String mapStore = "[" + ANSI_PURPLE + "B" + ANSI_RESET + "]";
    private final String mapWall = "[" + ANSI_YELLOW + "=" + ANSI_RESET + "]";

    private static final Random random = new Random();

    private Object[][] map = {
            {mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall},
            {mapWall, mapMoney, mapWall, mapMoney, "[ ]", "[ ]", "[ ]", "[ ]", mapMoney, mapWall},
            {mapWall, "[ ]", mapWall, mapWall, "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", mapWall},
            {mapWall, "[ ]", "[ ]", mapWall, "[ ]", "[ ]", mapWall, mapWall, "[ ]", mapWall},
            {mapWall, mapWall, "[ ]", mapWall, "[ ]", "[ ]", mapWall, "[ ]", "[ ]", mapWall},
            {mapWall, "[ ]", "[ ]", mapWall, "[ ]", mapWall, mapWall, "[ ]", "[ ]", mapWall},
            {mapWall, "[ ]", "[ ]", mapWall, "[ ]", mapWall, "[ ]", "[ ]", "[ ]", mapWall},
            {mapWall, "[ ]", "[ ]", "[ ]", "[ ]", mapWall, "[ ]", mapWall, mapWall, mapWall},
            {mapWall, mapMoney, mapWall, "[ ]", mapStore, mapWall, mapMoney, "[ ]", "[ ]", "[ ]"},
            {mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapFinish}
    };

    public Map(Player player) {
        map[1][0] = player;
        this.populateMap();
    }

    public void populateMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].equals("[ ]")) {
                    map[i][j] = getRandomElement();
                }
            }
        }
    }

    private Object getRandomElement() {
        int randomNum = random.nextInt(10);

        if (randomNum < 3) { // 30% de chance d'obtenir un Obstacle
            return new Obstacle("Obstacle");
        } else if (randomNum < 5) { // 20% de chance d'obtenir un Monstre
            return new Monster("Monstre", 15);
        } else {
            return "[ ]"; // 50% de chance de laisser la case vide
        }
    }

    public int getMapSize() {
        return MAP_SIZE;
    }

    public Object[][] getMap() {
        return map;
    }

    public void displayMap() {
        for (int i = 0; i < MAP_SIZE; i++) {
            System.out.println();
            for (int j = 0; j < MAP_SIZE; j++) {
                System.out.print(map[i][j]);
            }
        }
    }
}
