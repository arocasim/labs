package droids;

public class HealerDroid extends Droid {
    private int healingPower;

    public HealerDroid(String name, int health, int damage, int energy, int healingPower) {
        super(name, health, damage, energy);
        this.healingPower = healingPower;
    }

    public int getHealingPower() {
        return healingPower;
    }

    public void healSelf() {
        if (this.energy <= 0) {
            System.out.println(this.name + " має недостатньо енергії для лікування.");
            return;
        }

        if (this.health >= 25) {
            System.out.println(this.name + " має достатньо здоров'я і не потребує лікування.");
            return;
        }

        System.out.println(this.name + " лікує себе на " + healingPower + " здоров'я.");
        this.heal(healingPower);
        decreaseEnergy();
    }

    @Override
    public void attack(Droid target) {
        if (this.energy <= 0) {
            System.out.println(this.name + " має недостатньо енергії для атаки.");
            return;
        }

        System.out.println(this.name + " атакує " + target.getName() + " на " + this.damage + " шкоди.");
        target.takeDamage(this.damage);
        decreaseEnergy();
    }

    @Override
    public String toString() {
        return String.format("%s [Цілитель, Здоров'я: %d, Шкода: %d, Енергія: %d, Сила лікування: %d]",
                name, health, damage, energy, healingPower);
    }
}
