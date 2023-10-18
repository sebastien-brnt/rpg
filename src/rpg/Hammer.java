package rpg;

public class Hammer extends Weapon {

    private  static final String name = "Hammer";
    private  static final double damage = 10;
    private  static final double price = 50;

    public Hammer(int id) {
        super(id, name, damage, price);
    }

    public String ascii_art() {
        return  " /(               \n" +
                "|  >:=========== \n" +
                " )(               \n";
    }

}
