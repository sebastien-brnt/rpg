package rpg.destructible;

public class Obstacle extends Destructible {

    public static double PV = 50;

    public Obstacle(String name) {
        super(name, PV);
    }
}
