package rpg.destructible;

public class Destructible {

    protected String name;
    protected double pv;

    public Destructible(String name, double pv) {
        this.name = name;
        this.pv = pv;
    }

    public void hit_me(double damage) {
        if (damage > pv) {
            pv = 0;
        } else {
            pv -= damage;
        }
    }

    public double getPv() {
        return pv;
    }

    public String getName() {
        return name;
    }
}
