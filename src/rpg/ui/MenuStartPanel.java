package rpg.ui;

import rpg.game.GameInputs;
import rpg.game.player.PlayerCast;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuStartPanel extends JPanel {

    final MenuStartPanel thisPanel = this;
    private GameInputs gameInputs;

    // Composants de l'interface utilisateur
    private JLabel title;
    private JLabel pseudoLabel;
    private JTextField pseudoField;
    private JRadioButton radioMage, radioKnight, radioArcher;

    private boolean isValidPseudo = false;

    // Constructeur
    public MenuStartPanel(GameInputs gameInputs) {
        this.gameInputs = gameInputs;
        initComponents();
    }

    // Méthode pour définir le nom du joueur
    private void setPlayerName(String name) {
        this.gameInputs.setPlayerName(name);
    }

    // Méthode pour définir la classe du joueur
    private void setPlayerCast() {
        PlayerCast playerClass = null;
        if (this.radioMage.isSelected()) {
            playerClass = PlayerCast.MAGE;
        } else if (this.radioArcher.isSelected()) {
            playerClass = PlayerCast.ARCHER;
        } else if (this.radioKnight.isSelected()){
            playerClass = PlayerCast.KNIGHT;
        }
        this.gameInputs.setPlayerCast(playerClass);
    }

    // Initialisation des composants de l'interface utilisateur
    private void initComponents() {
        // Initialisation des composants
        title = new JLabel("Bienvenue dans votre monde");
        pseudoLabel = new JLabel("Choisissez votre nom : ");
        pseudoField = new JTextField();
        radioMage = new JRadioButton("MAGE");
        radioKnight = new JRadioButton("CHEVALIER");
        radioArcher = new JRadioButton("ARCHER");

        // Création du groupe de boutons
        ButtonGroup group = new ButtonGroup();
        group.add(radioMage);
        group.add(radioKnight);
        group.add(radioArcher);

        // Sélection de archer par défaut
        radioArcher.setSelected(true);

        // Configuration du layout
        setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gridbag);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Ajout du titre
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        gbc.insets = new Insets(0, 0, 30, 0);
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(title, gbc);

        // Configuration pour répartir les boutons radio uniformément
        gbc.insets = new Insets(0, 0, 30, 0);
        gbc.weightx = 1.0;
        gbc.gridwidth = 1;
        gbc.gridx = 0;

        // Ajout des boutons radio
        gbc.gridy = 1;
        add(radioMage, gbc);

        gbc.gridx = 1;
        add(radioArcher, gbc);

        gbc.gridx = 2;
        add(radioKnight, gbc);

        // Ajout du champ de saisie pour le nom du joueur
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.weightx = 0.0;
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(pseudoLabel, gbc);

        gbc.gridy = 3;
        pseudoField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                 if (pseudoField.getText().isEmpty() && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    JOptionPane.showMessageDialog(thisPanel, "Vous devez entrer un pseudo pour commencer le jeu");
                    isValidPseudo = false;
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    setPlayerName(pseudoField.getText());
                    setPlayerCast();
                    isValidPseudo = true;
                }
            }
        });
        add(pseudoField, gbc);
    }

    public boolean isValidPseudo() {
        return isValidPseudo;
    }


    // Méthode auxiliaire pour ajouter des composants au GridBagLayout
    private void addToGridBag(Component component, int x, int y, GridBagLayout gridBag, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(20, 0, 20, 0);
        gridBag.setConstraints(component, gbc);
        add(component);
    }

}
