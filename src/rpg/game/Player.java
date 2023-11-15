package rpg.game;

public class Player {

    private String name;

    private PlayerCast cast;

    public Player(String n, PlayerCast c) {
        this.name = n;
        this.cast = c;
    }

    public String getName() {
        return this.name;
    }

    public PlayerCast getCast() {
        return this.cast;
    }


}
