package rpg.store;

import rpg.utility.AnsiColors;
import rpg.weapons.Axe;
import rpg.weapons.Bow;
import rpg.weapons.Hammer;
import rpg.weapons.Weapon;

import java.util.ArrayList;

public class WeaponStore {
    private ArrayList<Weapon> weaponList = new ArrayList<>();

    private String representation = "[" + AnsiColors.PURPLE + "B" + AnsiColors.RESET + "]";

    public WeaponStore() {
        Weapon hammer = new Hammer("1");
        Weapon axe = new Axe("2");
        Weapon bow = new Bow("3");

        this.weaponList.add(hammer);
        this.weaponList.add(axe);
        this.weaponList.add(bow);
    }

    public ArrayList<Weapon> getWeaponList() {
        return this.weaponList;
    }

    public Weapon getWeaponOfStore(String id) {
        for(Weapon weapon : this.weaponList) {
            if(weapon.getId().equals(id)) {
                return weapon;
            }
        }
        return null;
    }

    public void RemoveWeapon(Weapon weapon) {
        ArrayList<Weapon> weaponsToRemove = new ArrayList<>();

        for (Weapon w : this.weaponList) {
            if (w.getId().equals(weapon.getId())) {
                weaponsToRemove.add(w);
            }
        }

        this.weaponList.removeAll(weaponsToRemove);
    }

    public void displayCatalogue() {
        System.out.println("\n================================");
        System.out.println("Catalogue de la boutique :");
        System.out.println("================================");

        for(Weapon weapon : this.weaponList) {
            System.out.println("\n" + weapon.ascii_art());
            System.out.println("ID : " + weapon.getId());
            System.out.println("Nom : " + weapon.getName());
            System.out.println("Dégâts aux obstacles : " + weapon.getDamageObstacle());
            System.out.println("Dégâts aux monstres : " + weapon.getDamageMonster());
            System.out.println("Prix : " + weapon.getPrice());
            System.out.println("Durabilité : " + weapon.getDurability());
            System.out.println("\n================================");
        }
    }

    @Override
    public String toString() {
        return representation;
    }
}
