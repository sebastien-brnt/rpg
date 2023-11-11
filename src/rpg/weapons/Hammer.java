package rpg.weapons;

import rpg.ConsoleRepresentable;

public class Hammer extends Weapon implements ConsoleRepresentable {

    private  static final String name = "Hammer";
    private  static final double damage = 35;
    private  static final double price = 50;
    private  static final double durability = 200;
    static final double MONSTER_DAMAGE_RATIO = 0.9;
    static final double OBSTACLE_DAMAGE_RATIO = 1;


    public Hammer(String id) {
        super(id, name, damage, price, durability, MONSTER_DAMAGE_RATIO, OBSTACLE_DAMAGE_RATIO);
    }

    @Override
    public String ascii_art() {
        return  " /(               \n" +
                "|  >:=========== \n" +
                " )(               \n";
    }
}
