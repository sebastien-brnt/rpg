package rpg.ui;

import rpg.game.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerInfoPanel extends JPanel {

    private Player player;

    public PlayerInfoPanel(Player player) {
        this.player = player;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawString("Votre nom : " + this.player.getName(), 10, 20);
        g.drawString("Rôle : " + this.player.getCast(), 10, 40);
        g.drawString("Vie : " + this.player.getPv() + " PV", 10, 60);
        g.drawString("Money : " + this.player.getMoney() + "$", 10, 80);
        g.drawString("Niveau : " + this.player.getLevel(), 10, 100);
        g.drawString("XP : " + this.player.getXp(), 10, 120);
    }
}
