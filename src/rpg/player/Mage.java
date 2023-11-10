package rpg.player;

public class Mage extends Player {

    public Mage(String name) {
        super(name, "Mage" , 90, 110);
    }

    @Override
    public String ascii_art() {
        return "                  .\n" +
                "\n" +
                "                  .\n" +
                "        /^\\     .\n" +
                "   /\\   \"V\"\n" +
                "  /__\\   I      O  o\n" +
                " //..\\\\  I     .\n" +
                " \\].`[/  I\n" +
                " /l\\/j\\  (]    .  O\n" +
                "/. ~~ ,\\/I          .\n" +
                "\\\\L__j^\\/I       o\n" +
                " \\/--v}  I     o   .\n" +
                " |    |  I   _________\n" +
                " |    |  I c(`       ')o\n" +
                " |    l  I   \\.     ,/\n" +
                "_/j  L l\\_!  _//^---^\\\\_    -Row\n";
    }
}
