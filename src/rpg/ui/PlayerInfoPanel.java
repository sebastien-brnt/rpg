package rpg.ui;

import rpg.game.player.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerInfoPanel extends JPanel {

    private JSplitPane splitPane;

    // Info panel
    private JPanel infoPanel;
    private Player player;
    private JLabel nameLabel;
    private JLabel castLabel;
    private JLabel pvLabel;
    private JLabel moneyLabel;
    private JLabel levelLabel;
    private JLabel xpLabel;

    // Action Panel
    private JPanel actionsPanel;
    private JLabel droite;
    private JLabel gauche;
    private JLabel haut;
    private JLabel bas;
    private JLabel inventare;
    private JLabel ramasserArgent;

    public PlayerInfoPanel(Player player) {
        this.player = player;

        setLayout(new BorderLayout());
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(7, 1, 5, 5)); // Espacement vertical réduit entre les lignes
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 20 pixels de marge sur tous les côtés

        // Initialisation des labels
        initializeLabels();

        // Ajout des labels au panneau d'information
        infoPanel.add(nameLabel);
        infoPanel.add(castLabel);
        infoPanel.add(pvLabel);
        infoPanel.add(moneyLabel);
        infoPanel.add(levelLabel);
        infoPanel.add(xpLabel);

        // Création du panneau des actions
        actionsPanel = new JPanel();
        actionsPanel.setLayout(new GridLayout(7, 1, 5, 5)); // Espacement vertical réduit entre les lignes
        actionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 20 pixels de marge sur tous les côtés

        // Ajout des labels au actions
        actionsPanel.add(haut);
        actionsPanel.add(bas);
        actionsPanel.add(gauche);
        actionsPanel.add(droite);
        actionsPanel.add(ramasserArgent);
        actionsPanel.add(inventare);

        // Création du JSplitPane
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, infoPanel, actionsPanel);
        splitPane.setDividerLocation(400); // Vous pouvez ajuster cette valeur selon vos besoins

        add(splitPane, BorderLayout.CENTER);
    }

    private void initializeLabels() {
        // Info player
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

        // Action player
        haut = new JLabel("[Z] : Aller vers le haut");
        gauche = new JLabel("[Q] : Aller sur la gauche");
        bas = new JLabel("[S] : Aller vers le bas");
        droite = new JLabel("[D] : Aller sur la droite");
        ramasserArgent = new JLabel("[R] : Ramasser l'argent");
        inventare = new JLabel("[I] : Changer d'arme");
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
