package rpg;

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
        System.out.println("\nEnchanté " + AnsiColors.BLUE + player.getName() +  AnsiColors.RESET + ", vous possédé désormais " + AnsiColors.CYAN + player.getPv() + " PV" + AnsiColors.RESET + " ainsi que " + AnsiColors.GREEN + + player.getMoney() + "$" + AnsiColors.RESET + " !");
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
            System.out.print("Veuillez entrer l'ID de l'arme que vous souhaitez acheter : ");
            String firstWeapon = scanner.nextLine();

            // Récupération de l'arme dans le store
            chosenWeapon = store.getWeaponOfStore(firstWeapon);

            if (chosenWeapon != null) {
                // Achat de l'arme
                player.buyWeapon(store, chosenWeapon);
            } else {
                System.out.println("Arme non disponible dans la boutique. Veuillez choisir une arme du catalogue.\n");
            }

        } while (chosenWeapon == null);

        // Sélection de l'arme choisie
        player.selectWeapon(chosenWeapon);

        Thread.sleep(2000);

        // Début du jeu
        System.out.println("\n================================");
        System.out.println("          Début du jeu          ");
        System.out.println("================================");

        // Récupération de la map
        Object[][] map = laMap.getMap();

        // Boucle de jeu
        while (!laMap.getMapBuffer().equals(laMap.getMapFinish()) && player.getPv() > 0) {

            // Affichage de la map
            laMap.displayMap();

            // Affichage des commandes disponibles pour le joueur
            player.displayAvailableCommand(laMap, posX, posY);

            char move = scanner.nextLine().toUpperCase().charAt(0);

            // Le joueur quitte, le jeu on met fin à la boucle
            if (move == ':') {
                System.out.println("\n" + AnsiColors.RED + "Vous avez quitté le jeu !" + AnsiColors.RESET);
                break;
            }

            switch (move) {
                case 'Z':
                    if (laMap.getMove(posX - 1, posY)) {
                        // Si le joueur était sur un item qu'il n'a pas récupéré on remet l'item sur la map
                        laMap.replaceItemInMap(posX, posY);

                        posX--;
                        laMap.setMapBuffer(map[posX][posY]);
                        laMap.definePlayerPosition(player, posX, posY);
                        System.out.println("\nVous vous êtes déplacé vers le " + AnsiColors.BLUE + "haut" + AnsiColors.RESET);
                    } else {
                        // L'action demandée n'est pas disponible
                        player.commandNotAvailable();
                    }
                    break;

                case 'Q':
                    if (laMap.getMove(posX, posY - 1)) {
                        // Si le joueur était sur un item qu'il n'a pas récupéré on remet l'item sur la map
                        laMap.replaceItemInMap(posX, posY);

                        posY--;
                        laMap.setMapBuffer(map[posX][posY]);
                        laMap.definePlayerPosition(player, posX, posY);
                        System.out.println("\nVous vous êtes déplacé vers la " + AnsiColors.BLUE + "gauche" + AnsiColors.RESET);
                    } else {
                        System.out.println("\n" + player.getName() + ", l'action demandée n'est pas disponible");
                    }
                    break;

                case 'S':
                    if (laMap.getMove(posX + 1, posY)) {
                        // Si le joueur était sur un item qu'il n'a pas récupéré on remet l'item sur la map
                        laMap.replaceItemInMap(posX, posY);

                        posX++;
                        laMap.setMapBuffer(map[posX][posY]);
                        laMap.definePlayerPosition(player, posX, posY);
                        System.out.println("\nVous vous êtes déplacé vers le " + AnsiColors.BLUE + "bas" + AnsiColors.RESET);
                    } else {
                        // L'action demandée n'est pas disponible
                        player.commandNotAvailable();
                    }
                    break;

                case 'D':
                    if (laMap.getMove(posX, posY + 1)) {
                        // Si le joueur était sur un item qu'il n'a pas récupéré on remet l'item sur la map
                        laMap.replaceItemInMap(posX, posY);

                        posY++;
                        laMap.setMapBuffer(map[posX][posY]);
                        laMap.definePlayerPosition(player, posX, posY);
                        System.out.println("\nVous vous êtes déplacé vers la " + AnsiColors.BLUE + "droite" + AnsiColors.RESET);
                    } else {
                        // L'action demandée n'est pas disponible
                        player.commandNotAvailable();
                    }
                    break;
                case 'R':
                    if (laMap.getMapBuffer().equals(laMap.getMapMoney())) {
                        laMap.setMapBuffer("");

                        // Le joueur gagne un montant aléatoire entre 1 et 30$
                        int min = 1;
                        int max = 30;
                        int winMoney = (int) Math.round(Math.random() * (max - min)) + min;

                        player.addMoney(winMoney);
                        Thread.sleep(300);

                        // Affichage du gain d'argent et du nouveau solde
                        System.out.println("\nVous venez de ramasser " + AnsiColors.GREEN + winMoney + "$" + AnsiColors.RESET + " !");
                        System.out.println("Votre nouveau solde est de " + AnsiColors.GREEN + player.getMoney() + "$" + AnsiColors.RESET);
                        Thread.sleep(1200);
                    } else {
                        // L'action demandée n'est pas disponible
                        player.commandNotAvailable();
                    }
                    break;
                case 'V':
                    // Affichage du catalogue de la boutique
                    store.displayCatalogue();

                    String playerAction;

                    do {
                        // Affichage des commandes disponibles pour le joueur
                        System.out.println("Que souhaitez-vous faire ?");
                        System.out.println("[A] : Acheter une nouvelle arme");
                        System.out.println("[S] : Sortir de la boutique");
                        playerAction = scanner.nextLine();

                        if (playerAction.equals("A")) {
                            System.out.print("Veuillez entrer l'ID de l'arme que vous souhaitez acheter : ");
                            String idWeapon = scanner.nextLine();

                            Weapon playchoosenWeapon = store.getWeaponOfStore(idWeapon);
                            if (playchoosenWeapon != null) {
                                player.buyWeapon(store, playchoosenWeapon);
                            } else {
                                System.out.println("L'ID entré ne correspond à aucune arme.");
                            }

                        } else if (!playerAction.equals("S")) {
                            System.out.println(player.getName() + ", l'action demandée n'existe pas");
                        }
                    } while (!playerAction.equals("S"));

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

                            // Ajout de l'XP au joueur
                            player.addXp(20, true);

                            Thread.sleep(1200);

                            // Remplace l'obstacle par un espace vide
                            laMap.resetLocation(obstacleX, obstacleY);
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

                            // Ajout de l'XP au joueur
                            player.addXp(120, true);

                            // Ajout des PV au joueur
                            player.addPv(20, true);

                            Thread.sleep(1200);

                            // Remplace l'obstacle par un espace vide
                            laMap.resetLocation(monsterX, monsterY);
                        }
                    } else {
                        System.out.println("\nIl n'y a pas de monstre à attaquer !");
                    }

                    break;
                case 'I':
                    player.displayInformation(scanner);
                    break;

                case '*':
                    laMap.displayMapLegend();
                    break;

                default:
                    // L'action demandée n'est pas disponible
                    player.commandNotAvailable();
                    break;
            }
        }

        if (player.getPv() <= 0) {
            System.out.println("\n" + AnsiColors.RED + "Vous êtes mort ! Vous n'avez pas réussi votre mission !" + AnsiColors.RESET);
        }
        if (laMap.getMapBuffer().equals(laMap.getMapFinish())) {
            System.out.println("\n" + AnsiColors.CYAN + "Félicitation vous avez terminé le jeu !" + AnsiColors.RESET);
        }

        scanner.close();
    }
}
