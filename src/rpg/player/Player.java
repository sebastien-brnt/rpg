package rpg.player;

import rpg.destructible.Destructible;
import rpg.store.WeaponStore;
import rpg.utility.AnsiColors;
import rpg.weapons.Weapon;

import java.util.ArrayList;

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
    public void addXp(double value) {
        this.xp += value;
    }

    public void removePv(double hit) {
        this.pv = this.pv - hit;
    }
    public void addPv(double value) {
        this.pv += value;
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

    public void displayInformation() {
        System.out.println("\n================================");
        System.out.println("       Vos informations :");
        System.out.println("================================");
        System.out.println("Nom : " + AnsiColors.BLUE + this.name + AnsiColors.RESET);
        System.out.println("PV : " + AnsiColors.CYAN + this.pv + " PV" + AnsiColors.RESET);
        System.out.println("Argent : " + AnsiColors.GREEN + this.money + "$" + AnsiColors.RESET);
        System.out.println("XP : " + AnsiColors.YELLOW + this.xp + AnsiColors.RESET);
        System.out.println("\nArme sélectionnée : " + this.selectedWeapon);
        System.out.println("\nListe des armes : " + this.weaponList);
    }

    @Override
    public String toString() {
        return representation;
    }
}
