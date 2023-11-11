package rpg.player;

import rpg.commonInterface.ConsoleRepresentable;
import rpg.destructible.Destructible;

public class Knight extends Player implements ActionsPlayer, ConsoleRepresentable {

    public Knight(String name) {
        super(name, "Chevalier" , 100, 100);
    }

    @Override
    public void attackDestructible(Destructible target) {
        // Nombre aléatoire entre 0 ou 1
        int randomResult = (int) (Math.random() * 4);

        // Attaque de la cible
        this.getSelectedWeapon().attack(target);

        // Une chance sur 4 d'attaquer une seconde fois
        if (randomResult == 3) {
            if (target.getPv() > 0) {
                this.getSelectedWeapon().attack(target);
            }
        }
    }

    @Override
    public String ascii_art() {
        return "|\\             //\n" +
                " \\\\           _!_\n" +
                "  \\\\         /___\\\n" +
                "   \\\\        [+++]\n" +
                "    \\\\    _ _\\^^^/_ _\n" +
                "     \\\\/ (    '-'  ( )\n" +
                "     /( \\/ | {&}   /\\ \\\n" +
                "       \\  / \\     / _> )\n" +
                "        \"`   >:::;-'`\"\"'-.\n" +
                "            /:::/         \\\n" +
                "           /  /||   {&}   |\n" +
                "          (  / (\\         /\n" +
                "          / /   \\'-.___.-'\n" +
                "        _/ /     \\ \\\n" +
                "       /___|    /___|\n";
    }
}
