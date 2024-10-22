package droids;

public class InvisibleDroid extends Droid {
    protected int invisibilityDuration;

    public InvisibleDroid(String name, int health, int damage, int energy, int invisibilityDuration) {
        super(name, health, damage, energy);
        this.invisibilityDuration = invisibilityDuration;
    }

    public int getInvisibilityDuration() {
        return invisibilityDuration;
    }

    public boolean isInvisible() {
        return invisibilityDuration > 0 && this.health > 50;
    }

    @Override
    public void attack(Droid target) {
        if (this.energy <= 0) {
            System.out.println(this.name + " має недостатньо енергії для атаки.");
            return;
        }

        if (isInvisible()) {
            System.out.println(this.name + " невидимий та атакує " + target.getName() + " на " + (damage * 2) + " шкоди (критичний удар).");
            target.takeDamage(damage * 2);
            invisibilityDuration--;
        } else {
            System.out.println(this.name + " атакує " + target.getName() + " на " + damage + " шкоди.");
            target.takeDamage(damage);
        }

        decreaseEnergy();
    }

    @Override
    public void takeDamage(int damage) {
        if (isInvisible()) {
            System.out.println(this.name + " є невидимим і не може бути атакований.");
        } else {
            super.takeDamage(damage);
        }
    }

    @Override
    public String toString() {
        return String.format("%s [Невидимка, Здоров'я: %d, Шкода: %d, Енергія: %d, Тривалість невидимості: %d]",
                name, health, damage, energy, invisibilityDuration);
    }
}
