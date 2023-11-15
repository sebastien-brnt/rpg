package rpg.game;

public class Player {

    private String name;

    private double pv;
    private double money;
    private double xp;
    private int level;

    private PlayerCast cast;

    public Player(String name, PlayerCast cast, double pv, double money) {
        this.name = name;
        this.cast = cast;
        this.pv = pv;
        this.money = money;
        this.xp = 0;
        this.level = 0;
    }

    public String getName() {
        return this.name;
    }
    public PlayerCast getCast() {
        return this.cast;
    }
    public double getPv() {
        return this.pv;
    }
    public double getXp() {
        return this.xp;
    }
    public void addMoney(double value, boolean showMessage) {
        this.money += value;

        if (showMessage) {
            System.out.println("Vous avez gagné " + value + "$" + "!");
            System.out.println("Votre nouveau solde est de " + this.getMoney() + "$" + "!");
        }
    }
    public void addMoney(double value) {
        addMoney(value, false);
    }
    public void addXp(double value, boolean showMessage) {
        this.xp += value;

        if (showMessage) {
            System.out.println("Vous avez gagné " + value + " XP !");

            int levelCount = 0;

            while ((this.getXp() - (this.getLevel() * 140)) > this.getNextLevel() - this.getLevel() * 140) {
                levelCount++;
                this.addLevel(1);
            }

            if (levelCount > 0) {
                System.out.println("Vous avez gagné " + levelCount + " niveau ! Vous êtes désormais niveau " +  this.getLevel() + " !");
            }
        }

    }
    public void addXp(double value) {
        this.addXp(value, false);
    }

    public void removeXp(double value) {
        this.pv -= value;
    }
    public int getLevel() {
        return this.level;
    }
    public double getNextLevel() {
        return ((this.level + 1) * 140);
    }

    public void addLevel(int value) {
        this.level += value;
    }
    public double getMoney() {
        return this.money;
    }

}
