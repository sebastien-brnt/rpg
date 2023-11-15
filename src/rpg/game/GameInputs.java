package rpg.game;

public class GameInputs {
    private String playerName;
    private PlayerCast playerCast;

    public GameInputs() {
        this.playerName = "";
        this.playerCast = null;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public PlayerCast getPlayerCast() {
        return this.playerCast;
    }

    public void setPlayerName(String n) {
        this.playerName = n;
    }

    public void setPlayerCast(PlayerCast c) {
        this.playerCast = c;
    }

}
