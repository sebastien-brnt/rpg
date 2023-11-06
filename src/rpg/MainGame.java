package rpg;

import rpg.destructible.Destructible;
import rpg.destructible.Monster;
import rpg.destructible.Obstacle;
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
        Monster monster2 = new Monster("Monster 2", 25);
        Monster monster3 = new Monster("Monster 2", 20);
        Monster monster4 = new Monster("Monster 4", 45);
        Obstacle rock = new Obstacle("Rocher");
        Obstacle rock2 = new Obstacle("Rocher");
        Obstacle rock3 = new Obstacle("Rocher");
        Obstacle tree = new Obstacle("Arbre");
        Obstacle tree2 = new Obstacle("Arbre");

        for (int i = 0; i < 3; i++) {
            System.out.println();
        }

        System.out.println("=====================================");
        System.out.println("Bienvenue dans votre nouveau monde !");
        System.out.println("=====================================");
        System.out.print("Comment vous appelez vous ? ");
        String name = scanner.nextLine();

        Player player = new Player(name, 100, 150, 0);

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

        player.selectWeapon(choosenWeapon);

        Thread.sleep(2000);

        System.out.println("\n================================");
        System.out.println("               MAP              ");
        System.out.println("================================");

        // Position initiale du joueur
        int posX = 1;
        int posY = 0;

        // Initialisation de la map
        Object[][] map = {
                {mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall},
                { mapWall, mapMoney, mapWall, mapMoney, "[ ]", rock3, "[ ]", "[ ]", mapMoney, mapWall},
                {mapWall, "[ ]", mapWall, mapWall, "[ ]", monster2, "[ ]", "[ ]", "[ ]", mapWall},
                {mapWall, "[ ]", "[ ]", mapWall, "[ ]", "[ ]", mapWall, mapWall, monster3, mapWall},
                {mapWall, mapWall, rock, mapWall, "[ ]", "[ ]", mapWall, "[ ]", "[ ]", mapWall},
                {mapWall, "[ ]", "[ ]", mapWall, "[ ]", mapWall, mapWall, "[ ]", "[ ]", mapWall},
                {mapWall, tree, monster1, mapWall, "[ ]", mapWall, "[ ]", "[ ]", "[ ]", mapWall},
                {mapWall, "[ ]", "[ ]", tree2, rock2, mapWall, "[ ]", mapWall, mapWall, mapWall},
                {mapWall, mapMoney, mapWall, "[ ]", mapStore, mapWall, mapMoney, "[ ]", monster4, "[ ]"},
                {mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapWall, mapFinish}
        };


        map[posX][posY] = mapPlayer;

        Object mapBuffer = "";

        while (!mapBuffer.equals(mapFinish) && player.getPv() > 0) {
            System.out.println();

            // Affichage de la map
            for (int i = 0; i < MAP_SIZE; i++) {
                for (int j = 0; j < MAP_SIZE; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }

            System.out.println("\nCommandes disponibles :");
            if (!(posX + 1 > MAP_SIZE - 1) && !(map[posX + 1][posY]).equals(mapWall) && !(map[posX + 1][posY] instanceof Destructible) ) {
                System.out.println("S (bas)");
            }
            if ((posY - 1) >= 0 && !(map[posX][posY - 1]).equals(mapWall) && !(map[posX][posY - 1] instanceof Destructible) ) {
                System.out.println("Q (gauche)");
            }
            if (!(posY + 1 > MAP_SIZE - 1) && !(map[posX][posY + 1]).equals(mapWall) && !(map[posX][posY + 1] instanceof Destructible) ) {
                System.out.println("D (droite)");
            }
            if ((posX - 1) >= 0 && !(map[posX - 1][posY]).equals(mapWall) && !(map[posX - 1][posY] instanceof Destructible) ) {
                System.out.println("Z (haut)");
            }
            if (mapBuffer.equals(mapMoney)) {
                System.out.println("R (Ramasser de l'argent)");
            }
            if ( (!(posX + 1 > MAP_SIZE - 1) && map[posX + 1][posY] instanceof Obstacle ) ||
                 ((posX - 1) >= 0 && map[posX - 1][posY] instanceof Obstacle ) ||
                 (!(posY + 1 > MAP_SIZE - 1) && map[posX][posY + 1]  instanceof Obstacle ) ||
                 ((posY - 1) >= 0 && map[posX][posY - 1] instanceof Obstacle ) )  {
                System.out.println("L (Attaquer l'obstacle)");
            }
            if ( (!(posX + 1 > MAP_SIZE - 1) && map[posX + 1][posY] instanceof Monster ) ||
                    ((posX - 1) >= 0 && map[posX - 1][posY] instanceof Monster ) ||
                    (!(posY + 1 > MAP_SIZE - 1) && map[posX][posY + 1]  instanceof Monster ) ||
                    ((posY - 1) >= 0 && map[posX][posY - 1] instanceof Monster ) )  {
                System.out.println("K (Attaquer le monstre)");
            }
            if (mapBuffer.equals(mapStore)) {
                System.out.println("V (Visiter la boutique)");
            }

            System.out.println("I (Voir mes information et mon inventaire)");
            System.out.println(": (Quitter le jeu)");
            System.out.println("* (Légende de la map)");

            char move = scanner.nextLine().toUpperCase().charAt(0);
            if (move == ':') break;

            switch (move) {
                case 'Z':
                    if (posX >= 0 && (posX - 1) >= 0 && !(map[posX - 1][posY]).equals(mapWall) && !(map[posX - 1][posY] instanceof Destructible)) {
                        // Si le joueur ne ramasse pas l'argent on remet l'argent sur la map
                        if (mapBuffer.equals(mapMoney)) {
                            map[posX][posY] = mapMoney;
                        } else if (mapBuffer.equals(mapStore)) {
                            map[posX][posY] = mapStore;
                        } else if (mapBuffer instanceof Destructible) {
                            map[posX][posY] = mapBuffer;
                        } else if (mapBuffer.equals(mapMonster)) {
                            map[posX][posY] = mapMonster;
                        } else {
                            map[posX][posY] = "[ ]";
                        }

                        posX--;
                        mapBuffer = map[posX][posY];
                        map[posX][posY] = mapPlayer;
                        System.out.println("\nVous vous êtes déplacé vers le " + ANSI_BLUE + "haut" + ANSI_RESET);
                    } else {
                        System.out.println("\n" + player.getName() + ", l'action demandée n'est pas disponible");
                    }
                    break;

                case 'Q':
                    if (posY >= 0 && (posY - 1) >= 0 && !(map[posX][posY - 1]).equals(mapWall) && !(map[posX][posY - 1] instanceof Destructible)) {
                        // Si le joueur ne ramasse pas l'argent on remet l'argent sur la map
                        if (mapBuffer.equals(mapMoney)) {
                            map[posX][posY] = mapMoney;
                        } else if (mapBuffer.equals(mapStore)) {
                            map[posX][posY] = mapStore;
                        } else if (mapBuffer instanceof Destructible) {
                            map[posX][posY] = mapBuffer;
                        } else if (mapBuffer.equals(mapMonster)) {
                            map[posX][posY] = mapMonster;
                        } else {
                            map[posX][posY] = "[ ]";
                        }

                        posY--;
                        mapBuffer = map[posX][posY];
                        map[posX][posY] = mapPlayer;
                        System.out.println("\nVous vous êtes déplacé vers la " + ANSI_BLUE + "gauche" + ANSI_RESET);
                    } else {
                        System.out.println("\n" + player.getName() + ", l'action demandée n'est pas disponible");
                    }
                    break;

                case 'S':
                    if (posX < MAP_SIZE - 1 && !(map[posX + 1][posY]).equals(mapWall) && !(map[posX + 1][posY] instanceof Destructible)) {
                        // Si le joueur ne ramasse pas l'argent on remet l'argent sur la map
                        if (mapBuffer.equals(mapMoney)) {
                            map[posX][posY] = mapMoney;
                        } else if (mapBuffer.equals(mapStore)) {
                            map[posX][posY] = mapStore;
                        } else if (mapBuffer instanceof Destructible) {
                            map[posX][posY] = mapBuffer;
                        } else if (mapBuffer.equals(mapMonster)) {
                            map[posX][posY] = mapMonster;
                        } else {
                            map[posX][posY] = "[ ]";
                        }

                        posX++;
                        mapBuffer = map[posX][posY];
                        map[posX][posY] = mapPlayer;
                        System.out.println("\nVous vous êtes déplacé vers le " + ANSI_BLUE + "bas" + ANSI_RESET);
                    } else {
                        System.out.println("\n" + player.getName() + ", l'action demandée n'est pas disponible");
                    }
                    break;

                case 'D':
                    if (posY < MAP_SIZE - 1 && !(map[posX][posY + 1]).equals(mapWall) && !(map[posX][posY + 1] instanceof Destructible)) {
                        // Si le joueur ne ramasse pas l'argent on remet l'argent sur la map
                        if (mapBuffer.equals(mapMoney)) {
                            map[posX][posY] = mapMoney;
                        } else if (mapBuffer.equals(mapStore)) {
                            map[posX][posY] = mapStore;
                        } else if (mapBuffer instanceof Destructible) {
                            map[posX][posY] = mapBuffer;
                        } else if (mapBuffer.equals(mapMonster)) {
                            map[posX][posY] = mapMonster;
                        } else {
                            map[posX][posY] = "[ ]";
                        }

                        posY++;
                        mapBuffer = map[posX][posY];
                        map[posX][posY] = mapPlayer;
                        System.out.println("\nVous vous êtes déplacé vers la " + ANSI_BLUE + "droite" + ANSI_RESET);
                    } else {
                        System.out.println("\n" + player.getName() + ", l'action demandée n'est pas disponible");
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
                    } else {
                        System.out.println("\n" + player.getName() + ", l'action demandée n'est pas disponible");
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
                    // Détermine l'emplacement de l'obstacle à attaquer
                    Obstacle obstacle = null;
                    int obstacleX = posX, obstacleY = posY; // Initialisation des coordonnées de l'obstacle

                    if (!(posX + 1 > MAP_SIZE - 1) && map[posX + 1][posY] instanceof Obstacle) {
                        obstacle = (Obstacle) map[posX + 1][posY];
                        obstacleX = posX + 1;
                    } else if ((posX - 1) >= 0 && map[posX - 1][posY] instanceof Obstacle) {
                        obstacle = (Obstacle) map[posX - 1][posY];
                        obstacleX = posX - 1;
                    } else if (!(posY + 1 > MAP_SIZE - 1) && map[posX][posY + 1] instanceof Obstacle) {
                        obstacle = (Obstacle) map[posX][posY + 1];
                        obstacleY = posY + 1;
                    } else if ((posY - 1) >= 0 && map[posX][posY - 1] instanceof Obstacle) {
                        obstacle = (Obstacle) map[posX][posY - 1];
                        obstacleY = posY - 1;
                    }

                    // Attaquez l'obstacle si trouvé
                    if (obstacle != null) {
                        player.attackDestructible(obstacle);
                        if (obstacle.getPv() <= 0) {
                            System.out.println(ANSI_BLUE + "\nL'obstacle est détruit !" + ANSI_RESET);
                            player.addXp(20);
                            System.out.println("Vous avez gagné " + ANSI_BLUE + "20 XP" + ANSI_RESET + "!");
                            map[obstacleX][obstacleY] = "[ ]"; // Remplace l'obstacle par un espace vide
                        }
                    } else {
                        System.out.println("Il n'y a pas d'obstacle à attaquer !");
                    }

                    break;
                case 'K':
                    // Détermine l'emplacement de l'obstacle à attaquer
                    Monster monster = null;
                    int monsterX = posX, monsterY = posY; // Initialisation des coordonnées de l'obstacle

                    if (!(posX + 1 > MAP_SIZE - 1) && map[posX + 1][posY] instanceof Monster) {
                        monster = (Monster) map[posX + 1][posY];
                        monsterX = posX + 1;
                    } else if ((posX - 1) >= 0 && map[posX - 1][posY] instanceof Monster) {
                        monster = (Monster) map[posX - 1][posY];
                        monsterX = posX - 1;
                    } else if (!(posY + 1 > MAP_SIZE - 1) && map[posX][posY + 1] instanceof Monster) {
                        monster = (Monster) map[posX][posY + 1];
                        monsterY = posY + 1;
                    } else if ((posY - 1) >= 0 && map[posX][posY - 1] instanceof Monster) {
                        monster = (Monster) map[posX][posY - 1];
                        monsterY = posY - 1;
                    }

                    // Attaquez l'obstacle si trouvé
                    if (monster != null) {
                        player.attackDestructible(monster);
                        monster.attackPlayer(player);
                        if (monster.getPv() <= 0) {
                            System.out.println(ANSI_BLUE + "\nLe monstre est mort !" + ANSI_RESET);
                            player.addXp(120);
                            System.out.println("Vous avez gagné " + ANSI_BLUE + "120 XP" + ANSI_RESET + "!");
                            map[monsterX][monsterY] = "[ ]"; // Remplace l'obstacle par un espace vide
                        }
                    } else {
                        System.out.println("Il n'y a pas de monstre à attaquer !");
                    }

                    break;
                case 'I':
                    System.out.println(player);
                    break;

                case '*':
                    System.out.println("\n================================");
                    System.out.println("Légende de la map");
                    System.out.println("================================");
                    System.out.println(mapPlayer + ": Vous");
                    System.out.println(mapMoney + ": Argent (Entre 0$ et 30$)");
                    System.out.println(mapWall + ": Murs");
                    System.out.println(mapObstacle + ": Obstacles (Rocher, Arbre ...)");
                    System.out.println(mapMonster + ": Monstres");
                    System.out.println(mapFinish + ": Objectif");
                    break;

                default:
                    System.out.println("\n" + player.getName() + ", l'action demandée n'existe pas");
                    break;
            }
        }

        if (player.getPv() <= 0) {
            System.out.println("\n" + ANSI_CYAN + "Vous êtes mort ! Vous n'avez pas réussi votre mission !" + ANSI_RESET);
        }
        if (mapBuffer.equals(mapFinish)) {
            System.out.println("\n" + ANSI_CYAN + "Félicitation vous avez terminer le jeux !" + ANSI_RESET);
        }

        scanner.close();
    }
}
