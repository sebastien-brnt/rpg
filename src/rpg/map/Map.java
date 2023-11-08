package rpg.map;

import rpg.destructible.Destructible;
import rpg.player.Player;
import rpg.destructible.Monster;
import rpg.destructible.Obstacle;
import rpg.utility.AnsiColors;

import java.util.Random;

public class Map {
    // Taille de la map
    private final int MAP_SIZE = 10;

    // Éléments de map
    private final String mapPlayer = "[" + AnsiColors.BLUE + "X" + AnsiColors.RESET + "]";
    private final String mapMoney = "[" + AnsiColors.GREEN + "$" + AnsiColors.RESET + "]";
    private final String mapObstacle = "[" + AnsiColors.YELLOW + "O" + AnsiColors.RESET + "]";
    private final String mapMonster = "[" + AnsiColors.RED + "M" + AnsiColors.RESET + "]";
    private final String mapFinish = "[" + AnsiColors.CYAN + "#" + AnsiColors.RESET + "]";
    private final String mapStore = "[" + AnsiColors.PURPLE + "B" + AnsiColors.RESET + "]";
    private final String mapWall = "[" + AnsiColors.YELLOW + "=" + AnsiColors.RESET + "]";

    // Buffer de la map
    private Object mapBuffer = "";

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

    // Constructor without position of player
    public Map(Player player) {
        map[1][0] = player;
        this.populateMap();
    }

    // Constructor with position of player
    public Map(Player player, int positionX, int positionY) {
        map[positionX][positionY] = player;
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
        } else if (randomNum < 12) { // 10% de chance d'obtenir de l'argent
            return mapMoney;
        } else {
            return "[ ]"; // 45% de chance de laisser la case vide
        }
    }

    public Object getMapBuffer() {
        return mapBuffer;
    }

    public void setMapBuffer(Object value) {
        this.mapBuffer = value;
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
        System.out.println();
    }
    
    public void displayMapLegend() {
        System.out.println("\n================================");
        System.out.println("Légende de la map");
        System.out.println("================================");
        System.out.println(this.getMapPlayer() + ": Vous");
        System.out.println(this.getMapMoney() + ": Argent (Entre 1$ et 30$)");
        System.out.println(this.getMapWall() + ": Murs");
        System.out.println(this.getMapObstacle() + ": Obstacles");
        System.out.println(this.getMapMonster() + ": Monstres");
        System.out.println(this.getMapFinish() + ": Objectif");
    }

    public String getMapPlayer() {
        return mapPlayer;
    }

    public String getMapMoney() {
        return mapMoney;
    }

    public String getMapObstacle() {
        return mapObstacle;
    }

    public String getMapMonster() {
        return mapMonster;
    }

    public String getMapFinish() {
        return mapFinish;
    }

    public String getMapStore() {
        return mapStore;
    }

    public String getMapWall() {
        return mapWall;
    }

    public void resetLocation(int posX, int posY) {
        this.map[posX][posY] = "[ ]";
    }

    // Place l'item où il était si le joueur ne le ramasse pas
    public void replaceItemInMap(int posX, int posY) {
        if (this.mapBuffer.equals(this.getMapMoney())) {
            map[posX][posY] = this.getMapMoney();
        } else if (this.mapBuffer.equals(this.getMapStore())) {
            map[posX][posY] = this.getMapStore();
        } else if (this.mapBuffer instanceof Destructible) {
            map[posX][posY] = this.mapBuffer;
        } else if (this.mapBuffer.equals(this.getMapMonster())) {
            map[posX][posY] = this.getMapMonster();
        } else {
            this.resetLocation(posX, posY);
        }
    }

    // Vérifie si un déplacement spécifique est diponible
    public boolean getMove(int x, int y) {
        // Vérifier si les coordonnées sont dans les limites de la map
        if (!inTheMap(x, y)) {
            return false;
        }

        // Vérifier si la case n'est pas un mur ou un objet destructible
        return !(this.map[x][y].equals(getMapWall()) || this.map[x][y] instanceof Destructible);
    }

    public boolean getPresenceAround(int x, int y, Class<?> type) {
        return (inTheMap(x + 1, y) && type.isInstance(this.map[x + 1][y])) ||
                (inTheMap(x - 1, y) && type.isInstance(this.map[x - 1][y])) ||
                (inTheMap(x, y + 1) && type.isInstance(this.map[x][y + 1])) ||
                (inTheMap(x, y - 1) && type.isInstance(this.map[x][y - 1]));
    }

    public boolean inTheMap(int x, int y) {
        return x >= 0 && x < getMapSize() && y >= 0 && y < getMapSize();
    }
}
