public class Axe extends Weapon {
    private  static final String name = "Axe";
    private  static final double damage = 35;
    private  static final double price = 80;

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

}
