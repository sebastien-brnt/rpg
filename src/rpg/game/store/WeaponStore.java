package rpg.game.store;

import rpg.game.weapons.Axe;
import rpg.game.weapons.Bow;
import rpg.game.weapons.Hammer;
import rpg.game.weapons.Weapon;

import java.util.ArrayList;

public class WeaponStore {
    private ArrayList<Weapon> weaponList = new ArrayList<>();

    public WeaponStore(String prefixId) {
        weaponList.add(new Hammer(prefixId + "1"));
        weaponList.add(new Axe(prefixId + "2"));
        weaponList.add(new Bow(prefixId + "3"));
    }

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

    public ArrayList<Weapon> getWeaponList() {
        return weaponList;
    }

    @Override
    public String toString() {
        return "[B]";
    }
}