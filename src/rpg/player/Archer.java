package rpg.player;

public class Archer extends Player {

    public Archer(String name) {
        super(name, "Archer" , 80, 120);
    }

    @Override
    public String ascii_art() {
        return "  /\\_/\\\n" +
                " ( o.o )\n" +
                "  > ^ <\n";
    }
}
