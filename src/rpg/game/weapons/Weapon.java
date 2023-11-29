package rpg.game.weapons;

import rpg.game.commonInterfaces.Hitting;
import rpg.game.destructible.Destructible;
import rpg.game.destructible.Obstacle;

public abstract class Weapon implements Hitting {
    private String id;
    private String name;
    private double damage;
    private double price;
    private double durability;
    private double monster_damage_ratio;
    private double obstacle_damage_ratio;

    public Weapon(String id, String name, double damage, double price, double durability, double monster_damage_ratio, double obstacle_damage_ratio) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.price = price;
        this.durability = durability;
        this.monster_damage_ratio = monster_damage_ratio;
        this.obstacle_damage_ratio = obstacle_damage_ratio;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getDamage() {
        return damage;
    }

    public double getDamageMonster() {
        return getDamage() * monster_damage_ratio;
    }

    public double getDamageObstacle() {
        return getDamage() * obstacle_damage_ratio;
    }

    public double getPrice() {
        return price;
    }

    public double getDurability() {
        return durability;
    }

    public void removeDurability(double value) {
        this.durability -= value;
    }

    public Weapon getWeapon() {
        return this;
    }


    public void attack(Destructible destructible) {
        double finalDamage;
        if (destructible instanceof Obstacle) {
            finalDamage = getDamageObstacle();
            destructible.hit_me(getDamageObstacle());
        } else {
            finalDamage = getDamageMonster();
            destructible.hit_me(getDamageMonster());
        }

        System.out.println(finalDamage + " PV infligé à " + destructible.getName() + ", PV restant : " + destructible.getPv() + " PV");
    }

    @Override
    public String toString() {
        return  "\n" +
                this.ascii_art() + "\n" +
                "ID : " + this.getId() + "\n" +
                "Nom : " + this.getName() + "\n" +
                "Dégâts aux monstres : " + this.getDamageMonster() + "\n" +
                "Dégâts aux obstacles : " + this.getDamageObstacle() + "\n" +
                "Durabilité : " + this.getDurability() + "\n" +
                "Prix : " + this.getPrice() +"$" + "\n";
    }

    public abstract String ascii_art();
}