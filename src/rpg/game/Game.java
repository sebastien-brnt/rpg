package rpg.game;

import rpg.game.destructible.Destructible;
import rpg.game.destructible.Monster;
import rpg.game.destructible.Obstacle;
import rpg.game.player.*;
import rpg.game.store.WeaponStore;
import rpg.ui.DialogBoxAttackDestructible;
import rpg.ui.DialogBoxChangeWeapon;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Game {

    private Map map;

    private Player player;

    private boolean gameIsFinish = false;

    public Game(GameInputs gameInputs) {
        // Création du joueur en fonction du cast
        if (gameInputs.getPlayerCast() == PlayerCast.MAGE) {
            this.player = new Mage(gameInputs.getPlayerName());
        } else if (gameInputs.getPlayerCast() == PlayerCast.ARCHER) {
            this.player = new Archer(gameInputs.getPlayerName());
        } else if (gameInputs.getPlayerCast() == PlayerCast.KNIGHT) {
            this.player = new Knight(gameInputs.getPlayerName());
        }

        // Création de la map
        this.map = new Map();

        // Initialisation du joueur sur la map
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
            if (this.map.inTheMap(playerX, playerY + 1) && (((this.map.getMap()[playerX][playerY + 1] instanceof Integer) && (Integer) this.map.getMap()[playerX][playerY + 1] != 1) || this.map.getMap()[playerX][playerY + 1] instanceof WeaponStore)) {
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
            if (this.map.inTheMap(playerX, playerY - 1) && ((this.map.getMap()[playerX][playerY - 1] instanceof Integer &&  (Integer) this.map.getMap()[playerX][playerY - 1] != 1) || this.map.getMap()[playerX][playerY - 1] instanceof WeaponStore)) {
                this.map.updateMap(playerX, playerY, this.map.getBuffer());
                this.map.setBuffer(playerX, playerY - 1);
                this.map.setPlayer(playerX, playerY - 1);
                System.out.println("gauche");
            } else {
                System.out.println("Action impossible");
            }
        }
    }

    public void openInventory(KeyEvent e, JFrame window) {
        if (e.getKeyChar() == 'i' || e.getKeyChar() == 'I') {
            new DialogBoxChangeWeapon(window, player);
        }
    }

    public void top(KeyEvent e) {
        if (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') {
            int playerX = this.map.getPlayerX();
            int playerY = this.map.getPlayerY();
            if (this.map.inTheMap(playerX - 1, playerY) && ((this.map.getMap()[playerX - 1][playerY] instanceof Integer &&  (Integer) this.map.getMap()[playerX - 1][playerY] != 1) || this.map.getMap()[playerX - 1][playerY] instanceof WeaponStore)) {
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
            if (this.map.inTheMap(playerX + 1, playerY) && ((this.map.getMap()[playerX + 1][playerY] instanceof Integer &&  (Integer) this.map.getMap()[playerX + 1][playerY] != 1) || this.map.getMap()[playerX + 1][playerY] instanceof WeaponStore)) {
                this.map.updateMap(playerX, playerY, this.map.getBuffer());
                this.map.setBuffer(playerX + 1, playerY);
                this.map.setPlayer(playerX + 1, playerY);
                System.out.println("bas");
            } else {
                System.out.println("Action impossible");
            }
        }
    }

    public void getMoney(KeyEvent e) {
        if (e.getKeyChar() == 'r' || e.getKeyChar() == 'R') {
            if (map.getBuffer() instanceof Integer && (Integer) map.getBuffer() == 7) {
                // Nombre aléatoire entre 1 et 30
                int winMoney = (int)(Math.random() * 29) + 1;

                // Ajout de l'argent au joueur
                this.player.addMoney(winMoney);

                // Mise à jour du buffer
                map.setBuffer(0);

                System.out.println(winMoney + "$ ramassé");
            }
        }
    }

    public void attackObstacle(KeyEvent e, JFrame window) {
        if (e.getKeyChar() == 'l' || e.getKeyChar() == 'L') {
            int playerX = this.map.getPlayerX();
            int playerY = this.map.getPlayerY();

            if (map.getPresenceAround(playerX, playerY, Obstacle.class)) {
                // Détermine l'emplacement de l'obstacle à attaquer
                int[] obstaclePosition = player.findAdjacentEntityPosition(playerX, playerY, Obstacle.class, this.getMap(), this.map.getMap());

                if (obstaclePosition != null) {
                    Obstacle obstacle = (Obstacle) map.getMap()[obstaclePosition[0]][obstaclePosition[1]];

                    new DialogBoxAttackDestructible(window, player, obstacle, map, obstaclePosition[0], obstaclePosition[1]);
                } else {
                    System.out.println("aucun obstacle à proximité");
                }
            }
        }
    }

    public void attackMonster(KeyEvent e, JFrame window) {
        if (e.getKeyChar() == 'k' || e.getKeyChar() == 'K') {
            int playerX = this.map.getPlayerX();
            int playerY = this.map.getPlayerY();

            if (map.getPresenceAround(playerX, playerY, Monster.class)) {
                // Détermine l'emplacement de le monstre à attaquer
                int[] monsterPosition = player.findAdjacentEntityPosition(playerX, playerY, Monster.class, this.getMap(), this.map.getMap());

                if (monsterPosition != null) {
                    Monster monster = (Monster) map.getMap()[monsterPosition[0]][monsterPosition[1]];

                    new DialogBoxAttackDestructible(window, player, monster, map, monsterPosition[0], monsterPosition[1]);
                } else {
                    System.out.println("aucun monstre à proximité");
                }
            }
        }
    }
    
    public boolean gameIsFinish() {
        return gameIsFinish;
    }

    public void setGameFinish(boolean value) {
        this.gameIsFinish = value;
    }
}
