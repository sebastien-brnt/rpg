package rpg.weapons;

import rpg.destructible.Destructible;
import rpg.destructible.Monster;
import rpg.destructible.Obstacle;
import rpg.utility.AnsiColors;

public class Axe extends Weapon {

    private  static final String name = "Axe";
    private  static final double damage = 35;
    private  static final double price = 80;
    private  static final double durability = 100;
    static final double MONSTER_DAMAGE_RATIO = 0.8;
    static final double OBSTACLE_DAMAGE_RATIO = 1.3;

    public Axe(String id) {
        super(id, name, damage, price, durability);
    }

    @Override
    public String ascii_art() {
        return  "     __    \n" +
                "  /\\ ) \\  \n" +
                "<=()=>  )  \n" +
                "  || )_/   \n" +
                "  ||       \n" +
                "  ||       \n";
    }

    @Override
    public void attack(Destructible destructible) {
        double finalDamage;
        if (destructible instanceof Obstacle) {
            finalDamage = damage * OBSTACLE_DAMAGE_RATIO;
            destructible.hit_me(finalDamage);
        } else {
            finalDamage = damage * MONSTER_DAMAGE_RATIO;
            destructible.hit_me(finalDamage);
        }

        System.out.println("\nVous venez d'infliger " + AnsiColors.CYAN + finalDamage + " PV" + AnsiColors.RESET + " à " + AnsiColors.BLUE + destructible.getName() + AnsiColors.RESET );
    }
}
