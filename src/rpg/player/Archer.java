package rpg.player;

public class Archer extends Player {

    public Archer(String name) {
        super(name, "Archer" , 80, 120);
    }

    @Override
    public String ascii_art() {
        return "         /|   \\\n" +
                "        /_|_{)/\n" +
                "---<<   | |  )\n" +
                "        \\ |  (\n" +
                "         \\|__)\n" +
                "           \\ |__\n" +
                "           ~    ~\n";
    }
}
