package rpg.game.player;

import javax.swing.*;
import java.awt.*;

public class Knight extends Player {

    private Image playerImage;

    public Knight(String name) {
        super(name, PlayerCast.KNIGHT , 100, 100);
    }

    public Image getPlayerImage() {
        this.playerImage = new ImageIcon("src/rpg/images/knight.png").getImage();
        return this.playerImage;
    }
}
