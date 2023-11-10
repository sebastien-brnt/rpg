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

public class Player implements ActionsPlayer {
    private String name;
    private double pv;
    private double money;
    private double xp;
    private Weapon selectedWeapon;
    private ArrayList<Weapon> weaponList = new ArrayList<>();

    public static String representation = "[" + AnsiColors.BLUE + "X" + AnsiColors.RESET + "]";

    public Player(String name, double pv, double money, double xp) {
        if (name.isEmpty()) {
            this.name = "Joueur";
        } else {
            this.name = name;
        }

        this.pv = pv;
        this.money = money;
        this.xp = xp;
    }

    public String getName() {
        return this.name;
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

    public void addMoney(double value) {
        this.money += value;
    }
    public void addXp(double value, boolean showMessage) {
        this.xp += value;

        if (showMessage) {
            System.out.println("Vous avez gagné " + AnsiColors.YELLOW + value + " XP" + AnsiColors.RESET + "!");
        }
    }

    public void addXp(double value) {
        this.addXp(value, false);
    }

    public void removePv(double hit) {
        this.pv = this.pv - hit;
    }

    public void addPv(double value, boolean showMessage) {
        this.pv += value;

        if (showMessage) {
            System.out.println("Vous avez gagné " + AnsiColors.CYAN + value + " PV" + AnsiColors.RESET + "!");
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
    public void buyWeapon(WeaponStore store, Weapon weapon) {
        if (weapon != null) {
            if (this.money >= weapon.getPrice()) {
                this.money -= weapon.getPrice();
                this.weaponList.add(weapon);
                store.RemoveWeapon(weapon);
                System.out.println(this.name + ", il vous reste " + AnsiColors.GREEN + this.money + "$" + AnsiColors.RESET + " après l'achat de : " + AnsiColors.BLUE + weapon.getName() + AnsiColors.RESET + " pour " + AnsiColors.GREEN + weapon.getPrice() + "$" + AnsiColors.RESET);
            } else {
                System.out.println(this.name + ", Tu n'a pas assez d'argent pour acheter cela (argent : " + AnsiColors.GREEN + this.money + "$" + AnsiColors.RESET + ", prix : " + AnsiColors.GREEN + weapon.getPrice() + "$" + AnsiColors.RESET + ")");
            }
        } else {
            System.out.println("Désolé " + this.name + ", cette arme n'est pas disponible dans la boutique...");
        }
    }

    public void displayInformation(Scanner scanner) {
        System.out.println("\n================================");
        System.out.println("       Vos informations :");
        System.out.println("================================");
        System.out.println("Nom : " + AnsiColors.BLUE + this.name + AnsiColors.RESET);
        System.out.println("PV : " + AnsiColors.CYAN + this.pv + " PV" + AnsiColors.RESET);
        System.out.println("Argent : " + AnsiColors.GREEN + this.money + "$" + AnsiColors.RESET);
        System.out.println("XP : " + AnsiColors.YELLOW + this.xp + AnsiColors.RESET);
        System.out.println("\nArme sélectionnée : " + this.selectedWeapon);
        System.out.println("\nListe des armes : " + this.weaponList);

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
}
