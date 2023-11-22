package rpg.ui;

import rpg.game.player.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerInfoPanel extends JPanel {

    private Player player;
    private JLabel nameLabel;
    private JLabel castLabel;
    private JLabel pvLabel;
    private JLabel moneyLabel;
    private JLabel levelLabel;
    private JLabel xpLabel;

    public PlayerInfoPanel(Player player) {
        this.player = player;
        // Ajuster les marges et l'espacement
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 20 pixels de marge sur tous les côtés
        setLayout(new GridLayout(7, 1, 5, 5)); // Espacement vertical réduit entre les lignes

        nameLabel = new JLabel("Votre nom : " + player.getName());
        nameLabel.setForeground(new Color(53, 48, 206));
        castLabel = new JLabel("Rôle : " + player.getCast());
        castLabel.setForeground(new Color(53, 48, 206));
        pvLabel = new JLabel("Vie : " + player.getPv() + " PV");
        pvLabel.setForeground(new Color(71, 159, 253));
        moneyLabel = new JLabel("Money : " + player.getMoney() + "$");
        moneyLabel.setForeground(new Color(0, 153, 0));
        levelLabel = new JLabel("Niveau : " + player.getLevel());
        levelLabel.setForeground(new Color(220, 163, 6));
        xpLabel = new JLabel("XP : " + player.getXp());
        xpLabel.setForeground(new Color(220, 163, 6));

        add(nameLabel);
        add(castLabel);
        add(pvLabel);
        add(moneyLabel);
        add(levelLabel);
        add(xpLabel);
    }

    // Mettre à jour les informations du joueur
    public void updatePlayerInfo() {
        nameLabel.setText("Votre nom : " + player.getName());
        castLabel.setText("Rôle : " + player.getCast());
        pvLabel.setText("Vie : " + player.getPv() + " PV");
        moneyLabel.setText("Money : " + player.getMoney() + "$");
        levelLabel.setText("Niveau : " + player.getLevel());
        xpLabel.setText("XP : " + player.getXp());
    }
}
