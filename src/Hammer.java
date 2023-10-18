public class Hammer extends Weapon {
    public Hammer(int id) {
        super(id, "Hammer", 10, 50);
    }

    public String ascii_art() {
        return  " /(               \n" +
                "|  >:=========== \n" +
                " )(               \n";
    }

}
