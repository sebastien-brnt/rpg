package rpg.game.player;

import javax.swing.*;
import java.awt.*;

public class Archer extends Player {

    private Image playerImage;

    public Archer(String name) {
        super(name, PlayerCast.ARCHER , 80, 120);
    }

    public Image getPlayerImage() {
        this.playerImage = new ImageIcon("src/rpg/images/link.png").getImage();
        return this.playerImage;
    }
}
