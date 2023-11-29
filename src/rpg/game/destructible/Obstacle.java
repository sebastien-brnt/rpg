package rpg.game.destructible;

import javax.swing.*;
import java.awt.*;

public class Obstacle extends Destructible {

    public static double PV = 50;
    public static double HIT = 0;
    public Obstacle(String name) {
        super(name, PV, HIT);
    }

    public Image getImage() {
        return new ImageIcon("src/rpg/images/tree.png").getImage();
    }
}
