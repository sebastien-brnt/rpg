package rpg.weapons;

import rpg.destructible.Destructible;
import rpg.destructible.Monster;
import rpg.destructible.Obstacle;
import rpg.utility.AnsiColors;

public class Bow extends Weapon {

    private  static final String name = "Bow";
    private  static final double damage = 80;
    private  static final double price = 130;
    private  static final double durability = 180;
    static final double MONSTER_DAMAGE_RATIO = 1.2;
    static final double OBSTACLE_DAMAGE_RATIO = 1;

    public Bow(String id) {
        super(id, name, damage, price, durability);
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

    @Override
    public void attack(Destructible destructible) {
        double finalDamage;
        if (destructible instanceof Obstacle) {
            finalDamage = damage * OBSTACLE_DAMAGE_RATIO;
            destructible.hit_me(damage * OBSTACLE_DAMAGE_RATIO);
        } else {
            finalDamage = damage * MONSTER_DAMAGE_RATIO;
            destructible.hit_me(damage * MONSTER_DAMAGE_RATIO);
        }

        System.out.println("\nVous venez d'infliger " + AnsiColors.BLUE + finalDamage + " PV" + AnsiColors.RESET + " à " + AnsiColors.BLUE + destructible.getName() + AnsiColors.RESET );
    }
}
