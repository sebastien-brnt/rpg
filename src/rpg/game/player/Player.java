package rpg.game.player;

import rpg.game.Map;
import rpg.game.destructible.Destructible;
import rpg.game.store.WeaponStore;
import rpg.game.weapons.Weapon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Player {

    private String name;

    private double pv;
    private double money;
    private double xp;
    private int level;
    private Weapon selectedWeapon;
    private ArrayList<Weapon> weaponList = new ArrayList<>();

    private Image playerImage;

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
    public void removePv(double value) {
        if (this.pv - value < 0) {
            this.pv = 0;
        } else {
            this.pv -= value;
        }
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

    public void attackDestructible(Destructible target) {
        this.getSelectedWeapon().attack(target);
    }

    public void addPv(double value) {
        // On limite à 100 les PV maximum
        if (this.pv + value > 100) {
            this.pv = 100;
        } else {
            this.pv += value;
        }
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
        }
        return false;
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

    public abstract Image getPlayerImage();
}
