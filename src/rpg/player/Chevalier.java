package rpg.player;

public class Chevalier extends Player {

    public Chevalier(String name) {
        super(name, "Chevalier" , 100, 100);
    }

    @Override
    public String ascii_art() {
        return "  /\\_/\\\n" +
                " ( o.o )\n" +
                "  > ^ <\n";
    }
}
