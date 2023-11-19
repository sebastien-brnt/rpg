package rpg.ui;

import rpg.game.GameInputs;
import rpg.game.Player;
import rpg.game.store.WeaponStore;
import rpg.game.weapons.Weapon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MenuStorePanel extends JPanel {

    private GameInputs gameInputs;
    private JLabel moneyLabel;
    private JTextField weaponField;
    private WeaponStore store;
    private Player player;
    private JTable weaponTable;
    private boolean weaponIsValid = false;

    public MenuStorePanel(GameInputs gameInputs, Player player) {
        this.gameInputs = gameInputs;
        this.store = new WeaponStore();
        this.player = player;
        initComponents();
    }

    public void setPlayerWeapon(Weapon weapon) {
        this.player.buyWeapon(store, weapon);
        this.moneyLabel.setText("Vous avez " + this.player.getMoney() + "$");
    }

    private ArrayList<Weapon> getWeaponData() {
        return store.getWeaponList();
    }

    private void initComponents() {
        // Définition des composants
        JLabel title = new JLabel("Bienvenue dans la boutique");
        this.moneyLabel = new JLabel("Vous avez " + this.player.getMoney() + "$");
        JLabel weaponLabel = new JLabel("Saisissez l'ID de l'Arme : ");
        this.weaponField = new JTextField();

        // Définition du layout du panel
        this.setBorder(new EmptyBorder(50, 50, 50, 50));
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gridbag);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Utilisation de GridBagConstraints.REMAINDER pour la largeur
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Ajout des composants au panel
        // Titre
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        this.add(title, gbc);

        // Espace après le titre
        gbc.gridy++;
        this.add(Box.createVerticalStrut(10), gbc); // Espace vertical

        // Liste des armes
        ArrayList<Weapon> weapons = getWeaponData();
        gbc.gridwidth = 1;  // Remet la largeur de la grille pour un élément
        gbc.gridy++;

        // Configuration initiale des espacements entre les colonnes
        gbc.insets = new Insets(5, 10, 5, 10); // Haut, Gauche, Bas, Droit

        // En-têtes de la liste des armes
        gbc.gridx = 0;
        gbc.gridy++;
        this.add(new JLabel("Arme"), gbc);
        gbc.gridx++;
        this.add(new JLabel("Prix"), gbc);
        gbc.gridx++;
        this.add(new JLabel("Damage"), gbc);
        gbc.gridx++;
        this.add(new JLabel("Durabilité"), gbc);
        gbc.gridy++;

        // Liste des armes
        for (Weapon weapon : weapons) {
            gbc.gridx = 0; // Commencez chaque ligne à la première colonne
            this.add(new JLabel(weapon.getName()), gbc);
            gbc.gridx++;
            this.add(new JLabel(String.valueOf(weapon.getPrice())), gbc);
            gbc.gridx++;
            this.add(new JLabel(String.valueOf(weapon.getDamage())), gbc);
            gbc.gridx++;
            this.add(new JLabel(String.valueOf(weapon.getDurability())), gbc);

            // Ajouter un espace vertical après chaque arme
            gbc.gridx = 0;
            gbc.gridwidth = GridBagConstraints.REMAINDER; // Utiliser REMAINDER pour l'espace
            gbc.gridy++;
            this.add(Box.createVerticalStrut(10), gbc);

            gbc.gridwidth = 1; // Réinitialiser la largeur de la grille pour la prochaine ligne d'armes
        }

        // Espace après la liste des armes
        gbc.gridx = 0; // Commencez chaque ligne à la première colonne
        this.add(Box.createVerticalStrut(10), gbc); // Espace vertical

        // Label de l'argent du joueur
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy++;
        this.add(this.moneyLabel, gbc);

        // Espace après l'argent du joueur
        gbc.gridx = 0; // Commencez chaque ligne à la première colonne
        this.add(Box.createVerticalStrut(10), gbc); // Espace vertical


        // Label et champ de texte pour l'arme
        gbc.gridwidth = 1;
        gbc.gridy++;
        this.add(weaponLabel, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy++;
        this.add(this.weaponField, gbc);

        // Gestionnaire d'événements pour le champ de texte
        this.weaponField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String weaponId = weaponField.getText();
                    handleWeaponSelection(weaponId);
                }
            }
        });
    }



    private void handleWeaponSelection(String weaponId) {
        if (weaponId == null || weaponId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir un ID d'arme valide.");
            return;
        }

        Weapon chosenWeapon = store.getWeaponOfStore(weaponId);
        if (chosenWeapon != null) {
            setPlayerWeapon(chosenWeapon);
            weaponField.setText("");  // Réinitialiser le champ de texte après l'achat
            JOptionPane.showMessageDialog(this, "Vous avez acheté : " + chosenWeapon.getName() + " pour " + chosenWeapon.getPrice() + "$ ! Bon jeu " + player.getName() + " !");
            weaponIsValid = true;
        } else {
            JOptionPane.showMessageDialog(this, "Arme non disponible dans la boutique. Veuillez choisir une arme du catalogue.");
            weaponIsValid = false;
        }
    }

    public boolean isWeaponValid() {
        return weaponIsValid;
    }
}