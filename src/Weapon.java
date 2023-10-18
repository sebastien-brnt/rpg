public abstract class Weapon {
    private int id;
    private String name;
    private double damage;
    private double price;

    public Weapon(int id, String name, double damage, double price) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDamage() {
        return damage;
    }

    public double getPrice() {
        return price;
    }

    public Weapon getWeapon() {
        return this;
    }

    public String toString() {
        return  "\n" + "ID : " + this.id + "\n" +
                "Name : " + this.name + "\n" +
                "Damage : " + this.damage + "\n" +
                "Price : " + this.price +"$" + "\n" +
                "Representation : " + "\n" +
                this.ascii_art();
    }

    public abstract String ascii_art();
}
