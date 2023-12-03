package rpg.game.player;

import rpg.game.destructible.Destructible;

import javax.swing.*;
import java.awt.*;

public class Archer extends Player implements IPlayer {

    private Image playerImage;

    public Archer(String name) {
        super(name, PlayerCast.ARCHER , 80, 120);
    }

    @Override
    public void attackDestructible(Destructible target) {
        // Attaque de la cible
        this.getSelectedWeapon().attack(target);
        // Gagne 2.5$ et 10 XP à chaque coup
        System.out.println();
        this.addMoney(2.5, true);
        this.addXp(10, true);
        System.out.println();
    }

    @Override
    public Image getPlayerImage() {
        this.playerImage = new ImageIcon("src/rpg/images/archer.png").getImage();
        return this.playerImage;
    }
}
