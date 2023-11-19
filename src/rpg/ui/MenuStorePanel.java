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

public class MenuStorePanel extends JPanel {

    private GameInputs gameInputs;

    private JLabel title;

    private JLabel weaponLabel;
    private JLabel moneyLabel;
    private JTextField weaponField;

    private WeaponStore store;

    private Player player;

    private JButton okButton;

    public MenuStorePanel(GameInputs gameInputs, Player player) {
        this.gameInputs = gameInputs;
        this.store = new WeaponStore();
        this.player = player;
        initComponents();
    }

    public void setPlayerWeapon(Weapon weaponId) {
        this.player.buyWeapon(store, weaponId);
        this.moneyLabel.setText("Vous avez " + this.player.getMoney() + "$");
    }

    private void initComponents() {
        // define components
        this.title = new JLabel("Bienvenue dans la boutique");
        this.moneyLabel = new JLabel("Vous avez " + this.player.getMoney() + "$");
        this.weaponLabel = new JLabel("Saisissez l'ID de l'Arme : ");
        this.weaponField = new JTextField();

        // define panel layout
        this.setBorder(new EmptyBorder(50, 50, 50, 50));
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        /* GridBagConstraints parameters :
            int gridx,
            int gridy,
            int gridwidth,
            int gridheight,
            double weightx,
            double weighty,
            int anchor,
            int fill,
            java.awt.Insets insets,
            int ipadx,
            int ipady
         */
        this.setLayout(gridbag);
        gbc.fill = GridBagConstraints.BOTH;

        // add components to panel
        // title
        this.title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        gbc.gridwidth = 3;  // 3 columns wide
        gbc.gridx = 0;
        gbc.gridy = 0;
        gridbag.setConstraints(this.title, gbc);
        this.add(this.title);

        gbc.gridwidth = 1;  // 3 columns wide
        gbc.gridx = 0;
        gbc.gridy = 1;
        gridbag.setConstraints(this.moneyLabel, gbc);
        this.add(this.moneyLabel);

        // weapon of the player
        this.add(this.weaponLabel,
                new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(50, 0, 30, 0), 0, 0));
        this.weaponField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    JTextField textField = (JTextField) e.getSource();

                    String firstWeapon = textField.getText();


                    // Récupération de l'arme dans le store
                    Weapon chosenWeapon = store.getWeaponOfStore(firstWeapon);

                    if (chosenWeapon != null) {
                        // Achat de l'arme
                        setPlayerWeapon(chosenWeapon);
                    } else {
                        System.out.println("Arme non disponible dans la boutique. Veuillez choisir une arme du catalogue.\n");
                    }

                }
            }
        });
        this.add(this.weaponField,
                new GridBagConstraints(1, 2, 3, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(50, 0, 30, 0), 0, 0));
    }
}
