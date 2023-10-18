package rpg;

public class Bow extends Weapon {

    private  static final String name = "Bow";
    private  static final double damage = 80;
    private  static final double price = 130;

    public Bow(int id) {
        super(id, name, damage, price);
    }

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
