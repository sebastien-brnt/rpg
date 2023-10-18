import java.util.ArrayList;

public class Player {
    private String name;
    private double money;
    private ArrayList<Weapon> weaponList = new ArrayList<>();

    public Player(String name, double money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return this.name;
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
                System.out.println(this.name + " has " + this.money + " after purchasing : " + weapon.getName() + " for " + weapon.getPrice() + "$");
            } else {
                System.out.println(this.name + " do not have enough money to buy this ! (money : " + this.money + "$, price : " + weapon.getPrice() + "$)");
            }
        } else {
            System.out.println(this.name + ", this weapon is not available in this store...");
        }
    }

    public String toString() {
        return  "Name : " + this.name + "\n" +
                "Money : " + this.money + "$\n" +
                "Weapon list : " + this.weaponList;

    }
}
