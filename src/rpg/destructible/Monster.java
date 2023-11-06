package rpg.destructible;

import rpg.player.Player;

public class Monster extends Destructible {

    public static double PV = 150;
    public static String representation = "[\u001B[31mM\u001B[0m]";
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
        double playerLife = player.getPv();

        if (playerLife < 0) {
            playerLife = 0;
        }

        System.out.println("Le monstre " + this.getName() + " vous à infligé " + this.hit + " PV, il vous reste désormais " + playerLife + " PV");
    }

    @Override
    public String toString() {
        return this.representation;
    }
}
