public class Bow extends Weapon {
    public Bow(int id) {
        super(id, "Bow",80, 170);
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
