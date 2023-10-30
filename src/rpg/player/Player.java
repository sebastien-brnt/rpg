package rpg.player;

import rpg.store.WeaponStore;
import rpg.destructible.Monster;
import rpg.destructible.Obstacle;
import rpg.weapons.Weapon;

import java.util.ArrayList;

public class Player implements ActionsPlayer {
    private String name;
    private double pv;
    private double money;
    private ArrayList<Weapon> weaponList = new ArrayList<>();

    public Player(String name, double pv, double money) {
        this.name = name;
        this.pv = pv;
        this.money = money;
    }

    public String getName() {
        return this.name;
    }

    public double getPv() {
        return this.pv;
    }

    public double getMoney() {
        return this.money;
    }

    public void addMoney(int value) {
         this.pv += value;
    }

    public void removePv(double hit) {
        this.pv = this.pv - hit;
    }

    public void attackMonster(Monster monster, Weapon weapon) {
        monster.hit_me(weapon.getDamage());
    }

    public void attackObstacle(Obstacle obstacle, Weapon weapon) {
        obstacle.hit_me(weapon.getDamage());
    }

    public ArrayList<Weapon> getWeaponList() {
        return weaponList;
    }

    public void buyWeapon(WeaponStore store, Weapon weapon) {
        if (weapon != null) {
            if (this.money >= weapon.getPrice()) {
                this.money -= weapon.getPrice();
                this.weaponList.add(weapon);
                store.RemoveWeapon(weapon);
                System.out.println(this.name + ", il vous reste " + this.money + "$ après l'achat de : " + weapon.getName() + " pour " + weapon.getPrice() + "$");
            } else {
                System.out.println(this.name + ", Tu n'a pas assez d'argent pour acheter cela (argent : " + this.money + "$, prix : " + weapon.getPrice() + "$)");
            }
        } else {
            System.out.println(this.name + ", cette arme n'est pas disponible dans la boutique...");
        }
    }

    public String toString() {
        return  "Name : " + this.name + "\n" +
                "Money : " + this.money + "$\n" +
                "Weapon list : " + this.weaponList;

    }
}
