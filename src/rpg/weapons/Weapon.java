package rpg.weapons;

import rpg.destructible.Destructible;
import rpg.destructible.Monster;
import rpg.destructible.Obstacle;

public abstract class Weapon {
    private String id;
    private String name;
    private double damage;
    private double price;

    public Weapon(String id, String name, double damage, double price) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDamage() {
        return damage;
    }

    public double getPrice() {
        return price;
    }

    public Weapon getWeapon() {
        return this;
    }

    public String toString() {
        return  "\n" + "ID : " + this.id + "\n" +
                "Name : " + this.name + "\n" +
                "Damage : " + this.damage + "\n" +
                "Price : " + this.price +"$" + "\n" +
                "Representation : " + "\n" +
                this.ascii_art();
    }

    public abstract String ascii_art();

    public abstract void attack(Destructible destructible);
}
