package rpg.game.weapons;

public class Axe extends Weapon {

    private  static final String name = "Axe";
    private  static final double damage = 55;
    private  static final double price = 80;
    private  static final double durability = 230;
    static final double MONSTER_DAMAGE_RATIO = 0.8;
    static final double OBSTACLE_DAMAGE_RATIO = 1.3;

    public Axe(String id) {
        super(id, name, damage, price, durability, MONSTER_DAMAGE_RATIO, OBSTACLE_DAMAGE_RATIO);
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
}
