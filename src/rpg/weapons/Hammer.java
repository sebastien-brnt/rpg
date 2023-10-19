package rpg.weapons;

import rpg.destructible.Monster;
import rpg.destructible.Obstacle;

public class Hammer extends Weapon {

    private  static final String name = "Hammer";
    private  static final double damage = 10;
    private  static final double price = 50;
    static final double MONSTER_DAMAGE_RATIO = 0.7;
    static final double OBSTACLE_DAMAGE_RATIO = 1;


    public Hammer(int id) {
        super(id, name, damage, price);
    }

    public String ascii_art() {
        return  " /(               \n" +
                "|  >:=========== \n" +
                " )(               \n";
    }

    public void attackMonster(Monster m) {
        m.hit_me(damage * MONSTER_DAMAGE_RATIO);
    }

    public void attackObstacle(Obstacle o) {
        o. hit_me(damage * OBSTACLE_DAMAGE_RATIO) ;
    }
}
