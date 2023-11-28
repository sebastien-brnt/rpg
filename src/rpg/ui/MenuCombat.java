package rpg.ui;

import rpg.game.destructible.Destructible;
import rpg.game.destructible.Monster;
import rpg.game.destructible.Obstacle;
import rpg.game.player.Player;

import javax.swing.*;
import java.awt.*;

public class MenuCombat extends JPanel {

        private JSplitPane splitPane;

        private JPanel imagesPanel;

        // Info panel
        private JLabel titrePlayerInfos;
        private JPanel infoPanel;
        private Player player;
        private JLabel pvLabel;
        private JLabel playerWeapon;
        private JLabel playerDamage;

        // Destructible Panel
        private JPanel actionsPanel;
        private JLabel titreDestructibleInfos;
        private JLabel pvLabelDestructible;
        private JLabel attackDestructible;
        private Destructible destructible;

        // Images
        private JLabel playerImageLabel;
        private JLabel destructibleImageLabel;

        public MenuCombat(Player player, Destructible destructible) {
            this.player = player;
            this.destructible = destructible;

            // Récupération des Images du joueur et du destructible
            Image playerImage = player.getPlayerImage();
            Image destructibleImage = destructible.getImage();
            // Redimensionnement de l'image du joueur
            ImageIcon playerIcon = new ImageIcon(player.getPlayerImage());
            Image playerImg = playerIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            playerImageLabel = new JLabel(new ImageIcon(playerImg));

            // Redimensionnement de l'image du destructible
            ImageIcon destructibleIcon = new ImageIcon(destructible.getImage());
            Image destructibleImg = destructibleIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            destructibleImageLabel = new JLabel(new ImageIcon(destructibleImg));


            imagesPanel = new JPanel(new BorderLayout());
            imagesPanel.add(playerImageLabel, BorderLayout.WEST);
            imagesPanel.add(destructibleImageLabel, BorderLayout.EAST);
            imagesPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 40, 60)); // 20 pixels de marge sur tous les côtés

            add(imagesPanel, BorderLayout.NORTH);


            setLayout(new BorderLayout());
            infoPanel = new JPanel();
            infoPanel.setLayout(new GridLayout(7, 1, 5, 5)); // Espacement vertical réduit entre les lignes
            infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 20 pixels de marge sur tous les côtés

            // Initialisation des labels
            initializeLabels();

            // Ajout des labels au panneau d'information
            infoPanel.add(titrePlayerInfos);
            infoPanel.add(pvLabel);
            infoPanel.add(playerWeapon);
            infoPanel.add(playerDamage);

            // Création du panneau des actions
            actionsPanel = new JPanel();
            actionsPanel.setLayout(new GridLayout(7, 1, 5, 5)); // Espacement vertical réduit entre les lignes
            actionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 20 pixels de marge sur tous les côtés

            // Ajout des labels au actions
            actionsPanel.add(titreDestructibleInfos);
            actionsPanel.add(pvLabelDestructible);
            actionsPanel.add(attackDestructible);

            // Création du JSplitPane
            splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, infoPanel, actionsPanel);
            splitPane.setDividerLocation(350);

            add(imagesPanel, BorderLayout.NORTH);
            add(splitPane, BorderLayout.CENTER);

        }

        private void initializeLabels() {
            // Info player
            titrePlayerInfos = new JLabel("Vos informations :");
            titrePlayerInfos.setFont(new Font("Segoe UI", Font.BOLD, 14));

            pvLabel = new JLabel("Vie : " + player.getPv() + " PV");
            pvLabel.setForeground(new Color(71, 159, 253));
            playerWeapon = new JLabel("Votre arme : " + player.getSelectedWeapon().getName());
            playerWeapon.setForeground(new Color(138, 71, 253));

            if (destructible instanceof Obstacle) {
                playerDamage = new JLabel("Dégât par coup : " + player.getSelectedWeapon().getDamageObstacle() + " PV");
            } else if (destructible instanceof Monster) {
                playerDamage = new JLabel("Dégât par coup : " + player.getSelectedWeapon().getDamageMonster() + " PV");
            }
            playerDamage.setForeground(new Color(253, 70, 50));

            // Action player
            titreDestructibleInfos = new JLabel("Informations de l'ennemi :");
            titreDestructibleInfos.setFont(new Font("Segoe UI", Font.BOLD, 14));

            pvLabelDestructible = new JLabel("Vie : " + player.getPv() + " PV");
            pvLabelDestructible.setForeground(new Color(71, 159, 253));
            attackDestructible = new JLabel("Dégât par coup : " + destructible.getDamage() + " PV");
            attackDestructible.setForeground(new Color(253, 70, 50));
        }

        // Mettre à jour les informations
        public void updateInfos() {
            pvLabel.setText("Vie : " + player.getPv() + " PV");
            pvLabelDestructible.setText("Vie : " + destructible.getPv() + " PV");
        }
    }
