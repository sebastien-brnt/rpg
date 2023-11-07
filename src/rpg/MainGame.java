package rpg;

import rpg.destructible.Destructible;
import rpg.destructible.Monster;
import rpg.destructible.Obstacle;
import rpg.map.Map;
import rpg.player.Player;
import rpg.store.WeaponStore;
import rpg.weapons.Weapon;
import rpg.utility.AnsiColors;


import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) throws InterruptedException {

        // Éléments de map
        final int MAP_SIZE = 10;
        final String mapPlayer = "[" + AnsiColors.BLUE + "X" + AnsiColors.RESET + "]";
        final String mapMoney = "[" + AnsiColors.GREEN + "$" + AnsiColors.RESET + "]";
        final String mapObstacle = "[" + AnsiColors.YELLOW + "O" + AnsiColors.RESET + "]";
        final String mapMonster = "[" + AnsiColors.RED + "M" + AnsiColors.RESET + "]";
        final String mapFinish = "[" + AnsiColors.CYAN + "#" + AnsiColors.RESET + "]";
        final String mapStore = "[" + AnsiColors.PURPLE + "B" + AnsiColors.RESET + "]";
        final String mapWall = "[" + AnsiColors.YELLOW + "=" + AnsiColors.RESET + "]";

        Scanner scanner = new Scanner(System.in);

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
        System.out.println("\nEnchanté " + AnsiColors.BLUE + name +  AnsiColors.RESET + ", vous possédé désormais " + AnsiColors.BLUE + "100 PV" + AnsiColors.RESET + " ainsi que " + AnsiColors.BLUE + "150$" + AnsiColors.RESET + " !");
        System.out.println("Votre mission est la suivante : Vous devez arriver au coin inférieur droit de la map représenté par " + mapFinish + " !");


        Thread.sleep(2500);

        // Affichage du catalogue de la boutique
        WeaponStore store = new WeaponStore();
        store.displayCatalogue();

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

        Map laMap = new Map(player, posX, posY);

        Object[][] map = laMap.getMap();

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
                        System.out.println("\nVous vous êtes déplacé vers le " + AnsiColors.BLUE + "haut" + AnsiColors.RESET);
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
                        System.out.println("\nVous vous êtes déplacé vers la " + AnsiColors.BLUE + "gauche" + AnsiColors.RESET);
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
                        System.out.println("\nVous vous êtes déplacé vers le " + AnsiColors.BLUE + "bas" + AnsiColors.RESET);
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
                        System.out.println("\nVous vous êtes déplacé vers la " + AnsiColors.BLUE + "droite" + AnsiColors.RESET);
                    } else {
                        System.out.println("\n" + player.getName() + ", l'action demandée n'est pas disponible");
                    }
                    break;
                case 'R':
                    if ((mapBuffer).equals(mapMoney)) {
                        mapBuffer = "";

                        // Le joueur gagne un montant aléatoire entre 1 et 30$
                        int min = 1;
                        int max = 30;
                        int winMoney = (int) Math.round(Math.random() * (max - min)) + min;

                        player.addMoney(winMoney);

                        Thread.sleep(300);
                        System.out.println("\nVous venez de ramasser " + AnsiColors.BLUE + winMoney + "$" + AnsiColors.RESET + " !");
                        System.out.println("Votre nouveau solde est de " + AnsiColors.BLUE + player.getMoney() + "$" + AnsiColors.RESET);
                        Thread.sleep(1200);
                    } else {
                        System.out.println("\n" + player.getName() + ", l'action demandée n'est pas disponible");
                    }
                    break;
                case 'V':
                    // Affichage du catalogue de la boutique
                    store.displayCatalogue();

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
                            Thread.sleep(300);
                            System.out.println(AnsiColors.BLUE + "\nL'obstacle est détruit !" + AnsiColors.RESET);
                            player.addXp(20);
                            System.out.println("Vous avez gagné " + AnsiColors.BLUE + "20 XP" + AnsiColors.RESET + "!");
                            Thread.sleep(1200);
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
                        Thread.sleep(300);
                        monster.attackPlayer(player);
                        Thread.sleep(300);
                        if (monster.getPv() <= 0) {
                            System.out.println(AnsiColors.BLUE + "\nLe monstre est mort !" + AnsiColors.RESET);
                            player.addXp(120);
                            System.out.println("Vous avez gagné " + AnsiColors.BLUE + "120 XP" + AnsiColors.RESET + "!");
                            Thread.sleep(300);
                            player.addPv(20);
                            System.out.println("Vous avez gagné " + AnsiColors.BLUE + "20 PV" + AnsiColors.RESET + "!");
                            Thread.sleep(1200);
                            map[monsterX][monsterY] = "[ ]"; // Remplace l'obstacle par un espace vide
                        }
                    } else {
                        System.out.println("\nIl n'y a pas de monstre à attaquer !");
                    }

                    break;
                case 'I':
                    System.out.println(player.getInformation());
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
            System.out.println("\n" + AnsiColors.RED + "Vous êtes mort ! Vous n'avez pas réussi votre mission !" + AnsiColors.RESET);
        }
        if (mapBuffer.equals(mapFinish)) {
            System.out.println("\n" + AnsiColors.CYAN + "Félicitation vous avez terminé le jeu !" + AnsiColors.RESET);
        }

        scanner.close();
    }
}
