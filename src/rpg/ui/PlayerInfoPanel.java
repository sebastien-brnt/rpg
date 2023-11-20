package rpg.ui;

import rpg.game.Player;

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
    private JLabel actionLabel;

    public PlayerInfoPanel(Player player) {
        this.player = player;
        // Ajuster les marges et l'espacement
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 20 pixels de marge sur tous les côtés
        setLayout(new GridLayout(7, 1, 5, 5)); // Espacement vertical réduit entre les lignes

        nameLabel = new JLabel("Votre nom : " + player.getName());
        castLabel = new JLabel("Rôle : " + player.getCast());
        pvLabel = new JLabel("Vie : " + player.getPv() + " PV");
        moneyLabel = new JLabel("Money : " + player.getMoney() + "$");
        levelLabel = new JLabel("Niveau : " + player.getLevel());
        xpLabel = new JLabel("XP : " + player.getXp());
        actionLabel = new JLabel("Action : ");

        add(nameLabel);
        add(castLabel);
        add(pvLabel);
        add(moneyLabel);
        add(levelLabel);
        add(xpLabel);
        add(actionLabel);
    }

    // Mettre à jour les informations du joueur
    public void updatePlayerInfo() {
        nameLabel.setText("Votre nom : " + player.getName());
        castLabel.setText("Rôle : " + player.getCast());
        pvLabel.setText("Vie : " + player.getPv() + " PV");
        moneyLabel.setText("Money : " + player.getMoney() + "$");
        levelLabel.setText("Niveau : " + player.getLevel());
        xpLabel.setText("XP : " + player.getXp());
        actionLabel.setText("Action : ");
    }
}
