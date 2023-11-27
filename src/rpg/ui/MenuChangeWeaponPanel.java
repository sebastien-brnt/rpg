package rpg.ui;

import rpg.game.GameInputs;
import rpg.game.player.Player;
import rpg.game.store.WeaponStore;
import rpg.game.weapons.Weapon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MenuChangeWeaponPanel extends JPanel {

    private GameInputs gameInputs;
    private JTextField weaponField;
    private Player player;
    private boolean weaponIsValid = false;

    public MenuChangeWeaponPanel(GameInputs gameInputs, Player player) {
        this.gameInputs = gameInputs;
        this.player = player;
        initComponents();
    }

    public boolean setPlayerWeapon(Weapon weapon) {
        return true;
    }

    private ArrayList<Weapon> getWeaponData() {
        return player.getWeaponList();
    }

    private void initComponents() {
        // Définition des composants
        JLabel title = new JLabel("Votre inventaire");
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
        this.add(new JLabel("ID"), gbc);
        gbc.gridy++;
        this.add(new JLabel("Arme"), gbc);
        gbc.gridx++;
        this.add(new JLabel("Prix"), gbc);
        gbc.gridx++;
        this.add(new JLabel("Dégât Monstre"), gbc);
        gbc.gridx++;
        this.add(new JLabel("Dégât Obstacle"), gbc);
        gbc.gridx++;
        this.add(new JLabel("Durabilité"), gbc);
        gbc.gridy++;

        // Liste des armes
        for (Weapon weapon : weapons) {
            gbc.gridx = 0;
            this.add(new JLabel(String.valueOf(weapon.getId())), gbc);
            gbc.gridx++;
            this.add(new JLabel(weapon.getName()), gbc);
            gbc.gridx++;
            JLabel price = new JLabel(String.valueOf(weapon.getPrice()) + "$");
            price.setForeground(new Color(0, 153, 0));
            this.add(price, gbc);
            gbc.gridx++;
            JLabel damageMonster = new JLabel(String.valueOf(weapon.getDamageMonster()) + " PV");
            damageMonster.setForeground(new Color(71, 159, 253));
            this.add(damageMonster, gbc);
            gbc.gridx++;
            JLabel damageObstacle = new JLabel(String.valueOf(weapon.getDamageObstacle()) + " PV");
            damageObstacle.setForeground(new Color(71, 159, 253));
            this.add(damageObstacle, gbc);
            gbc.gridx++;
            JLabel durability = new JLabel(String.valueOf(weapon.getDurability()));
            durability.setForeground(new Color(133, 51, 0));
            this.add(durability, gbc);

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

        Weapon chosenWeapon = null;

        for(Weapon weapon : player.getWeaponList()) {
            if(weapon.getId().equals(weaponId)) {
                chosenWeapon = weapon;
            }
        }

        if (chosenWeapon != null) {
            player.changeWeapon(chosenWeapon);
            JOptionPane.showMessageDialog(this, "L'arme sélectionné est désormais " + chosenWeapon.getName());
            weaponIsValid = true;
        } else {
            JOptionPane.showMessageDialog(this, "Arme non disponible dans votre inventaire. Veuillez choisir une arme que vous avez.");
            weaponIsValid = false;
        }
    }

    public boolean isWeaponValid() {
        return weaponIsValid;
    }
}