package rpg.destructible;

import rpg.Hitting;

public class Destructible implements Hitting {

    protected String name;
    protected double pv;
    private double hit;

    public Destructible(String name, double pv, double hit) {
        this.name = name;
        this.pv = pv;
        this.hit = hit;
    }

    public void hit_me(double damage) {
        if (damage > pv) {
            pv = 0;
        } else {
            pv -= damage;
        }
    }

    @Override
    public double getDamage() {
        return hit;
    }

    public double getPv() {
        return pv;
    }

    public String getName() {
        return name;
    }
}
