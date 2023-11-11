package rpg.player;

import rpg.ConsoleRepresentable;

public class Archer extends Player implements ConsoleRepresentable {

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
