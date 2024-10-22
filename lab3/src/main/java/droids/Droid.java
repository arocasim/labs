package droids;

public class Droid {
    protected String name;
    protected int health;
    protected int damage;
    protected int energy;

    public Droid(String name, int health, int damage, int energy) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.energy = energy;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getEnergy() {
        return energy;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isAlive() {
        return health > 0;
    }
    public void decreaseEnergy() {
        if (this.energy > 0) {
            this.energy--;
            System.out.println(this.name + " використовує енергію. Залишок енергії: " + this.energy);
        } else {
            System.out.println(this.name + " не має енергії для бою.");
        }
    }

    public void attack(Droid target) {
        if (this.energy <= 0) {
            System.out.println(this.name + " має недостатньо енергії для атаки.");
            return;
        }
        System.out.println(this.name + " атакує " + target.getName() + " на " + damage + " шкоди.");
        target.takeDamage(damage);
        this.energy--;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println(this.name + " отримав " + damage + " шкоди. Здоров'я тепер: " + this.health);
    }

    public void heal(int amount) {
        this.health += amount;
        System.out.println(this.name + " відновив " + amount + " здоров'я. Поточне здоров'я: " + this.health);
    }

    @Override
    public String toString() {
        return String.format("%s [Здоров'я: %d, Шкода: %d, Енергія: %d]", name, health, damage, energy);
    }
}
