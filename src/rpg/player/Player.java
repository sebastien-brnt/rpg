package rpg.player;

import rpg.destructible.Destructible;
import rpg.destructible.Monster;
import rpg.destructible.Obstacle;
import rpg.map.Map;
import rpg.store.WeaponStore;
import rpg.utility.AnsiColors;
import rpg.weapons.Weapon;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Player implements ActionsPlayer {
    private String name;
    private String role;
    private double pv;
    private double money;
    private double xp;
    private int level;
    private Weapon selectedWeapon;
    private ArrayList<Weapon> weaponList = new ArrayList<>();

    public static String representation = "[" + AnsiColors.BLUE + "X" + AnsiColors.RESET + "]";

    public Player(String name, String role, double pv, double money) {
        if (name.isEmpty()) {
            this.name = "Joueur";
        } else {
            this.name = name;
        }

        if (role.isEmpty()) {
            this.role = "Guerrier";
        } else {
            this.role = role;
        }

        this.pv = pv;
        this.money = money;
        this.xp = 0;
        this.level = 0;
    }

    public String getName() {
        return this.name;
    }
    public String getRole() {
        return this.role;
    }
    public double getPv() {
        return this.pv;
    }
    public double getXp() {
        return this.xp;
    }
    public Weapon getSelectedWeapon() {
        return this.selectedWeapon;
    }

    public double getMoney() {
        return this.money;
    }

    public void selectWeapon(Weapon weapon) {
        this.selectedWeapon = weapon;
    }

    public void addMoney(double value, boolean showMessage) {
        this.money += value;

        if (showMessage) {
            System.out.println("Vous avez gagné " + AnsiColors.GREEN + value + "$" + AnsiColors.RESET + "!");
            System.out.println("Votre nouveau solde est de " + AnsiColors.GREEN + this.getMoney() + "$" + AnsiColors.RESET + "!");
        }
    }

    public void addMoney(double value) {
        addMoney(value, false);
    }
    public void addXp(double value, boolean showMessage) {
        this.xp += value;

        if (showMessage) {
            System.out.println("Vous avez gagné " + AnsiColors.YELLOW + value + " XP" + AnsiColors.RESET + "!");

            int levelCount = 0;

            while ((this.getXp() - (this.getLevel() * 140)) > this.getNextLevel() - this.getLevel() * 100) {
                levelCount++;
                this.addlevel(1);
            }

            if (levelCount > 0) {
                System.out.println("Vous avez gagné " + levelCount + " niveau ! Vous êtes désormais niveau " + AnsiColors.YELLOW + this.getLevel() + AnsiColors.RESET + " !");
            }
        }

    }

    public void addXp(double value) {
        this.addXp(value, false);
    }


    public int getLevel() {
        return this.level;
    }

    public double getNextLevel() {
        return ((this.level + 1) * 140);
    }

    public void addlevel(int value) {
        this.level += value;
    }


    public void removePv(double hit) {
        this.pv = this.pv - hit;
    }

    public void addPv(double value, boolean showMessage) {
        // On limite à 100 les PV maximum
        if (this.pv + value > 100) {
            this.pv = 100;
        } else {
            this.pv += value;
        }

        // Affichage des messages
        if (showMessage) {
            System.out.println("Vous avez gagné " + AnsiColors.CYAN + value + " PV" + AnsiColors.RESET + "!");
            System.out.println("Vous avez désormais " + AnsiColors.CYAN + this.getPv() + " PV" + AnsiColors.RESET + "!");
        }
    }

    public void addPv(double value) {
        this.addPv(value, false);
    }

    public void removeXp(double value) {
        this.pv -= value;
    }

    public void attackDestructible(Destructible target) {
        this.selectedWeapon.attack(target);
    }

    public ArrayList<Weapon> getWeaponList() {
        return weaponList;
    }

    public void changeWeapon(Weapon weapon) {
        this.selectedWeapon = weapon;

        System.out.println("Vous avez changé d'arme, vous avez désormais : " + AnsiColors.BLUE + weapon.getName() + AnsiColors.RESET + " comme arme sélectionnée");
    }

    @Override
    public boolean buyWeapon(WeaponStore store, Weapon weapon) {
        if (weapon != null) {
            if (this.money >= weapon.getPrice()) {
                this.money -= weapon.getPrice();
                this.weaponList.add(weapon);
                store.RemoveWeapon(weapon);
                System.out.println(this.name + ", il vous reste " + AnsiColors.GREEN + this.money + "$" + AnsiColors.RESET + " après l'achat de : " + AnsiColors.BLUE + weapon.getName() + AnsiColors.RESET + " pour " + AnsiColors.GREEN + weapon.getPrice() + "$" + AnsiColors.RESET);
                return true;
            } else {
                System.out.println(this.name + ", Tu n'a pas assez d'argent pour acheter cela (argent : " + AnsiColors.GREEN + this.money + "$" + AnsiColors.RESET + ", prix : " + AnsiColors.GREEN + weapon.getPrice() + "$" + AnsiColors.RESET + ")");
                return false;
            }
        } else {
            System.out.println("Désolé " + this.name + ", cette arme n'est pas disponible dans la boutique...");
            return false;
        }
    }

    public void displayInformation(Scanner scanner) {
        System.out.println("\n================================");
        System.out.println("       Vos informations :");
        System.out.println("================================");
        System.out.println("Nom : " + AnsiColors.BLUE + this.getName() + AnsiColors.RESET);
        System.out.println("Role : " + AnsiColors.BLUE + this.getRole() + AnsiColors.RESET);
        System.out.println("PV : " + AnsiColors.CYAN + this.getPv() + " PV" + AnsiColors.RESET);
        System.out.println("Argent : " + AnsiColors.GREEN + this.getMoney() + "$" + AnsiColors.RESET);
        System.out.println("Niveau : " + AnsiColors.YELLOW + this.getLevel() + AnsiColors.RESET);
        System.out.println("XP : " + AnsiColors.YELLOW + this.getXp() + AnsiColors.RESET);
        System.out.println("\nArme sélectionnée : " + this.getSelectedWeapon());
        System.out.println("\nListe des armes : " + this.getWeaponList());

        String playerAction;

        do {
            // Affichage des commandes disponibles pour le joueur
            System.out.println("\nQue souhaitez-vous faire ?");

            if (this.weaponList.size() > 1) {
                System.out.println("[C] : Changer l'arme sélectionnée");
            }
            System.out.println("[S] : Sortir du menu d'information");
            playerAction = scanner.nextLine();

            if (playerAction.equals("C")) {
                System.out.print("\nVeuillez entrer l'ID de l'arme que vous souhaitez selectionnée : ");
                String idWeapon = scanner.nextLine();

                Weapon playchosenWeapon = null;

                for(Weapon weapon : this.weaponList) {
                    if(weapon.getId().equals(idWeapon)) {
                        playchosenWeapon = weapon;
                    }
                }

                if (playchosenWeapon != null) {
                    this.changeWeapon(playchosenWeapon);
                } else {
                    System.out.println("Vous ne possédé aucune arme avec l'ID : " + idWeapon + " ...");
                }

            } else if (!playerAction.equals("S")) {
                this.commandNotAvailable();
            }
        } while (!playerAction.equals("S"));
    }

    public void displayAvailableCommand(Map map, int posX, int posY) {
        System.out.println("\nListe des commandes disponibles :");

        // Commandes situationelles
        if (map.getMove(posX - 1, posY)) {
            System.out.println("[Z] : Haut");
        }
        if (map.getMove(posX, posY - 1)) {
            System.out.println("[Q] : Gauche");
        }
        if (map.getMove(posX + 1, posY)) {
            System.out.println("[S] : Bas");
        }
        if (map.getMove(posX, posY + 1)) {
            System.out.println("[D] : Droite");
        }
        if (map.getMapBuffer().equals(map.getMapMoney())) {
            System.out.println("[R] : Ramasser l'argent");
        }
        if (map.getPresenceAround(posX, posY, Obstacle.class))  {
            System.out.println("[L] : Attaquer l'obstacle");
        }
        if (map.getPresenceAround(posX, posY, Monster.class))  {
            System.out.println("[K] : Attaquer le monstre");
        }
        if (map.getMapBuffer() instanceof WeaponStore) {
            System.out.println("[V] : Visiter la boutique");
        }

        // Commandes toujours disponibles
        System.out.println("[I] : Voir mes information et mon inventaire");
        System.out.println("[*] : Légende de la map");
        System.out.println("[:] : Quitter le jeu");
    }

    public void commandNotAvailable() {
        System.out.println("\n" + this.getName() + ", l'action demandée n'est pas ou n'existe pas");
    }

    // Vérifie la présence d'une entité autour du joueur et retourne ses coordonnées
    public int[] findAdjacentEntityPosition(int posX, int posY, Class<?> entityType, Map laMap, Object[][] map) {
        int mapSize = laMap.getMapSize();

        // Vérifie à droite
        if (!(posX + 1 > mapSize - 1) && entityType.isInstance(map[posX + 1][posY])) {
            return new int[] { posX + 1, posY };
        }
        // Vérifie à gauche
        else if ((posX - 1) >= 0 && entityType.isInstance(map[posX - 1][posY])) {
            return new int[] { posX - 1, posY };
        }
        // Vérifie en bas
        else if (!(posY + 1 > mapSize - 1) && entityType.isInstance(map[posX][posY + 1])) {
            return new int[] { posX, posY + 1 };
        }
        // Vérifie en haut
        else if ((posY - 1) >= 0 && entityType.isInstance(map[posX][posY - 1])) {
            return new int[] { posX, posY - 1 };
        }

        // Aucune entité trouvée
        return null;
    }


    @Override
    public String toString() {
        return representation;
    }

    public abstract String ascii_art();
}
