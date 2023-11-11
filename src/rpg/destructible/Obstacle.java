package rpg.destructible;

import rpg.MapRepresentable;
import rpg.utility.AnsiColors;

public class Obstacle extends Destructible implements MapRepresentable {

    public static double PV = 50;
    public static double HIT = 0;
    public static final String representation = "[" + AnsiColors.YELLOW + "O" + AnsiColors.RESET + "]";

    public Obstacle(String name) {
        super(name, PV, HIT);
    }

    public String getRepresentation() {
        return representation;
    }

    @Override
    public String toString() {
        return getRepresentation();
    }
}
