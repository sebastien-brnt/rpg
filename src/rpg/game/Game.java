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
            int playerX = this.map.getPlayerX();
            int playerY = this.map.getPlayerY();
            System.out.println(this.getMap().getMap()[playerX][playerY + 1]);
            if (this.getMap().getMap()[playerX][playerY + 1] != 1) {
                this.map.updateMap(playerX, playerY, this.map.getBuffer());
                this.map.setBuffer(playerX, playerY + 1);
                this.map.setPlayer(playerX, playerY + 1);
                System.out.println("droite");
            } else {
                System.out.println("Action impossible");
            }
        }
    }

    public void left(KeyEvent e) {
        if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
            int playerX = this.map.getPlayerX();
            int playerY = this.map.getPlayerY();
            if (this.getMap().getMap()[playerX][playerY - 1] != 1) {
                this.map.updateMap(playerX, playerY, this.map.getBuffer());
                this.map.setBuffer(playerX, playerY - 1);
                this.map.setPlayer(playerX, playerY - 1);
                System.out.println("gauche");
            } else {
                System.out.println("Action impossible");
            }
        }
    }

    public void top(KeyEvent e) {
        if (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') {
            int playerX = this.map.getPlayerX();
            int playerY = this.map.getPlayerY();
            if (this.getMap().getMap()[playerX - 1][playerY] != 1) {
                this.map.updateMap(playerX, playerY, this.map.getBuffer());
                this.map.setBuffer(playerX - 1, playerY);
                this.map.setPlayer(playerX - 1, playerY);
                System.out.println("haut");
            } else {
                System.out.println("Action impossible");
            }
        }
    }

    public void bottom(KeyEvent e) {
        if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
            int playerX = this.map.getPlayerX();
            int playerY = this.map.getPlayerY();
            if (this.getMap().getMap()[playerX + 1][playerY] != 1) {
                this.map.updateMap(playerX, playerY, this.map.getBuffer());
                this.map.setBuffer(playerX + 1, playerY);
                this.map.setPlayer(playerX + 1, playerY);
                System.out.println("bas");
            } else {
                System.out.println("Action impossible");
            }
        }
    }
}
