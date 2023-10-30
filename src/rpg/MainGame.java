package rpg;

import rpg.destructible.Monster;
import rpg.player.Player;
import rpg.store.WeaponStore;
import rpg.weapons.Weapon;

import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) throws InterruptedException {

        // Initialisation des codes d'échappement ANSI pour les couleurs
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";

        Scanner scanner = new Scanner(System.in);

        Monster monster1 = new Monster("Monster 1", 15);
        Monster monster2 = new Monster("Monster 2", 15);

        System.out.println("\n=====================================");
        System.out.println("Bienvenue dans votre nouveau monde !");
        System.out.println("=====================================");
        System.out.print("Comment vous appelez vous ? ");
        String name = scanner.nextLine();

        Player player = new Player(name, 100, 150);

        Thread.sleep(300);
        System.out.println("\nEnchanté " + ANSI_BLUE + name +  ANSI_RESET + ", vous possédé désormais " + ANSI_BLUE + "100 PV" + ANSI_RESET + " ainsi que " + ANSI_BLUE + "150$" + ANSI_RESET + " !");
        System.out.println("Votre mission est la suivante : Vous devez arriver au coin inférieur droit de la map !");


        Thread.sleep(2500);

        WeaponStore store = new WeaponStore();
        System.out.println("\n================================");
        System.out.println("Catalogue de la boutique :");
        System.out.println("================================");
        Thread.sleep(1000);
        System.out.println(store.getWeaponList());

        Thread.sleep(800);
        System.out.println("\n================================");
        System.out.println("Choissisez votre première arme :");
        System.out.println("================================");

        System.out.print("Veuillez entrer l'ID de l'arme que vous souhaitez acheter  : ");
        String firstWeapon = scanner.nextLine();

        Weapon choosenWeapon = store.getWeaponOfStore(firstWeapon);
        player.buyWeapon(store, choosenWeapon);

        Thread.sleep(300);
        while (choosenWeapon == null) {
            System.out.print("\nVeuillez entrer l'ID de l'arme que vous souhaitez acheter  : ");
            firstWeapon = scanner.nextLine();

            choosenWeapon = store.getWeaponOfStore(firstWeapon);
            player.buyWeapon(store, choosenWeapon);
        }


        Thread.sleep(2000);

        System.out.println("\n================================");
        System.out.println("               MAP              ");
        System.out.println("================================");

        final int MAP_SIZE = 10;  // Taille de la map (5x5 pour cet exemple)
        final String mapPlayer = "[" + ANSI_BLUE + "X" + ANSI_RESET + "]";
        final String mapMoney = "[" + ANSI_GREEN + "$" + ANSI_RESET + "]";
        final String mapObstacle = "[" + ANSI_YELLOW + "O" + ANSI_RESET + "]";
        final String mapMonster = "[" + ANSI_RED + "M" + ANSI_RESET + "]";
        final String mapStore = "[" + ANSI_PURPLE + "B" + ANSI_RESET + "]";

        // Position initiale du joueur
        int posX = 1;
        int posY = 0;

        // Initialisation de la map
        String[][] map = {
                {"[-]", "[-]", "[-]", "[-]", "[-]", "[-]", "[-]", "[-]", "[-]", "[-]"},
                {mapStore, mapMoney, "[-]", mapMoney, "[ ]", "[ ]", "[ ]", "[ ]", mapMoney, "[-]"},
                {"[-]", "[ ]", "[-]", "[-]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", "[-]"},
                {"[-]", "[ ]", "[ ]", "[-]", "[ ]", "[ ]", "[-]", "[-]", "[ ]", "[-]"},
                {"[-]", "[-]", "[ ]", "[-]", "[ ]", "[ ]", "[-]", "[ ]", "[ ]", "[-]"},
                {"[-]", "[ ]", "[ ]", "[-]", "[ ]", "[-]", "[-]", "[ ]", "[ ]", "[-]"},
                {"[-]", "[ ]", "[ ]", "[-]", "[ ]", "[-]", "[ ]", "[ ]", "[ ]", "[-]"},
                {"[-]", "[ ]", "[ ]", "[ ]", "[ ]", "[-]", "[ ]", "[-]", "[-]", "[-]"},
                {"[-]", mapMoney, "[-]", "[ ]", mapStore, "[-]", mapMoney, "[ ]", "[ ]", "[ ]"},
                {"[-]", "[-]", "[-]", "[-]", "[-]", "[-]", "[-]", "[-]", "[-]", "[ ]"}
        };


        map[posX][posY] = mapPlayer;

        String mapBuffer = "";

        while (true) {
            // Affichage de la map
            for (int i = 0; i < MAP_SIZE; i++) {
                for (int j = 0; j < MAP_SIZE; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }

            System.out.println("\nCommandes disponibles :");
            if (!(map[posX + 1][posY]).equals("[-]")) {
                System.out.println("S (bas)");
            }
            if ((posY - 1) > 0 && !(map[posX][posY - 1]).equals("[-]")) {
                System.out.println("Q (gauche)");
            }
            if (!(map[posX][posY + 1]).equals("[-]")) {
                System.out.println("D (droite)");
            }
            if ((posX - 1) > 0 && !(map[posX - 1][posY]).equals("[-]")) {
                System.out.println("Z (haut)");
            }
            if (mapBuffer.equals(mapMoney)) {
                System.out.println("R (Ramasser)");
            }
            if (mapBuffer.equals(mapStore)) {
                System.out.println("V (Visiter la boutique)");
            }

            System.out.println("I (Voir mes information et mon inventaire)");
            System.out.println(": (quitter le jeu)");

            char move = scanner.nextLine().toUpperCase().charAt(0);
            if (move == ':') break;

            switch (move) {
                case 'Z':
                    if (posX > 0 && (posX - 1) > 0 && !(map[posX - 1][posY]).equals("[-]")) {
                        map[posX][posY] = "[ ]";
                        posX--;
                        mapBuffer = map[posX][posY];
                        map[posX][posY] = mapPlayer;
                    }
                    break;
                case 'Q':
                    if (posY > 0 && (posY - 1) > 0 && !(map[posX][posY - 1]).equals("[-]")) {
                        map[posX][posY] = "[ ]";
                        posY--;
                        mapBuffer = map[posX][posY];
                        map[posX][posY] = mapPlayer;
                    }
                    break;
                case 'S':
                    if (posX < MAP_SIZE - 1 && !(map[posX + 1][posY]).equals("[-]")) {
                        map[posX][posY] = "[ ]";
                        posX++;
                        mapBuffer = map[posX][posY];
                        map[posX][posY] = mapPlayer;
                    }
                    break;
                case 'D':
                    if (posY < MAP_SIZE - 1 && !(map[posX][posY + 1]).equals("[-]")) {
                        map[posX][posY] = "[ ]";
                        posY++;
                        mapBuffer = map[posX][posY];
                        map[posX][posY] = mapPlayer;
                    }
                    break;
                case 'R':
                    if ((mapBuffer).equals("[$]")) {
                        map[posX][posY] = "[ ]";
                        player.addMoney(10);
                        Thread.sleep(300);
                        System.out.println("\nVous venez de ramasser 10$ !");
                        System.out.println("Votre solde est de " + ANSI_BLUE + player.getMoney() + ANSI_RESET);
                        Thread.sleep(2000);
                    }
                    break;
                case 'I':
                    System.out.println(player);
                    break;
            }
        }

        Thread.sleep(20000);


        System.out.println(player);

        System.out.println("\n===================== ACTIONS ==========================");
        System.out.println(monster1.getPv());
        System.out.println("==========================================================\n");

        System.out.println("================================");
        System.out.println("           Joueurs              ");
        System.out.println("================================");
        System.out.println(player);

        scanner.close();
    }
}
