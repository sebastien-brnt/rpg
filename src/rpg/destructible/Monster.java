package rpg.destructible;

import rpg.player.Player;

public class Monster extends Destructible {

    public static double PV = 150;
    private double hit;

    public Monster(String name, double hit) {
        super(name, PV);
        this.hit = hit;
    }

    public double getHit() {
        return hit;
    }

    public void attackPlayer(Player player) {
        player.removePv(this.hit);
    }
}
