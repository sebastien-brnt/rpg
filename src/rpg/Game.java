package rpg;

import rpg.destructible.Monster;
import rpg.destructible.Obstacle;
import rpg.map.Map;
import rpg.player.Player;
import rpg.store.WeaponStore;
import rpg.utility.AnsiColors;
import rpg.weapons.Weapon;

import java.util.Scanner;

public class Game {

    // Position initiale du joueur
    private int posX = 1;
    private int posY = 0;

    public Game() {
        System.out.println("Game");
    }

    public void start() throws InterruptedException {
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

        // Démarrage du jeu
        this.gameLogic(scanner);

        // Fermeture du scanner
        scanner.close();
    }

    public void gameLogic(Scanner scanner) throws InterruptedException {
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
        laMap.getMission();

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

            String playerCmd = scanner.nextLine();
            char move = ' ';

            if (!playerCmd.isEmpty()) {
                move = playerCmd.toUpperCase().charAt(0);
            }

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
                        System.out.println("\nQue souhaitez-vous faire ?");
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
                    int[] obstaclePosition = player.findAdjacentEntityPosition(posX, posY, Obstacle.class, laMap, map);

                    if (obstaclePosition != null) {
                        Obstacle obstacle = (Obstacle) map[obstaclePosition[0]][obstaclePosition[1]];
                        player.attackDestructible(obstacle);
                        Thread.sleep(300);

                        if (obstacle.getPv() <= 0) {
                            Thread.sleep(300);
                            System.out.println(AnsiColors.BLUE + "\nL'obstacle est détruit !" + AnsiColors.RESET);

                            // Ajout de l'XP au joueur
                            player.addXp(20, true);

                            Thread.sleep(1200);

                            // Remplace l'obstacle par un espace vide
                            laMap.resetLocation(obstaclePosition[0], obstaclePosition[1]);
                        }
                    } else {
                        System.out.println("Il n'y a pas d'obstacle à attaquer !");
                    }
                    break;
                case 'K':
                    // Détermine l'emplacement de l'obstacle à attaquer
                    int[] monsterPosition = player.findAdjacentEntityPosition(posX, posY, Monster.class, laMap, map);

                    if (monsterPosition != null) {
                        Monster monster = (Monster) map[monsterPosition[0]][monsterPosition[1]];
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
                            laMap.resetLocation(monsterPosition[0], monsterPosition[1]);
                        }
                    } else {
                        System.out.println("\nIl n'y a pas de monstre à attaquer !");
                    }
                    break;
                case 'I':
                    player.displayInformation(scanner);
                    break;

                case '*':
                    laMap.displayMapLegend(scanner);
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
    }
}
