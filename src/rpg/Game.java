package rpg;

import rpg.destructible.Destructible;
import rpg.destructible.Monster;
import rpg.destructible.Obstacle;
import rpg.map.Map;
import rpg.player.Archer;
import rpg.player.Mage;
import rpg.player.Player;
import rpg.player.Knight;
import rpg.store.WeaponStore;
import rpg.utility.AnsiColors;
import rpg.weapons.Weapon;

import java.util.Scanner;

public class Game {
    // Attributs

    // Position initiale du joueur
    private int posX, posY;

    // Joueur
    Player player;

    // Map
    Map theMap;

    // Store
    WeaponStore store = new WeaponStore();

    // Méthodes
    // Constructeur
    public Game(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    // Constructeur par défaut
    public Game() {
        this.posX = 1;
        this.posY = 0;
    }

    // Démmarage de la partie
    public void start() throws InterruptedException {
        // Initialisation du scanner
        Scanner scanner = new Scanner(System.in);

        // Affichage du titre du jeu
        this.displayGameTitle();

        // Initialisation du joueur
        this.initPlayer(scanner);

        // Choix de la première arme
        this.choseFirstWeapon(scanner);

        // Démarrage du jeu
        this.gameLogic(scanner);

        // Fermeture du scanner
        scanner.close();
    }

    // Affichage du titre du jeu
    public void displayGameTitle() {
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
    }

    // Initialisation du joueur
    public void initPlayer(Scanner scanner) throws InterruptedException {
        // Récupération du nom du joueur
        System.out.println("=====================================");
        System.out.println("Bienvenue dans votre nouveau monde !");
        System.out.println("=====================================");
        System.out.print("Comment vous appelez vous ? ");
        String name = scanner.nextLine();

        String role = "";

        System.out.println("\nChoississez votre rôle : ");
        System.out.println("[1] : " + AnsiColors.BLUE + "Chavlier" + AnsiColors.RESET + " (Vous avez 1 chance sur 4 d'infliger " + AnsiColors.PURPLE + "2 fois votre attaque" + AnsiColors.RESET + ")");
        System.out.println("[2] : " + AnsiColors.BLUE + "Archer" + AnsiColors.RESET + " (Vous gagnez " + AnsiColors.GREEN + "2,5$" + AnsiColors.RESET + " et " + AnsiColors.YELLOW + "10 XP" + AnsiColors.RESET + " par attaque)");
        System.out.println("[3] : " + AnsiColors.BLUE + "Mage" + AnsiColors.RESET + " (Vous avez 1 chance 2 de gagner " + AnsiColors.CYAN +  "5 PV" + AnsiColors.RESET + " lors de chaque attaque)");

        do {
            System.out.print("\nVotre choix : ");
            String roleId = scanner.nextLine();

            switch (roleId) {
                case "1" :
                    this.player = new Knight(name);
                    role = "Chevalier";
                    break;
                case "2" :
                    this.player = new Archer(name);
                    role = "Archer";
                    break;
                case "3" :
                    this.player = new Mage(name);
                    role = "Mage";
                    break;
                default:
                    System.out.println("Le rôle choisi n'existe pas !");
                    break;
            }
        } while (role.isEmpty());


        // Initialisation du joueur et de la map
        this.theMap = new Map(player, posX, posY);
        this.store = new WeaponStore();

        Thread.sleep(300);

        // Affichage des informations du joueur
        System.out.println("\nEnchanté " + AnsiColors.BLUE + player.getRole() + " " + player.getName() +  AnsiColors.RESET + ", vous possédé désormais " + AnsiColors.CYAN + player.getPv() + " PV" + AnsiColors.RESET + " ainsi que " + AnsiColors.GREEN + + player.getMoney() + "$" + AnsiColors.RESET + " !");
        this.theMap.getMission();

        Thread.sleep(3000);
    }

    // Choix de la première arme
    public void choseFirstWeapon(Scanner scanner) throws InterruptedException {
        // Affichage du catalogue de la boutique
        store = new WeaponStore();
        store.displayCatalogue();

        Thread.sleep(800);

        // Choix de la première arme
        System.out.println("\n================================");
        System.out.println("Choissisez votre première arme :");
        System.out.println("================================");

        Weapon chosenWeapon;
        boolean buy = false;

        do {
            System.out.print("Veuillez entrer l'ID de l'arme que vous souhaitez acheter : ");
            String firstWeapon = scanner.nextLine();


            // Récupération de l'arme dans le store
            chosenWeapon = store.getWeaponOfStore(firstWeapon);

            if (chosenWeapon != null) {
                // Achat de l'arme
                buy = player.buyWeapon(store, chosenWeapon);
            } else {
                System.out.println("Arme non disponible dans la boutique. Veuillez choisir une arme du catalogue.\n");
            }

        } while (chosenWeapon == null || !buy);

        // Sélection de l'arme choisie
        player.selectWeapon(chosenWeapon);

        Thread.sleep(2000);
    }

    public void fightLoop(Scanner scanner, Destructible entity) throws InterruptedException {
        String playerAction = "";
        int actionCount = 0;

        while (!playerAction.equals("S") && entity.getPv() > 0) {
            double durabilityToRemove = 10;

            if (player.getPv() > 0) {
                if (player.getSelectedWeapon().getDurability() > 0) {
                    if (actionCount == 0) {
                        if (entity instanceof Monster monster) {
                            System.out.println("\n=========================================");
                            System.out.println("Vous entrez en combat avec " + monster.getName() + " !");
                            System.out.println("=========================================");
                        }

                        player.attackDestructible(entity);

                        if (entity instanceof Monster monster) {
                            durabilityToRemove += 5;
                            monster.attackPlayer(player);
                        }

                        player.getSelectedWeapon().removeDurability(durabilityToRemove);

                        if (player.getSelectedWeapon().getDurability() <= 0) {
                            System.out.println("\n" + AnsiColors.RED + "Votre arme s'est cassée !" + AnsiColors.RESET);
                            Thread.sleep(1200);
                            break;
                        }

                        Thread.sleep(300);
                        actionCount++;
                    } else {
                        // Affichage des commandes disponibles pour le joueur
                        System.out.println("\nQue souhaitez-vous faire ?");
                        System.out.println("[A] : Attaquer");
                        System.out.println("[S] : Sortir du combat");
                        playerAction = scanner.nextLine();

                        if (playerAction.equals("A")) {

                            if (entity instanceof Monster monster) {
                                player.attackDestructible(monster);
                                monster.attackPlayer(player);
                                Thread.sleep(300);

                                actionCount++;
                            } else if (entity instanceof Obstacle obstacle) {
                                player.attackDestructible(obstacle);
                                Thread.sleep(300);
                                actionCount++;
                            }

                            player.getSelectedWeapon().removeDurability(durabilityToRemove);

                        } else if (!playerAction.equals("S")) {
                            player.commandNotAvailable();
                        }
                    }
                } else {
                    System.out.println("Vous ne pouvez combattre avec l'arme sélectionnée car elle est cassée !");
                    Thread.sleep(1200);
                    break;
                }
            } else {
                break;
            }
        }
    }

    // Logique du jeu
    public void gameLogic(Scanner scanner) throws InterruptedException {
        // Début du jeu
        System.out.println("\n================================");
        System.out.println("          Début du jeu          ");
        System.out.println("================================");

        // Récupération de la map
        Object[][] map = this.theMap.getMap();

        // Boucle de jeu
        while (!this.theMap.getMapBuffer().equals(this.theMap.getMapFinish()) && player.getPv() > 0) {

            // Affichage de la map
            this.theMap.displayMap();

            // Affichage des commandes disponibles pour le joueur
            player.displayAvailableCommand(this.theMap, posX, posY);

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
                    if (this.theMap.getMove(posX - 1, posY)) {
                        // Si le joueur était sur un item qu'il n'a pas récupéré on remet l'item sur la map
                        this.theMap.replaceItemInMap(posX, posY);

                        posX--;
                        this.theMap.setMapBuffer(map[posX][posY]);
                        this.theMap.definePlayerPosition(player, posX, posY);
                        System.out.println("\nVous vous êtes déplacé vers le " + AnsiColors.BLUE + "haut" + AnsiColors.RESET);
                    } else {
                        // L'action demandée n'est pas disponible
                        player.commandNotAvailable();
                    }
                    break;

                case 'Q':
                    if (this.theMap.getMove(posX, posY - 1)) {
                        // Si le joueur était sur un item qu'il n'a pas récupéré on remet l'item sur la map
                        this.theMap.replaceItemInMap(posX, posY);

                        posY--;
                        this.theMap.setMapBuffer(map[posX][posY]);
                        this.theMap.definePlayerPosition(player, posX, posY);
                        System.out.println("\nVous vous êtes déplacé vers la " + AnsiColors.BLUE + "gauche" + AnsiColors.RESET);
                    } else {
                        System.out.println("\n" + player.getName() + ", l'action demandée n'est pas disponible");
                    }
                    break;

                case 'S':
                    if (this.theMap.getMove(posX + 1, posY)) {
                        // Si le joueur était sur un item qu'il n'a pas récupéré on remet l'item sur la map
                        this.theMap.replaceItemInMap(posX, posY);

                        posX++;
                        this.theMap.setMapBuffer(map[posX][posY]);
                        this.theMap.definePlayerPosition(player, posX, posY);
                        System.out.println("\nVous vous êtes déplacé vers le " + AnsiColors.BLUE + "bas" + AnsiColors.RESET);
                    } else {
                        // L'action demandée n'est pas disponible
                        player.commandNotAvailable();
                    }
                    break;

                case 'D':
                    if (this.theMap.getMove(posX, posY + 1)) {
                        // Si le joueur était sur un item qu'il n'a pas récupéré on remet l'item sur la map
                        this.theMap.replaceItemInMap(posX, posY);

                        posY++;
                        this.theMap.setMapBuffer(map[posX][posY]);
                        this.theMap.definePlayerPosition(player, posX, posY);
                        System.out.println("\nVous vous êtes déplacé vers la " + AnsiColors.BLUE + "droite" + AnsiColors.RESET);
                    } else {
                        // L'action demandée n'est pas disponible
                        player.commandNotAvailable();
                    }
                    break;
                case 'R':
                    if (this.theMap.getMapBuffer().equals(this.theMap.getMapMoney())) {
                        this.theMap.setMapBuffer("");

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
                    if (this.theMap.getMapBuffer() instanceof WeaponStore currentStore) {
                        // Affichage du catalogue de la boutique
                        currentStore.displayCatalogue();

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

                                Weapon playchoosenWeapon = currentStore.getWeaponOfStore(idWeapon);
                                if (playchoosenWeapon != null) {
                                    player.buyWeapon(currentStore, playchoosenWeapon);
                                } else {
                                    System.out.println("L'ID entré ne correspond à aucune arme.");
                                }

                            } else if (!playerAction.equals("S")) {
                                System.out.println(player.getName() + ", l'action demandée n'existe pas");
                            }
                        } while (!playerAction.equals("S"));
                    } else {
                        player.commandNotAvailable();
                    }
                    break;
                case 'L':
                    // Détermine l'emplacement de l'obstacle à attaquer
                    int[] obstaclePosition = player.findAdjacentEntityPosition(posX, posY, Obstacle.class, this.theMap, map);

                    if (obstaclePosition != null) {
                        Obstacle obstacle = (Obstacle) map[obstaclePosition[0]][obstaclePosition[1]];
                        Thread.sleep(300);

                        // Boucle de combat
                        this.fightLoop(scanner, obstacle);

                        if (obstacle.getPv() <= 0) {
                            Thread.sleep(300);
                            System.out.println(AnsiColors.BLUE + "\nL'obstacle est détruit !" + AnsiColors.RESET);

                            // Ajout de l'XP au joueur
                            player.addXp(30, true);

                            // Ajout de 10$ au joueur
                            player.addMoney(10, true);

                            Thread.sleep(1200);

                            // Remplace l'obstacle par un espace vide
                            this.theMap.resetLocation(obstaclePosition[0], obstaclePosition[1]);
                        }
                    } else {
                        System.out.println("Il n'y a pas d'obstacle à attaquer !");
                    }
                    break;
                case 'K':
                    // Détermine l'emplacement de l'obstacle à attaquer
                    int[] monsterPosition = player.findAdjacentEntityPosition(posX, posY, Monster.class, this.theMap, map);

                    if (monsterPosition != null) {
                        Monster monster = (Monster) map[monsterPosition[0]][monsterPosition[1]];

                        // Boucle de combat
                        this.fightLoop(scanner, monster);

                        if (monster.getPv() <= 0) {
                            System.out.println(AnsiColors.BLUE + "\nLe monstre est mort !" + AnsiColors.RESET);

                            // Ajout de l'XP au joueur
                            player.addXp(120, true);

                            if (player.getPv() > 0) {
                                // Ajout des PV au joueur en fonction de son niveau
                                double winPv = 20;

                                // Pour chaque niveau du joueur il gagne 5 PV en plus
                                for ( int i = 0; i < player.getLevel(); i++ ) {
                                    if (i <= 1) {
                                        winPv += 5;
                                    } else if (i <= 3) {
                                        winPv += 2.5;
                                    } else if (i <= 5) {
                                        winPv += 1.75;
                                    } else {
                                        winPv += 1;
                                    }
                                }

                                player.addPv(winPv, true);

                                // Ajout de 20$ au joueur
                                player.addMoney(20, true);
                            }

                            Thread.sleep(1200);

                            // Remplace l'obstacle par un espace vide
                            this.theMap.resetLocation(monsterPosition[0], monsterPosition[1]);
                        }
                    } else {
                        System.out.println("\nIl n'y a pas de monstre à attaquer !");
                    }
                    break;
                case 'I':
                    player.displayInformation(scanner);
                    break;

                case '*':
                    this.theMap.displayMapLegend(scanner);
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
        if (this.theMap.getMapBuffer().equals(this.theMap.getMapFinish())) {
            System.out.println("\n" + AnsiColors.CYAN + "Félicitation vous avez terminé le jeu !" + AnsiColors.RESET);
        }
    }
}
