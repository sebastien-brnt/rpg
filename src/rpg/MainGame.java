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

        // Position initiale du joueur
        int posX = 1;
        int posY = 0;

        // Initialisation du scanner
        Scanner scanner = new Scanner(System.in);

        // Affichage du titre du jeu
        for (int i = 0; i < 6; i++) {
            if (i == 3) {
                System.out.println("=====================================");
                System.out.println("             " + AnsiColors.PURPLE + "RPG JAVA" + AnsiColors.RESET);
                System.out.println("          " + AnsiColors.YELLOW + "By Sébastien B." + AnsiColors.RESET);
                System.out.println("=====================================");
            } else {
                System.out.println();
            }
        }

        // Récupération du nom du joueur
        System.out.println("=====================================");
        System.out.println("Bienvenue dans votre nouveau monde !");
        System.out.println("=====================================");
        System.out.print("Comment vous appelez vous ? ");
        String name = scanner.nextLine();

        // Initialisation du joueur et de la map
        Player player = new Player(name, 100, 150, 0);
        Map laMap = new Map(player, posX, posY);

        Thread.sleep(300);

        // Affichage des informations du joueur
        System.out.println("\nEnchanté " + AnsiColors.BLUE + player.getName() +  AnsiColors.RESET + ", vous possédé désormais " + AnsiColors.BLUE + player.getPv() + " PV" + AnsiColors.RESET + " ainsi que " + AnsiColors.BLUE + + player.getMoney() + "$" + AnsiColors.RESET + " !");
        System.out.println("Votre mission est la suivante : Vous devez arriver au coin inférieur droit de la map représenté par " + laMap.getMapFinish() + " !");

        Thread.sleep(1500);

        // Affichage du catalogue de la boutique
        WeaponStore store = new WeaponStore();
        store.displayCatalogue();

        Thread.sleep(800);

        // Choix de la première arme
        System.out.println("\n================================");
        System.out.println("Choissisez votre première arme :");
        System.out.println("================================");

        Weapon chosenWeapon;

        do {
            System.out.print("\nVeuillez entrer l'ID de l'arme que vous souhaitez acheter : ");
            String firstWeapon = scanner.nextLine();

            // Récupération de l'arme dans le store
            chosenWeapon = store.getWeaponOfStore(firstWeapon);

            if (chosenWeapon != null) {
                // Achat de l'arme
                player.buyWeapon(store, chosenWeapon);
            } else {
                System.out.println("Arme non disponible dans la boutique. Veuillez choisir une arme du catalogue.");
            }

        } while (chosenWeapon == null);

        // Sélection de l'arme choisie
        player.selectWeapon(chosenWeapon);

        Thread.sleep(2000);

        // Début du jeu
        System.out.println("\n================================");
        System.out.println("             MAP                ");
        System.out.println("================================");

        Object[][] map = laMap.getMap();

        Object mapBuffer = "";

        while (!mapBuffer.equals(laMap.getMapFinish()) && player.getPv() > 0) {

            // Affichage de la map
            laMap.displayMap();

            System.out.println("\nCommandes disponibles :");
            if (!(posX + 1 > laMap.getMapSize() - 1) && !(map[posX + 1][posY]).equals(laMap.getMapWall()) && !(map[posX + 1][posY] instanceof Destructible) ) {
                System.out.println("S (bas)");
            }
            if ((posY - 1) >= 0 && !(map[posX][posY - 1]).equals(laMap.getMapWall()) && !(map[posX][posY - 1] instanceof Destructible) ) {
                System.out.println("Q (gauche)");
            }
            if (!(posY + 1 > laMap.getMapSize() - 1) && !(map[posX][posY + 1]).equals(laMap.getMapWall()) && !(map[posX][posY + 1] instanceof Destructible) ) {
                System.out.println("D (droite)");
            }
            if ((posX - 1) >= 0 && !(map[posX - 1][posY]).equals(laMap.getMapWall()) && !(map[posX - 1][posY] instanceof Destructible) ) {
                System.out.println("Z (haut)");
            }
            if (mapBuffer.equals(laMap.getMapMoney())) {
                System.out.println("R (Ramasser de l'argent)");
            }
            if ( (!(posX + 1 > laMap.getMapSize() - 1) && map[posX + 1][posY] instanceof Obstacle ) ||
                 ((posX - 1) >= 0 && map[posX - 1][posY] instanceof Obstacle ) ||
                 (!(posY + 1 > laMap.getMapSize() - 1) && map[posX][posY + 1]  instanceof Obstacle ) ||
                 ((posY - 1) >= 0 && map[posX][posY - 1] instanceof Obstacle ) )  {
                System.out.println("L (Attaquer l'obstacle)");
            }
            if ( (!(posX + 1 > laMap.getMapSize() - 1) && map[posX + 1][posY] instanceof Monster ) ||
                    ((posX - 1) >= 0 && map[posX - 1][posY] instanceof Monster ) ||
                    (!(posY + 1 > laMap.getMapSize() - 1) && map[posX][posY + 1]  instanceof Monster ) ||
                    ((posY - 1) >= 0 && map[posX][posY - 1] instanceof Monster ) )  {
                System.out.println("K (Attaquer le monstre)");
            }
            if (mapBuffer.equals(laMap.getMapStore())) {
                System.out.println("V (Visiter la boutique)");
            }

            System.out.println("I (Voir mes information et mon inventaire)");
            System.out.println(": (Quitter le jeu)");
            System.out.println("* (Légende de la map)");

            char move = scanner.nextLine().toUpperCase().charAt(0);
            if (move == ':') break;

            switch (move) {
                case 'Z':
                    if (posX >= 0 && (posX - 1) >= 0 && !(map[posX - 1][posY]).equals(laMap.getMapWall()) && !(map[posX - 1][posY] instanceof Destructible)) {
                        // Si le joueur ne ramasse pas l'argent on remet l'argent sur la map
                        if (mapBuffer.equals(laMap.getMapMoney())) {
                            map[posX][posY] = laMap.getMapMoney();
                        } else if (mapBuffer.equals(laMap.getMapStore())) {
                            map[posX][posY] = laMap.getMapStore();
                        } else if (mapBuffer instanceof Destructible) {
                            map[posX][posY] = mapBuffer;
                        } else if (mapBuffer.equals(laMap.getMapMonster())) {
                            map[posX][posY] = laMap.getMapMonster();
                        } else {
                            map[posX][posY] = "[ ]";
                        }

                        posX--;
                        mapBuffer = map[posX][posY];
                        map[posX][posY] = player;
                        System.out.println("\nVous vous êtes déplacé vers le " + AnsiColors.BLUE + "haut" + AnsiColors.RESET);
                    } else {
                        System.out.println("\n" + player.getName() + ", l'action demandée n'est pas disponible");
                    }
                    break;

                case 'Q':
                    if (posY >= 0 && (posY - 1) >= 0 && !(map[posX][posY - 1]).equals(laMap.getMapWall()) && !(map[posX][posY - 1] instanceof Destructible)) {
                        // Si le joueur ne ramasse pas l'argent on remet l'argent sur la map
                        if (mapBuffer.equals(laMap.getMapMoney())) {
                            map[posX][posY] = laMap.getMapMoney();
                        } else if (mapBuffer.equals(laMap.getMapStore())) {
                            map[posX][posY] = laMap.getMapStore();
                        } else if (mapBuffer instanceof Destructible) {
                            map[posX][posY] = mapBuffer;
                        } else if (mapBuffer.equals(laMap.getMapMonster())) {
                            map[posX][posY] = laMap.getMapMonster();
                        } else {
                            map[posX][posY] = "[ ]";
                        }

                        posY--;
                        mapBuffer = map[posX][posY];
                        map[posX][posY] = player;
                        System.out.println("\nVous vous êtes déplacé vers la " + AnsiColors.BLUE + "gauche" + AnsiColors.RESET);
                    } else {
                        System.out.println("\n" + player.getName() + ", l'action demandée n'est pas disponible");
                    }
                    break;

                case 'S':
                    if (posX < laMap.getMapSize() - 1 && !(map[posX + 1][posY]).equals(laMap.getMapWall()) && !(map[posX + 1][posY] instanceof Destructible)) {
                        // Si le joueur ne ramasse pas l'argent on remet l'argent sur la map
                        if (mapBuffer.equals(laMap.getMapMoney())) {
                            map[posX][posY] = laMap.getMapMoney();
                        } else if (mapBuffer.equals(laMap.getMapStore())) {
                            map[posX][posY] = laMap.getMapStore();
                        } else if (mapBuffer instanceof Destructible) {
                            map[posX][posY] = mapBuffer;
                        } else if (mapBuffer.equals(laMap.getMapMonster())) {
                            map[posX][posY] = laMap.getMapMonster();
                        } else {
                            map[posX][posY] = "[ ]";
                        }

                        posX++;
                        mapBuffer = map[posX][posY];
                        map[posX][posY] = player;
                        System.out.println("\nVous vous êtes déplacé vers le " + AnsiColors.BLUE + "bas" + AnsiColors.RESET);
                    } else {
                        System.out.println("\n" + player.getName() + ", l'action demandée n'est pas disponible");
                    }
                    break;

                case 'D':
                    if (posY < laMap.getMapSize() - 1 && !(map[posX][posY + 1]).equals(laMap.getMapWall()) && !(map[posX][posY + 1] instanceof Destructible)) {
                        // Si le joueur ne ramasse pas l'argent on remet l'argent sur la map
                        if (mapBuffer.equals(laMap.getMapMoney())) {
                            map[posX][posY] = laMap.getMapMoney();
                        } else if (mapBuffer.equals(laMap.getMapStore())) {
                            map[posX][posY] = laMap.getMapStore();
                        } else if (mapBuffer instanceof Destructible) {
                            map[posX][posY] = mapBuffer;
                        } else if (mapBuffer.equals(laMap.getMapMonster())) {
                            map[posX][posY] = laMap.getMapMonster();
                        } else {
                            map[posX][posY] = "[ ]";
                        }

                        posY++;
                        mapBuffer = map[posX][posY];
                        map[posX][posY] = player;
                        System.out.println("\nVous vous êtes déplacé vers la " + AnsiColors.BLUE + "droite" + AnsiColors.RESET);
                    } else {
                        System.out.println("\n" + player.getName() + ", l'action demandée n'est pas disponible");
                    }
                    break;
                case 'R':
                    if ((mapBuffer).equals(laMap.getMapMoney())) {
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

                    if (!(posX + 1 > laMap.getMapSize() - 1) && map[posX + 1][posY] instanceof Obstacle) {
                        obstacle = (Obstacle) map[posX + 1][posY];
                        obstacleX = posX + 1;
                    } else if ((posX - 1) >= 0 && map[posX - 1][posY] instanceof Obstacle) {
                        obstacle = (Obstacle) map[posX - 1][posY];
                        obstacleX = posX - 1;
                    } else if (!(posY + 1 > laMap.getMapSize() - 1) && map[posX][posY + 1] instanceof Obstacle) {
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

                    if (!(posX + 1 > laMap.getMapSize() - 1) && map[posX + 1][posY] instanceof Monster) {
                        monster = (Monster) map[posX + 1][posY];
                        monsterX = posX + 1;
                    } else if ((posX - 1) >= 0 && map[posX - 1][posY] instanceof Monster) {
                        monster = (Monster) map[posX - 1][posY];
                        monsterX = posX - 1;
                    } else if (!(posY + 1 > laMap.getMapSize() - 1) && map[posX][posY + 1] instanceof Monster) {
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
                    System.out.println(player + ": Vous");
                    System.out.println(laMap.getMapMoney() + ": Argent (Entre 1$ et 30$)");
                    System.out.println(laMap.getMapWall() + ": Murs");
                    System.out.println(laMap.getMapObstacle() + ": Obstacles (Rocher, Arbre ...)");
                    System.out.println(laMap.getMapMonster() + ": Monstres");
                    System.out.println(laMap.getMapFinish() + ": Objectif");
                    break;

                default:
                    System.out.println("\n" + player.getName() + ", l'action demandée n'existe pas");
                    break;
            }
        }

        if (player.getPv() <= 0) {
            System.out.println("\n" + AnsiColors.RED + "Vous êtes mort ! Vous n'avez pas réussi votre mission !" + AnsiColors.RESET);
        }
        if (mapBuffer.equals(laMap.getMapFinish())) {
            System.out.println("\n" + AnsiColors.CYAN + "Félicitation vous avez terminé le jeu !" + AnsiColors.RESET);
        }

        scanner.close();
    }
}
