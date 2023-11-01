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

        // Éléments de map
        final int MAP_SIZE = 10;
        final String mapPlayer = "[" + ANSI_BLUE + "X" + ANSI_RESET + "]";
        final String mapMoney = "[" + ANSI_GREEN + "$" + ANSI_RESET + "]";
        final String mapObstacle = "[" + ANSI_YELLOW + "O" + ANSI_RESET + "]";
        final String mapMonster = "[" + ANSI_RED + "M" + ANSI_RESET + "]";
        final String mapFinish = "[" + ANSI_CYAN + "#" + ANSI_RESET + "]";
        final String mapStore = "[" + ANSI_PURPLE + "B" + ANSI_RESET + "]";
        final String mapWall = "[" + ANSI_YELLOW + "=" + ANSI_RESET + "]";

        Scanner scanner = new Scanner(System.in);

        Monster monster1 = new Monster("Monster 1", 15);
        Monster monster2 = new Monster("Monster 2", 15);

        for (int i = 0; i < 3; i++) {
            System.out.println();
        }

        System.out.println("=====================================");
        System.out.println("Bienvenue dans votre nouveau monde !");
        System.out.println("=====================================");
        System.out.print("Comment vous appelez vous ? ");
        String name = scanner.nextLine();

        Player player = new Player(name, 100, 150);

        Thread.sleep(300);
        System.out.println("\nEnchanté " + ANSI_BLUE + name +  ANSI_RESET + ", vous possédé désormais " + ANSI_BLUE + "100 PV" + ANSI_RESET + " ainsi que " + ANSI_BLUE + "150$" + ANSI_RESET + " !");
        System.out.println("Votre mission est la suivante : Vous devez arriver au coin inférieur droit de la map représenté par " + mapFinish + " !");


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

        System.out.print("Veuillez entrer l'ID de l'arme que vous souhaitez acheter : ");
        String firstWeapon = scanner.nextLine();

        Weapon choosenWeapon = store.getWeaponOfStore(firstWeapon);
        player.buyWeapon(store, choosenWeapon);

        Thread.sleep(300);
        while (choosenWeapon == null) {
            System.out.print("\nVeuillez entrer l'ID de l'arme que vous souhaitez acheter : ");
            firstWeapon = scanner.nextLine();

            choosenWeapon = store.getWeaponOfStore(firstWeapon);
            player.buyWeapon(store, choosenWeapon);
        }


        Thread.sleep(2000);

        System.out.println("\n================================");
        System.out.println("               MAP              ");
        System.out.println("================================");

        // Position initiale du joueur
        int posX = 1;
        int posY = 0;

        // Initialisation de la map
        String[][] map = {
                {mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall},
                { mapWall, mapMoney, mapWall, mapMoney, "[ ]", mapObstacle, "[ ]", "[ ]", mapMoney, mapWall},
                {mapWall, "[ ]", mapWall, mapWall, "[ ]", mapMonster, "[ ]", "[ ]", "[ ]", mapWall},
                {mapWall, "[ ]", "[ ]", mapWall, "[ ]", "[ ]", mapWall, mapWall, mapMonster, mapWall},
                {mapWall, mapWall, mapObstacle, mapWall, "[ ]", "[ ]", mapWall, "[ ]", "[ ]", mapWall},
                {mapWall, "[ ]", "[ ]", mapWall, "[ ]", mapWall, mapWall, "[ ]", "[ ]", mapWall},
                {mapWall, mapObstacle, mapMonster, mapWall, "[ ]", mapWall, "[ ]", "[ ]", "[ ]", mapWall},
                {mapWall, "[ ]", "[ ]", mapObstacle, mapObstacle, mapWall, "[ ]", mapWall, mapWall, mapWall},
                {mapWall, mapMoney, mapWall, "[ ]", mapStore, mapWall, mapMoney, "[ ]", mapMonster, "[ ]"},
                {mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapFinish}
        };


        map[posX][posY] = mapPlayer;

        String mapBuffer = "";

        while (!mapBuffer.equals(mapFinish)) {
            System.out.println();

            // Affichage de la map
            for (int i = 0; i < MAP_SIZE; i++) {
                for (int j = 0; j < MAP_SIZE; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }

            System.out.println("\nCommandes disponibles :");
            if (!(posX + 1 > MAP_SIZE - 1) && !(map[posX + 1][posY]).equals(mapWall) && !(map[posX + 1][posY]).equals(mapObstacle)) {
                System.out.println("S (bas)");
            }
            if ((posY - 1) >= 0 && !(map[posX][posY - 1]).equals(mapWall) && !(map[posX][posY - 1]).equals(mapObstacle)) {
                System.out.println("Q (gauche)");
            }
            if (!(posY + 1 > MAP_SIZE - 1) && !(map[posX][posY + 1]).equals(mapWall) && !(map[posX][posY + 1]).equals(mapObstacle)) {
                System.out.println("D (droite)");
            }
            if ((posX - 1) >= 0 && !(map[posX - 1][posY]).equals(mapWall) && !(map[posX - 1][posY]).equals(mapObstacle)) {
                System.out.println("Z (haut)");
            }
            if (mapBuffer.equals(mapMoney)) {
                System.out.println("R (Ramasser de l'argent)");
            }
            if ( (!(posX + 1 > MAP_SIZE - 1) && map[posX + 1][posY].equals(mapObstacle)) ||
                 ((posX - 1) >= 0 && map[posX - 1][posY].equals(mapObstacle)) ||
                 (!(posY + 1 > MAP_SIZE - 1) && map[posX][posY + 1].equals(mapObstacle)) ||
                 ((posY - 1) >= 0 && map[posX][posY - 1].equals(mapObstacle)) )  {
                System.out.println("L (Attaquer l'obstacle)");
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
                    if (posX >= 0 && (posX - 1) >= 0 && !(map[posX - 1][posY]).equals(mapWall)) {
                        // Si le joueur ne ramasse pas l'argent on remet l'argent sur la map
                        if (mapBuffer.equals(mapMoney)) {
                            map[posX][posY] = mapMoney;
                        } else if (mapBuffer.equals(mapStore)) {
                            map[posX][posY] = mapStore;
                        } else if (mapBuffer.equals(mapObstacle)) {
                            map[posX][posY] = mapObstacle;
                        } else if (mapBuffer.equals(mapMonster)) {
                            map[posX][posY] = mapMonster;
                        } else {
                            map[posX][posY] = "[ ]";
                        }

                        posX--;
                        mapBuffer = map[posX][posY];
                        map[posX][posY] = mapPlayer;
                        System.out.println("\nVous vous êtes déplacé vers le " + ANSI_BLUE + "haut" + ANSI_RESET);
                    }
                    break;
                case 'Q':
                    if (posY >= 0 && (posY - 1) >= 0 && !(map[posX][posY - 1]).equals(mapWall)) {
                        // Si le joueur ne ramasse pas l'argent on remet l'argent sur la map
                        if (mapBuffer.equals(mapMoney)) {
                            map[posX][posY] = mapMoney;
                        } else if (mapBuffer.equals(mapStore)) {
                            map[posX][posY] = mapStore;
                        } else if (mapBuffer.equals(mapObstacle)) {
                            map[posX][posY] = mapObstacle;
                        } else if (mapBuffer.equals(mapMonster)) {
                            map[posX][posY] = mapMonster;
                        } else {
                            map[posX][posY] = "[ ]";
                        }

                        posY--;
                        mapBuffer = map[posX][posY];
                        map[posX][posY] = mapPlayer;
                        System.out.println("\nVous vous êtes déplacé vers la " + ANSI_BLUE + "gauche" + ANSI_RESET);
                    }
                    break;
                case 'S':
                    if (posX < MAP_SIZE - 1 && !(map[posX + 1][posY]).equals(mapWall)) {
                        // Si le joueur ne ramasse pas l'argent on remet l'argent sur la map
                        if (mapBuffer.equals(mapMoney)) {
                            map[posX][posY] = mapMoney;
                        } else if (mapBuffer.equals(mapStore)) {
                            map[posX][posY] = mapStore;
                        } else if (mapBuffer.equals(mapObstacle)) {
                            map[posX][posY] = mapObstacle;
                        } else if (mapBuffer.equals(mapMonster)) {
                            map[posX][posY] = mapMonster;
                        } else {
                            map[posX][posY] = "[ ]";
                        }

                        posX++;
                        mapBuffer = map[posX][posY];
                        map[posX][posY] = mapPlayer;
                        System.out.println("\nVous vous êtes déplacé vers le " + ANSI_BLUE + "bas" + ANSI_RESET);
                    }
                    break;
                case 'D':
                    if (posY < MAP_SIZE - 1 && !(map[posX][posY + 1]).equals(mapWall)) {
                        // Si le joueur ne ramasse pas l'argent on remet l'argent sur la map
                        if (mapBuffer.equals(mapMoney)) {
                            map[posX][posY] = mapMoney;
                        } else if (mapBuffer.equals(mapStore)) {
                            map[posX][posY] = mapStore;
                        } else if (mapBuffer.equals(mapObstacle)) {
                            map[posX][posY] = mapObstacle;
                        } else if (mapBuffer.equals(mapMonster)) {
                            map[posX][posY] = mapMonster;
                        } else {
                            map[posX][posY] = "[ ]";
                        }

                        posY++;
                        mapBuffer = map[posX][posY];
                        map[posX][posY] = mapPlayer;
                        System.out.println("\nVous vous êtes déplacé vers la " + ANSI_BLUE + "droite" + ANSI_RESET);
                    }
                    break;
                case 'R':
                    if ((mapBuffer).equals(mapMoney)) {
                        mapBuffer = "";

                        // Le joueur gagne un montant aléatoire entre 0 et 30$
                        int max = 30;
                        double randomDouble = Math.random() * max;
                        double winMoney = (int) Math.round(randomDouble);

                        player.addMoney(winMoney);

                        Thread.sleep(300);
                        System.out.println("\nVous venez de ramasser " + ANSI_BLUE + winMoney + ANSI_RESET + " !");
                        System.out.println("Votre nouveau solde est de " + ANSI_BLUE + player.getMoney() + "$" + ANSI_RESET);
                        Thread.sleep(2000);
                    }
                    break;
                case 'V':
                    System.out.println("\n================================");
                    System.out.println("Catalogue de la boutique :");
                    System.out.println("================================");
                    Thread.sleep(1000);
                    System.out.println(store.getWeaponList());

                    String playAction;

                    do {
                        System.out.println("Que souhaitez-vous faire ?");
                        System.out.println("A : Acheter une nouvelle arme");
                        System.out.println("S : Sortir de la boutique");
                        playAction = scanner.nextLine();

                        if (playAction.equals("A")) {
                            System.out.print("Veuillez entrer l'ID de l'arme que vous souhaitez acheter : ");
                            String idWeapon = scanner.nextLine();

                            Weapon playchoosenWeapon = store.getWeaponOfStore(idWeapon);
                            if (playchoosenWeapon != null) {
                                player.buyWeapon(store, playchoosenWeapon);
                            } else {
                                System.out.println("L'ID entré ne correspond à aucune arme.");
                            }

                        } else if (!playAction.equals("S")) {
                            System.out.println(player.getName() + ", l'action demandée n'existe pas");
                        }
                    } while (!playAction.equals("S"));

                    break;
                case 'L':
                    System.out.println("Obstacle attaqué");
                    break;
                case 'I':
                    System.out.println(player);
                    break;
            }
        }

        System.out.println("\n" + ANSI_CYAN + "Félicitation vous avez terminer le jeux !" + ANSI_RESET);

        scanner.close();
    }
}
