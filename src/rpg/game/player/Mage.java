package rpg.player;

import rpg.commonInterface.ConsoleRepresentable;
import rpg.destructible.Destructible;

public class Mage extends Player implements ActionsPlayer, ConsoleRepresentable {

    public Mage(String name) {
        super(name, "Mage" , 90, 110);
    }

    @Override
    public void attackDestructible(Destructible target) {
        // Nombre aléatoire entre 0 ou 1
        int randomResult = (int) (Math.random() * 2);

        // Attaque de la cible
        this.getSelectedWeapon().attack(target);

        // Une chance sur 2 de récupérer 5 PV en plus de l'attaque
        if (randomResult == 1) {
            this.addPv(5, true);
        }
    }

    @Override
    public String ascii_art() {
        return "                  .\n" +
                "\n" +
                "                  .\n" +
                "        /^\\     .\n" +
                "   /\\   \"V\"\n" +
                "  /__\\   I      O  o\n" +
                " //..\\\\  I     .\n" +
                " \\].`[/  I\n" +
                " /l\\/j\\  (]    .  O\n" +
                "/. ~~ ,\\/I          .\n" +
                "\\\\L__j^\\/I       o\n" +
                " \\/--v}  I     o   .\n" +
                " |    |  I   _________\n" +
                " |    |  I c(`       ')o\n" +
                " |    l  I   \\.     ,/\n" +
                "_/j  L l\\_!  _//^---^\\\\_    -Row\n";
    }
}
