package rpg.game.destructible;

public class Obstacle extends Destructible {

    public static double PV = 50;
    public static double HIT = 0;
    public Obstacle(String name) {
        super(name, PV, HIT);
    }
}
