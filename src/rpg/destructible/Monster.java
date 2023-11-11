package rpg.destructible;

import rpg.commonInterface.MapRepresentable;
import rpg.player.Player;
import rpg.utility.AnsiColors;

public class Monster extends Destructible implements MapRepresentable {

    public static double PV = 125;
    public static final String representation = "[" + AnsiColors.RED + "M" + AnsiColors.RESET + "]";

    public Monster(String name, double hit) {
        super(name, PV, hit);
    }

    public void attackPlayer(Player player) {
        player.removePv(this.getDamage());
        double playerLife = player.getPv();

        if (playerLife < 0) {
            playerLife = 0;
        }

        System.out.println("Le monstre " + this.getName() + " vous à infligé " + AnsiColors.RED + this.getDamage() + " PV" + AnsiColors.RESET+ ", il vous reste désormais " + AnsiColors.CYAN + playerLife + " PV" + AnsiColors.RESET);
    }

    public String getRepresentation() {
        return representation;
    }

    @Override
    public String toString() {
        return this.getRepresentation();
    }
}
