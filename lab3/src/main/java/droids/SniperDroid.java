package droids;

public class SniperDroid extends Droid {
    private double accuracy;

    public SniperDroid(String name, int health, int damage, int energy, double accuracy) {
        super(name, health, damage, energy);
        this.accuracy = accuracy;
    }

    public double getAccuracy() {
        return accuracy;
    }

    @Override
    public void attack(Droid target) {
        if (this.energy <= 0) {
            System.out.println(this.name + " має недостатньо енергії для атаки.");
            return;
        }

        double hitChance = Math.random();
        System.out.println(this.name + " намагається атакувати з точністю " + accuracy);

        if (hitChance < accuracy) {
            System.out.println(this.name + " вистрілює в " + target.getName() + " на " + damage + " шкоди.");
            target.takeDamage(damage);
        } else {
            System.out.println(this.name + " промахнувся!");
        }
        decreaseEnergy();
    }

    @Override
    public String toString() {
        return String.format("%s [Снайпер Здоров'я: %d, Шкода: %d, Енергія: %d, Точність: %.2f]",
                name, health, damage, energy, accuracy);
    }
}
