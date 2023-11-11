package rpg.player;

import rpg.destructible.Destructible;
import rpg.store.WeaponStore;
import rpg.weapons.Weapon;

public interface ActionsPlayer {

    /**
     * Attaque une entité Destructible
     *
     * @param target Cible de l'attaque (Destructible)
     **/
    public void attackDestructible(Destructible target);
}