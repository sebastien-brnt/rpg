package rpg.game.player;

import rpg.game.destructible.Destructible;

import javax.swing.*;
import java.awt.*;

public class Mage extends Player implements IPlayer {

    private Image playerImage;

    public Mage(String name) {
        super(name, PlayerCast.MAGE , 90, 110);
    }

    @Override
    public void attackDestructible(Destructible target) {
        // Nombre aléatoire entre 0 ou 1
        int randomResult = (int) (Math.random() * 2);
        // Attaque de la cible
        this.getSelectedWeapon().attack(target);
        // Une chance sur 2 de récupérer 5 PV en plus de l'attaque
        if (randomResult == 1) {
            this.addPv(5);
        }
    }

    @Override
    public Image getPlayerImage() {
        this.playerImage = new ImageIcon("src/rpg/images/mage.png").getImage();
        return this.playerImage;
    }
}
