package rpg.game;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Game {

    private Map map;

    private Player player;

    public Game(GameInputs gameInputs) {
        this.player = new Player(gameInputs.getPlayerName(), gameInputs.getPlayerCast(), 100, 150);
        this.map = new Map();
    }

    public Map getMap(){
        return this.map;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getTarget() {
        return "votre mission est de rejoindre le point vert sans mourir. Bon jeu !";
    }

    public static void right(KeyEvent e, JLabel label) {
        if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
            label.setText("Vous vous êtes déplacé vers la droite");
        }
    }
}
