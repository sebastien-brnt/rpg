package rpg.map;

import rpg.player.Player;
import rpg.destructible.Monster;
import rpg.destructible.Obstacle;
import rpg.utility.AnsiColors;

import java.util.Random;

public class Map {
    private final int MAP_SIZE = 10;
    // Éléments de map
    private final String mapPlayer = "[" + AnsiColors.BLUE + "X" + AnsiColors.RESET + "]";
    private final String mapMoney = "[" + AnsiColors.GREEN + "$" + AnsiColors.RESET + "]";
    private final String mapObstacle = "[" + AnsiColors.YELLOW + "O" + AnsiColors.RESET + "]";
    private final String mapMonster = "[" + AnsiColors.RED + "M" + AnsiColors.RESET + "]";
    private final String mapFinish = "[" + AnsiColors.CYAN + "#" + AnsiColors.RESET + "]";
    private final String mapStore = "[" + AnsiColors.PURPLE + "B" + AnsiColors.RESET + "]";
    private final String mapWall = "[" + AnsiColors.YELLOW + "=" + AnsiColors.RESET + "]";

    private static final Random random = new Random();

    private Object[][] map = {
            {mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall},
            {mapWall, "[ ]", mapWall, "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", mapWall},
            {mapWall, "[ ]", mapWall, mapWall, "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", mapWall},
            {mapWall, "[ ]", "[ ]", mapWall, "[ ]", "[ ]", mapWall, mapWall, "[ ]", mapWall},
            {mapWall, mapWall, "[ ]", mapWall, "[ ]", "[ ]", mapWall, "[ ]", "[ ]", mapWall},
            {mapWall, "[ ]", "[ ]", mapWall, "[ ]", mapWall, mapWall, "[ ]", "[ ]", mapWall},
            {mapWall, "[ ]", "[ ]", mapWall, "[ ]", mapWall, "[ ]", "[ ]", "[ ]", mapWall},
            {mapWall, "[ ]", "[ ]", "[ ]", "[ ]", mapWall, "[ ]", mapWall, mapWall, mapWall},
            {mapWall, "[ ]", mapWall, "[ ]", mapStore, mapWall, "[ ]", "[ ]", "[ ]", "[ ]"},
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
        int randomNum = random.nextInt(20);

        if (randomNum < 6) { // 30% de chance d'obtenir un Obstacle
            return new Obstacle("Obstacle");
        } else if (randomNum < 10) { // 20% de chance d'obtenir un Monstre
            return new Monster("Monstre", 15);
        } else if (randomNum < 11) { // 5% de chance d'obtenir un Monstre
            return mapMoney;
        } else {
            return "[ ]"; // 45% de chance de laisser la case vide
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
