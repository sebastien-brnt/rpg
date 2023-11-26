package rpg.game.destructible;

import rpg.game.player.Player;

public class Monster extends Destructible {

    public static double PV = 125;

    public Monster(String name, double hit) {
        super(name, PV, hit);
    }

    public void attackPlayer(Player player) {
        player.removePv(this.getDamage());
        double playerLife = player.getPv();

        if (playerLife < 0) {
            playerLife = 0;
        }

        System.out.println("Le monstre " + this.getName() + " vous à infligé " + this.getDamage() + " PV, il vous reste désormais " + playerLife + " PV");
    }
}
