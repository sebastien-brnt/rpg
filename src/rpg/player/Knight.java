package rpg.player;

public class Knight extends Player {

    public Knight(String name) {
        super(name, "Chevalier" , 100, 100);
    }

    @Override
    public String ascii_art() {
        return "|\\             //\n" +
                " \\\\           _!_\n" +
                "  \\\\         /___\\\n" +
                "   \\\\        [+++]\n" +
                "    \\\\    _ _\\^^^/_ _\n" +
                "     \\\\/ (    '-'  ( )\n" +
                "     /( \\/ | {&}   /\\ \\\n" +
                "       \\  / \\     / _> )\n" +
                "        \"`   >:::;-'`\"\"'-.\n" +
                "            /:::/         \\\n" +
                "           /  /||   {&}   |\n" +
                "          (  / (\\         /\n" +
                "          / /   \\'-.___.-'\n" +
                "        _/ /     \\ \\\n" +
                "       /___|    /___|\n";
    }
}
