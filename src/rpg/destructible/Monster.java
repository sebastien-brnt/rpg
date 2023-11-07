package rpg.destructible;

import rpg.player.Player;
import rpg.utility.AnsiColors;

public class Monster extends Destructible {

    public static double PV = 150;
    public static String representation = "[" + AnsiColors.RED + "M" + AnsiColors.RESET + "]";
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

        System.out.println("Le monstre " + this.getName() + " vous à infligé " + AnsiColors.RED + this.hit + " PV" + AnsiColors.RESET+ ", il vous reste désormais " + AnsiColors.BLUE + playerLife + " PV" + AnsiColors.RESET);
    }

    @Override
    public String toString() {
        return representation;
    }
}
