package rpg;

public class Obstacle {
    private double pv;


    public Obstacle(double pv) {
        this.pv = pv;
    }

    public double getPv() {
        return pv;
    }

    public void removePv(double hit) {
        this.pv = this.pv - hit;
    }
}
