package droids;

public class AttackDroid extends Droid {

    public AttackDroid(String name, int health, int damage, int energy) {
        super(name, health, damage, energy);
    }

    @Override
    public void attack(Droid target) {
        if (this.energy <= 0) {
            System.out.println(this.name + " має недостатньо енергії для атаки.");
            return;
        }
        System.out.println(this.name + " атакує " + target.getName() + " на " + damage + " шкоди.");
        target.takeDamage(damage);
        decreaseEnergy();
    }

    @Override
    public String toString() {
        return String.format("%s [Звичайний, Здоров'я: %d, Шкода: %d, Енергія: %d]",
                name, health, damage, energy);
    }
}
