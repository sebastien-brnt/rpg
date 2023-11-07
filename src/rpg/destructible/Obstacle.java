package rpg.destructible;

import rpg.utility.AnsiColors;

public class Obstacle extends Destructible {

    public static double PV = 50;
    public static String representation = "[" + AnsiColors.YELLOW + "O" + AnsiColors.RESET + "]";

    public Obstacle(String name) {
        super(name, PV);
    }

    @Override
    public String toString() {
        return representation;
    }
}
