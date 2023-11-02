package rpg.destructible;

public class Obstacle extends Destructible {

    public static double PV = 50;
    public static String representation = "[\u001B[33mO\u001B[0m]";

    public Obstacle(String name) {
        super(name, PV);
    }

    @Override
    public String toString() {
        return this.representation;
    }
}
