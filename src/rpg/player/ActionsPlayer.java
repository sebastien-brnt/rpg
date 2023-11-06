package rpg.player;

import rpg.store.WeaponStore;
import rpg.weapons.Weapon;

public interface ActionsPlayer {

    /**
     * Acheter une arme
     *
     * @param store Boutique dans laquelle le joueur achète l'arme
     * @param weapon Arme que le joueur souhaite acheter
     **/
    public void buyWeapon(WeaponStore store, Weapon weapon);
}