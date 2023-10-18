public class Axe extends Weapon {
    public Axe(int id) {
        super(id, "Axe",35, 80);
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
