package rpg.store;

import rpg.commonInterface.MapRepresentable;
import rpg.utility.AnsiColors;
import rpg.weapons.Axe;
import rpg.weapons.Bow;
import rpg.weapons.Hammer;
import rpg.weapons.Weapon;

import java.util.ArrayList;

public class WeaponStore implements MapRepresentable {
    private ArrayList<Weapon> weaponList = new ArrayList<>();
    private static final String representation = "[" + AnsiColors.PURPLE + "B" + AnsiColors.RESET + "]";

    public WeaponStore() {
        weaponList.add(new Hammer("1"));
        weaponList.add(new Axe("2"));
        weaponList.add(new Bow("3"));
    }

    public Weapon getWeaponOfStore(String id) {
        for (Weapon weapon : weaponList) {
            if (weapon.getId().equals(id)) {
                return weapon;
            }
        }
        return null;
    }

    public void removeWeapon(Weapon weapon) {
        ArrayList<Weapon> weaponsToRemove = new ArrayList<>();
        for (Weapon w : weaponList) {
            if (w.getId().equals(weapon.getId())) {
                weaponsToRemove.add(w);
            }
        }
        weaponList.removeAll(weaponsToRemove);
    }

    public void displayCatalogue() {
        System.out.println("\n================================");
        System.out.println("Catalogue de la boutique :");
        System.out.println("================================");

        for (Weapon weapon : getWeaponList()) {
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

    public ArrayList<Weapon> getWeaponList() {
        return weaponList;
    }

    public String getRepresentation() {
        return representation;
    }

    @Override
    public String toString() {
        return getRepresentation();
    }
}