package rpg.destructible;

import rpg.player.Player;

public class Monster extends Destructible {
    private double hit;

    public Monster(String name, double pv, double hit) {
        super(name, pv);
        this.hit = hit;
    }

    public double getHit() {
        return hit;
    }

    public void attackPlayer(Player player) {
        player.removePv(this.hit);
    }
}
