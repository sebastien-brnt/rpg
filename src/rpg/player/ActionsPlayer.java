package rpg.player;

import rpg.store.WeaponStore;
import rpg.weapons.Weapon;

public interface ActionsPlayer {

    // Acheter une arme
    public void buyWeapon(WeaponStore store, Weapon weapon);
}