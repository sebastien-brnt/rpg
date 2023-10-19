package rpg.weapons;

import rpg.destructible.Monster;
import rpg.destructible.Obstacle;

public class Axe extends Weapon {

    private  static final String name = "Axe";
    private  static final double damage = 35;
    private  static final double price = 80;
    static final double MONSTER_DAMAGE_RATIO = 0.8;
    static final double OBSTACLE_DAMAGE_RATIO = 1.3;

    public Axe(int id) {
        super(id, name, damage, price);
    }

    public String ascii_art() {
        return  "     __    \n" +
                "  /\\ ) \\  \n" +
                "<=()=>  )  \n" +
                "  || )_/   \n" +
                "  ||       \n" +
                "  ||       \n";
    }

    public void attackMonster(Monster m) {
        m.hit_me(damage * MONSTER_DAMAGE_RATIO);
    }

    public void attackObstacle(Obstacle o) {
        o. hit_me(damage * OBSTACLE_DAMAGE_RATIO) ;
    }
}
