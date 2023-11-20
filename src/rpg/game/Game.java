package rpg.game;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Game {

    private Map map;

    private Player player;

    public Game(GameInputs gameInputs) {
        this.player = new Player(gameInputs.getPlayerName(), gameInputs.getPlayerCast(), 100, 150);
        this.map = new Map();
        this.map.setPlayer(1, 0);
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

    public void right(KeyEvent e) {
        if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
            this.map.updateMap(this.map.getPlayerX(), this.map.getPlayerY(), this.map.getBuffer());
            this.map.setBuffer(this.map.getPlayerX(), this.map.getPlayerY() + 1);
            this.map.setPlayer(this.map.getPlayerX(), this.map.getPlayerY() + 1);
            System.out.println("droite");
        }
    }

    public void left(KeyEvent e) {
        if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
            this.map.updateMap(this.map.getPlayerX(), this.map.getPlayerY(), this.map.getBuffer());
            this.map.setBuffer(this.map.getPlayerX(), this.map.getPlayerY() - 1);
            this.map.setPlayer(this.map.getPlayerX(), this.map.getPlayerY() - 1);
            System.out.println("gauche");
        }
    }

    public void top(KeyEvent e) {
        if (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') {
            this.map.updateMap(this.map.getPlayerX(), this.map.getPlayerY(), this.map.getBuffer());
            this.map.setBuffer(this.map.getPlayerX() - 1, this.map.getPlayerY());
            this.map.setPlayer(this.map.getPlayerX() - 1, this.map.getPlayerY());
            System.out.println("haut");
        }
    }

    public void bottom(KeyEvent e) {
        if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
            this.map.updateMap(this.map.getPlayerX(), this.map.getPlayerY(), this.map.getBuffer());
            this.map.setBuffer(this.map.getPlayerX() + 1, this.map.getPlayerY());
            this.map.setPlayer(this.map.getPlayerX() + 1, this.map.getPlayerY());
            System.out.println("bas");
        }
    }
}
