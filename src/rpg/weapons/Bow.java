package rpg.weapons;

import rpg.commonInterface.ConsoleRepresentable;

public class Bow extends Weapon implements ConsoleRepresentable {

    private  static final String name = "Bow";
    private  static final double damage = 70;
    private  static final double price = 130;
    private  static final double durability = 250;
    static final double MONSTER_DAMAGE_RATIO = 1.1;
    static final double OBSTACLE_DAMAGE_RATIO = 1;

    public Bow(String id) {
        super(id, name, damage, price, durability, MONSTER_DAMAGE_RATIO, OBSTACLE_DAMAGE_RATIO);
    }

    @Override
    public String ascii_art() {
        return  " (           \n" +
                "   )         \n" +
                "    )        \n" +
                "##-------->  \n" +
                "    )        \n" +
                "   )         \n" +
                " (           \n";
    }
}
