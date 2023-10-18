import java.util.ArrayList;

public class WeaponStore {
    private ArrayList<Weapon> weaponList = new ArrayList<>();

    public WeaponStore() {
        Weapon hammer = new Hammer(1);
        Weapon axe = new Axe(2);
        Weapon bow = new Bow(3);

        this.weaponList.add(hammer);
        this.weaponList.add(axe);
        this.weaponList.add(bow);
    }

    public ArrayList<Weapon> getWeaponList() {
        return weaponList;
    }

    public Weapon getWeaponOfStore(int id) {
        for(Weapon weapon : weaponList) {
            if(weapon.getId() == id) {
                return weapon;
            }
        }
        return null;
    }

    public void RemoveWeapon(Weapon weapon) {
        ArrayList<Weapon> weaponsToRemove = new ArrayList<>();

        for (Weapon w : weaponList) {
            if (w.getId() == weapon.getId()) {
                weaponsToRemove.add(w);
            }
        }

        weaponList.removeAll(weaponsToRemove);
    }
}
