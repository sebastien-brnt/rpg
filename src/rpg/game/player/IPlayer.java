package rpg.game.player;

import rpg.game.destructible.Destructible;

import java.awt.*;

public interface IPlayer {
    /**
     * Attaque une entité Destructible
     *
     * @param target Cible de l'attaque (Destructible)
     **/
    public void attackDestructible(Destructible target);

    /**
     * Récupère l'image du joueur
     **/
    public Image getPlayerImage();
}
