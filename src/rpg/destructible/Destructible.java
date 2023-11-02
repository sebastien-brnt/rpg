package rpg.destructible;

public class Destructible {

    protected String name;
    protected double pv;

    public Destructible(String name, double pv) {
        this.name = name;
        this.pv = pv;
    }

    public void hit_me(double damage) {
        pv -= damage;
    }

    public double getPv() {
        return pv;
    }

    public String getName() {
        return name;
    }
}
