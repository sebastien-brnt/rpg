import java.util.ArrayList;

public class MainGame {
    public static void main(String[] args) {

        Player player = new Player("Sébastien", 150);

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
        System.out.println("==========================================================\n");

        System.out.println("================================");
        System.out.println("           Joueurs              ");
        System.out.println("================================");
        System.out.println(player);
    }
}
