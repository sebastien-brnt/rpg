package rpg.player;

public class Mage extends Player {

    public Mage(String name) {
        super(name, "Mage" , 90, 110);
    }

    @Override
    public String ascii_art() {
        return "  /\\_/\\\n" +
                " ( o.o )\n" +
                "  > ^ <\n";
    }
}
