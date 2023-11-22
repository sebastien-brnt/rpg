package rpg.game.player;

import javax.swing.*;
import java.awt.*;

public class Mage extends Player {

    private Image playerImage;

    public Mage(String name) {
        super(name, PlayerCast.MAGE , 90, 110);
    }

    public Image getPlayerImage() {
        this.playerImage = new ImageIcon("src/rpg/images/mage.png").getImage();
        return this.playerImage;
    }
}
