package rpg.ui;

import rpg.game.GameInputs;
import rpg.game.PlayerCast;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuStartPanel extends JPanel {

    private GameInputs gameInputs;

    // Composants de l'interface utilisateur
    private JLabel title;
    private JLabel pseudoLabel;
    private JTextField pseudoField;
    private JRadioButton radioMage, radioChevalier, radioArcher;

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
        } else if (this.radioChevalier.isSelected()){
            playerClass = PlayerCast.CHEVALIER;
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
        radioChevalier = new JRadioButton("CHEVALIER");
        radioArcher = new JRadioButton("ARCHER");

        // Configuration du layout
        setBorder(new EmptyBorder(20, 20, 20, 20)); // 20px de marge tout autour
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gridbag);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Ajout du titre
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(title, gbc);

        // Configuration pour répartir les boutons radio uniformément
        gbc.weightx = 1.0; // Distribue l'espace uniformément entre les éléments
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(radioMage, gbc);

        gbc.gridx = 1;
        add(radioArcher, gbc);

        gbc.gridx = 2;
        add(radioChevalier, gbc);

        // Ajout du champ de saisie pour le nom du joueur
        gbc.weightx = 0.0; // Réinitialisation pour les autres composants
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(pseudoLabel, gbc);

        gbc.gridy = 3;
        pseudoField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    setPlayerName(pseudoField.getText());
                    setPlayerCast();
                }
            }
        });
        add(pseudoField, gbc);
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
