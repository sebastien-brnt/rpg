package rpg.ui;

import javax.swing.*;
import java.awt.*;

public class MenuRulesPanel extends JPanel {

    public MenuRulesPanel() {
        setLayout(new BorderLayout());
        JTextArea rulesText = new JTextArea();
        rulesText.setText(
                "Règles du jeu:\n\n" +
                        "Joueur:\n" +
                        "Vous démarrez la partie avec 100 PV et 150$. Vous êtes positionné en haut à gauche de la map et vous devez arriver en bas à droite sans mourir !\n\n" +
                        "Chaque personnage possède des avantages !\n" +
                        "Chevalier : Vous avez 1 chance sur 4 d'infliger 2 fois votre attaque.\n" +
                        "Archer : Vous gagnez 2,5$ et 10 XP par attaque.\n" +
                        "Mage : Vous avez 1 chance sur 2 de gagner 5 PV lors de chaque attaque.\n\n\n" +
                        "Déplacements:\n" +
                        "Vous pouvez vous déplacer à l’aide des touches Z (haut), Q (gauche), S (bas), D (droite). Il vous est impossible de vous déplacer sur les murs, représentés par des carrés noirs. Il vous est également impossible de vous déplacer sur une case contenant un Monstre ou un Obstacle, vous devez les combattre et les vaincre afin de libérer la case et pouvoir vous déplacer dessus. Vous pouvez en revanche aller sur les cases avec de l’argent, il vous suffira ensuite d’appuyer sur R pour ramasser l’argent. Pour entrer dans la boutique, il vous suffit d’aller sur la case, la boutique s’ouvrira toute seule.\n\n\n" +
                        "Combat:\n" +
                        "Afin de combattre un Monstre ou un Obstacle vous devez être sur une case à côté de l’élément. Les touches sont L (combat contre un obstacle) et K (combat contre un monstre). Une fois en combat, vous devez appuyer sur Entrer pour donner un coup. Vous ne pouvez pas changer d’arme pendant le combat, vous devrez quitter afin de changer l’arme sélectionnée avec la touche C (ouverture du menu de changement d’arme) et ensuite saisir l’identifiant de l’arme souhaité puis appuyer sur entrer. À la fin de chaque combat vous aurez des récompenses (XP, Argent et PV), il n’y a pas de gain de PV contre les obstacles. Attention à ne pas mourir, sinon la partie sera perdue !"
        );
        rulesText.setEditable(false);
        rulesText.setWrapStyleWord(true);
        rulesText.setLineWrap(true);
        rulesText.setOpaque(false);
        rulesText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rulesText.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(rulesText);
        scrollPane.setPreferredSize(new Dimension(510, 250));
        scrollPane.setMinimumSize(new Dimension(510, 250));
        scrollPane.setMaximumSize(new Dimension(510, 250));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        add(scrollPane, BorderLayout.CENTER);
    }
}
