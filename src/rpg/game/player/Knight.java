package rpg.game.player;

import rpg.game.destructible.Destructible;

import javax.swing.*;
import java.awt.*;

public class Knight extends Player implements IPlayer {

    private Image playerImage;

    public Knight(String name) {
        super(name, PlayerCast.KNIGHT , 100, 100);
    }

    @Override
    public void attackDestructible(Destructible target) {
        // Nombre aléatoire entre 0 ou 1
        int randomResult = (int) (Math.random() * 4);
        // Attaque de la cible
        this.getSelectedWeapon().attack(target);
        // Une chance sur 4 d'attaquer une seconde fois
        if (randomResult == 3) {
            if (target.getPv() > 0) {
                this.getSelectedWeapon().attack(target);
            }
        }
    }

    @Override
    public Image getPlayerImage() {
        this.playerImage = new ImageIcon("src/rpg/images/knight.png").getImage();
        return this.playerImage;
    }
}
