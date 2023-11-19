package rpg.game;

import rpg.game.store.WeaponStore;
import rpg.game.weapons.Weapon;

import java.util.ArrayList;

public class Player {

    private String name;

    private double pv;
    private double money;
    private double xp;
    private int level;
    private Weapon selectedWeapon;
    private ArrayList<Weapon> weaponList = new ArrayList<>();

    private PlayerCast cast;

    public Player(String name, PlayerCast cast, double pv, double money) {
        this.name = name;
        this.cast = cast;
        this.pv = pv;
        this.money = money;
        this.xp = 0;
        this.level = 0;
    }

    public String getName() {
        return this.name;
    }
    public PlayerCast getCast() {
        return this.cast;
    }
    public double getPv() {
        return this.pv;
    }
    public double getXp() {
        return this.xp;
    }
    public void addMoney(double value, boolean showMessage) {
        this.money += value;

        if (showMessage) {
            System.out.println("Vous avez gagné " + value + "$" + "!");
            System.out.println("Votre nouveau solde est de " + this.getMoney() + "$" + "!");
        }
    }
    public void addMoney(double value) {
        addMoney(value, false);
    }
    public void addXp(double value, boolean showMessage) {
        this.xp += value;

        if (showMessage) {
            System.out.println("Vous avez gagné " + value + " XP !");

            int levelCount = 0;

            while ((this.getXp() - (this.getLevel() * 140)) > this.getNextLevel() - this.getLevel() * 140) {
                levelCount++;
                this.addLevel(1);
            }

            if (levelCount > 0) {
                System.out.println("Vous avez gagné " + levelCount + " niveau ! Vous êtes désormais niveau " +  this.getLevel() + " !");
            }
        }

    }
    public void addXp(double value) {
        this.addXp(value, false);
    }

    public void removeXp(double value) {
        this.pv -= value;
    }
    public int getLevel() {
        return this.level;
    }
    public double getNextLevel() {
        return ((this.level + 1) * 140);
    }

    public void addLevel(int value) {
        this.level += value;
    }
    public double getMoney() {
        return this.money;
    }

    public void removeMoney(double value) {
        this.money -= value;
    }

    public Weapon getSelectedWeapon() {
        return this.selectedWeapon;
    }

    public void selectWeapon(Weapon weapon) {
        this.selectedWeapon = weapon;
    }

    public ArrayList<Weapon> getWeaponList() {
        return weaponList;
    }

    public void changeWeapon(Weapon weapon) {
        this.selectedWeapon = weapon;

        System.out.println("Vous avez changé d'arme, vous avez désormais : " + weapon.getName() + " comme arme sélectionnée");
    }

    public boolean buyWeapon(WeaponStore store, Weapon weapon) {
        if (weapon != null) {
            if (this.money >= weapon.getPrice()) {
                this.money -= weapon.getPrice();
                this.weaponList.add(weapon);
                store.removeWeapon(weapon);
                System.out.println("Achat de : "  + weapon.getName() +  " pour " + weapon.getPrice() + "$");
                return true;
            } else {
                System.out.println("Pas assez d'argent pour acheter : "  + weapon.getName() + " (argent : "  + this.money + "$" + ", prix : " + weapon.getPrice() + "$"  + ")");
                return false;
            }
        } else {
            System.out.println("Arme n'est pas disponible dans la boutique");
            return false;
        }
    }

}
