package rpg;

public class Monster {
    private String name;
    private double pv;
    private double hit;


    public Monster(String name, double pv, double hit) {
        this.name = name;
        this.pv = pv;
        this.hit = hit;
    }

    public double getPv() {
        return pv;
    }

    public double getHit() {
        return hit;
    }

    public void attackPlayer(Player player) {
        player.removePv(this.hit);
    }

    public void removePv(double hit) {
        this.pv = this.pv - hit;
    }
}
