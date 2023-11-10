package rpg.player;

public class Chevalier extends Player {

    public Chevalier(String name) {
        super(name, "Chevalier" , 100, 100);
    }

    @Override
    public String ascii_art() {
        return "    /__\\__  //\n" +
                "    //_____\\///\n" +
                "   _| /-_-)|/_\n" +
                "  (___\\ _ //___\\\n" +
                "  (  |\\\\_/// * \\\\\n" +
                "   \\_| \\_((*   *))\n" +
                "   ( |__|_\\\\  *//\n" +
                "   (o/  _  \\_*_/\n" +
                "   //\\__|__/\\\n" +
                "  // |  | |  |\n" +
                " //  _\\ | |___)\n" +
                "//  (___|\n";
    }
}
