package rpg.player;

import rpg.commonInterface.ConsoleRepresentable;
import rpg.destructible.Destructible;

public class Archer extends Player implements ActionsPlayer, ConsoleRepresentable {

    public Archer(String name) {
        super(name, "Archer" , 80, 120);
    }


    @Override
    public void attackDestructible(Destructible target) {
        // Attaque de la cible
        this.getSelectedWeapon().attack(target);

        // Gagne 5$ et 10 XP à chaque coup
        System.out.println();
        this.addMoney(2.5, true);
        this.addXp(10, true);
        System.out.println();
    }

    @Override
    public String ascii_art() {
        return "         /|   \\\n" +
                "        /_|_{)/\n" +
                "---<<   | |  )\n" +
                "        \\ |  (\n" +
                "         \\|__)\n" +
                "           \\ |__\n" +
                "           ~    ~\n";
    }
}
