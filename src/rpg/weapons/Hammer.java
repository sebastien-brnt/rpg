package rpg.weapons;

import rpg.destructible.Destructible;
import rpg.destructible.Monster;
import rpg.destructible.Obstacle;

public class Hammer extends Weapon {

    private  static final String name = "Hammer";
    private  static final double damage = 10;
    private  static final double price = 50;
    private  static final double durability = 80;
    static final double MONSTER_DAMAGE_RATIO = 0.7;
    static final double OBSTACLE_DAMAGE_RATIO = 1;


    public Hammer(String id) {
        super(id, name, damage, price, durability);
    }

    public String ascii_art() {
        return  " /(               \n" +
                "|  >:=========== \n" +
                " )(               \n";
    }

    public void attack(Destructible destructible) {
        if (destructible instanceof Obstacle) {
            destructible.hit_me(damage * OBSTACLE_DAMAGE_RATIO);
        } else {
            destructible.hit_me(damage * MONSTER_DAMAGE_RATIO);
        }

        System.out.println("\nVous venez d'infliger " + damage + " PV à " + destructible.getName() );
    }
}
