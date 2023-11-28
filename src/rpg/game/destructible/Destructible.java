package rpg.game.destructible;

import java.awt.*;

public abstract class Destructible {

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

    public double getDamage() {
        return hit;
    }

    public double getPv() {
        return pv;
    }

    public String getName() {
        return name;
    }

    public abstract Image getImage();
}
