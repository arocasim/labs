package droids;

import java.util.Random;

public class FlyingDroid extends Droid {
    private int flightPeriod;
    private Random random;

    public FlyingDroid(String name, int health, int damage, int energy, int flightPeriod) {
        super(name, health, damage, energy);
        this.flightPeriod = flightPeriod;
        this.random = new Random();
    }

    public int getFlightPeriod() {
        return flightPeriod;
    }

    @Override
    public void attack(Droid target) {
        if (this.energy <= 0) {
            System.out.println(this.name + " має недостатньо енергії для атаки.");
            return;
        }

        boolean isFlying = random.nextFloat() < 0.3;

        if (isFlying && flightPeriod > 0) {
            System.out.println(this.name + " літає та атакує " + target.getName() + " на " + this.damage + " шкоди.");
            target.takeDamage(this.damage);
            flightPeriod--;
            this.decreaseEnergy();
        } else {
            System.out.println(this.name + " приземлюється та атакує " + target.getName() + " на " + this.damage + " шкоди.");
            target.takeDamage(this.damage);
            this.decreaseEnergy();
        }
    }

    @Override
    public String toString() {
        return String.format("%s [Літун, Здоров'я: %d, Шкода: %d, Енергія: %d, Період польоту: %d]",
                name, health, damage, energy, flightPeriod);
    }
}
