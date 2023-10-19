package rpg;

import rpg.destructible.Monster;
import rpg.player.Player;
import rpg.store.WeaponStore;

public class MainGame {
    public static void main(String[] args) {

        Player player = new Player("Sébastien", 100, 150);
        Monster monster1 = new Monster("Monster 1", 100, 15);
        Monster monster2 = new Monster("Monster 2", 100, 15);

        WeaponStore store = new WeaponStore();
        System.out.println("\n================================");
        System.out.println("The store catalog is as follows:");
        System.out.println("================================");
        System.out.println(store.getWeaponList());

        System.out.println("================================");
        System.out.println("           Joueurs              ");
        System.out.println("================================");
        System.out.println(player);

        System.out.println("\n===================== ACTIONS ==========================");
        player.buyWeapon(store, store.getWeaponOfStore(3));
        player.buyWeapon(store, store.getWeaponOfStore(1));
        System.out.println(monster1.getPv());
        System.out.println("==========================================================\n");

        System.out.println("================================");
        System.out.println("           Joueurs              ");
        System.out.println("================================");
        System.out.println(player);
    }
}
