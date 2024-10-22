package menu;

import droids.*;
import battle.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<Droid> droids = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private List<Battle> battles = new ArrayList<>();
    private BattleFileHandler battleFileHandler = new BattleFileHandler();

    public void showMenu() {
        while (true) {
            System.out.println("\n--- Бойове меню ---");
            System.out.println("1. Створити дроїда");
            System.out.println("2. Показати список дроїдів");
            System.out.println("3. Запустити бій 1 на 1");
            System.out.println("4. Запустити командний бій");
            System.out.println("5. Записати проведений бій у файл");
            System.out.println("6. Відтворити проведений бій зі збереженого файлу");
            System.out.println("7. Вийти");

            int choice = getIntInput("\nВиберіть опцію: ");

            switch (choice) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showDroids();
                    break;
                case 3:
                    startOneOnOneBattle();
                    break;
                case 4:
                    startTeamBattle();
                    break;
                case 5:
                    saveBattleToFile();
                    break;
                case 6:
                    loadBattleFromFile();
                    break;
                case 7:
                    System.out.println("Вихід...");
                    return;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private void createDroid() {
        System.out.println("\nВиберіть тип дроїда:");
        System.out.println("1. Звичайний");
        System.out.println("2. Снайпер");
        System.out.println("3. Невидимка");
        System.out.println("4. Літун");
        System.out.println("5. Цілитель");

        int type = getIntInput("Виберіть тип: ");

        System.out.print("Введіть ім'я дроїда: ");
        String name = scanner.nextLine();

        int health = getIntInput("Введіть рівень здоров'я дроїда: ");
        int damage = getIntInput("Введіть шкоду дроїда: ");
        int energy = getIntInput("Введіть рівень енергії дроїда: ");

        Droid droid;
        switch (type) {
            case 1:
                droid = new AttackDroid(name, health, damage, energy);
                break;
            case 2:
                double accuracy = getDoubleInput("Введіть точність дроїда (значення від 0 до 1): ");
                droid = new SniperDroid(name, health, damage, energy, accuracy);
                break;
            case 3:
                int invisibilityDuration = getIntInput("Введіть кількість ходів для невидимості: ");
                droid = new InvisibleDroid(name, health, damage, energy, invisibilityDuration);
                break;
            case 4:
                int flightPeriod = getIntInput("Введіть кількість ходів польоту: ");
                droid = new FlyingDroid(name, health, damage, energy, flightPeriod);
                break;
            case 5:
                int healingPower = getIntInput("Введіть силу лікування дроїда: ");
                droid = new HealerDroid(name, health, damage, energy, healingPower);
                break;
            default:
                System.out.println("Невірний вибір. Дроїд не створений.");
                return;
        }
        droids.add(droid);
        System.out.println("\nДроїд створений: " + droid);
    }

    private void showDroids() {
        if (droids.isEmpty()) {
            System.out.println("Немає створених дроїдів.");
        } else {
            System.out.println("\n--- Список дроїдів ---");
            for (int i = 0; i < droids.size(); i++) {
                System.out.println((i + 1) + ". " + droids.get(i));
            }
        }
    }

    private void startOneOnOneBattle() {
        if (droids.size() < 2) {
            System.out.println("Недостатньо дроїдів для бою 1 на 1.");
            return;
        }

        System.out.println("Виберіть першого дроїда:");
        Droid droid1 = selectDroid();
        System.out.println("Виберіть другого дроїда:");
        Droid droid2 = selectDroid();

        if (droid1 == null || droid2 == null || droid1 == droid2) {
            System.out.println("Недійсний вибір дроїдів.");
            return;
        }

        System.out.println("\nБій між " + droid1.getName() + " та " + droid2.getName() + " почався!");

        Droid clone1 = cloneDroid(droid1);
        Droid clone2 = cloneDroid(droid2);

        Battle battle = new Battle(List.of(droid1), List.of(droid2), "");
        int roundNumber = 1;

        while (clone1.isAlive() && clone2.isAlive()) {
            StringBuilder actions = new StringBuilder();

            clone1.attack(clone2);
            actions.append(clone1.getName()).append(" атакує ").append(clone2.getName())
                    .append(" на ").append(clone1.getDamage()).append(" шкоди.\n")
                    .append(clone2.getName()).append(" здоров'я: ").append(clone2.getHealth()).append("\n");

            if (clone2.isAlive()) {
                clone2.attack(clone1);
                actions.append(clone2.getName()).append(" атакує ").append(clone1.getName())
                        .append(" на ").append(clone2.getDamage()).append(" шкоди.\n")
                        .append(clone1.getName()).append(" здоров'я: ").append(clone1.getHealth()).append("\n");
            }

            Round round = new Round(roundNumber, actions.toString());
            battle.addRound(round);
            roundNumber++;
        }

        String result;
        if (clone1.isAlive()) {
            result = clone1.getName() + " переміг!";
            System.out.println(result);
        } else {
            result = clone2.getName() + " переміг!";
            System.out.println(result);
        }
        battle.setResult(result);

        battle.showBattleDetails();
        battles.add(battle);
        System.out.println("Бій збережено у списку проведених боїв.");
    }

    private void startTeamBattle() {
        if (droids.size() < 4) {
            System.out.println("Недостатньо дроїдів для командного бою.");
            return;
        }

        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();

        System.out.println("\nВиберіть дроїдів для команди 1:");
        for (int i = 0; i < 2; i++) {
            Droid droid = selectDroid();
            if (droid != null && !team1.contains(droid)) {
                team1.add(droid);
            } else {
                System.out.println("Недійсний вибір.");
                i--;
            }
        }

        System.out.println("\nВиберіть дроїдів для команди 2:");
        for (int i = 0; i < 2; i++) {
            Droid droid = selectDroid();
            if (droid != null && !team2.contains(droid) && !team1.contains(droid)) {
                team2.add(droid);
            } else {
                System.out.println("Недійсний вибір.");
                i--;
            }
        }

        System.out.println("\nБій між командами почався!");

        List<Droid> cloneTeam1 = new ArrayList<>();
        for (Droid d : team1) {
            cloneTeam1.add(cloneDroid(d));
        }

        List<Droid> cloneTeam2 = new ArrayList<>();
        for (Droid d : team2) {
            cloneTeam2.add(cloneDroid(d));
        }

        Battle battle = new Battle(team1, team2, "");
        int roundNumber = 1;

        while (teamIsAlive(cloneTeam1) && teamIsAlive(cloneTeam2)) {
            StringBuilder actions = new StringBuilder();

            Droid attacker1 = getAliveDroid(cloneTeam1);
            Droid defender1 = getAliveDroid(cloneTeam2);
            Droid attacker2 = getAliveDroid(cloneTeam2);
            Droid defender2 = getAliveDroid(cloneTeam1);

            if (attacker1 != null && defender1 != null) {
                if (attacker1.getHealth() < 25 && attacker1 instanceof HealerDroid) {
                    ((HealerDroid) attacker1).healSelf();
                    actions.append(attacker1.getName()).append(" лікує себе на ").append(((HealerDroid) attacker1).getHealingPower()).append(" здоров'я.\n");
                } else {
                    attacker1.attack(defender1);
                    actions.append(attacker1.getName()).append(" атакує ").append(defender1.getName())
                            .append(" на ").append(attacker1.getDamage()).append(" шкоди.\n")
                            .append(defender1.getName()).append(" здоров'я: ").append(defender1.getHealth()).append("\n");
                }
            }

            if (attacker2 != null && defender2 != null && defender1.isAlive()) {
                if (attacker2.getHealth() < 25 && attacker2 instanceof HealerDroid) {
                    ((HealerDroid) attacker2).healSelf();
                    actions.append(attacker2.getName()).append(" лікує себе на ").append(((HealerDroid) attacker2).getHealingPower()).append(" здоров'я.\n");
                } else {
                    attacker2.attack(defender2);
                    actions.append(attacker2.getName()).append(" атакує ").append(defender2.getName())
                            .append(" на ").append(attacker2.getDamage()).append(" шкоди.\n")
                            .append(defender2.getName()).append(" здоров'я: ").append(defender2.getHealth()).append("\n");
                }
            }

            Round round = new Round(roundNumber, actions.toString());
            battle.addRound(round);
            roundNumber++;
        }

        String result;
        if (teamIsAlive(cloneTeam1)) {
            result = "Перемогла команда 1!";
            System.out.println(result);
        } else {
            result = "Перемогла команда 2!";
            System.out.println(result);
        }
        battle.setResult(result);

        battle.showBattleDetails();
        battles.add(battle);
        System.out.println("Бій збережено у списку проведених боїв.");
    }

    private void saveBattleToFile() {
        if (battles.isEmpty()) {
            System.out.println("Немає боїв для збереження.");
            return;
        }

        System.out.println("Виберіть бій для збереження:");
        for (int i = 0; i < battles.size(); i++) {
            System.out.println((i + 1) + ". " + battles.get(i).getResult());
        }

        int index = getIntInput("Введіть номер бою: ") - 1;
        if (index < 0 || index >= battles.size()) {
            System.out.println("Недійсний номер бою.");
            return;
        }

        Battle battle = battles.get(index);
        System.out.print("Введіть ім'я файлу для збереження: ");
        String filename = scanner.nextLine();
        battleFileHandler.saveBattleToFile(battle, filename);
    }

    private void loadBattleFromFile() {
        System.out.print("Введіть ім'я файлу для завантаження: ");
        String filename = scanner.nextLine();

        Battle loadedBattle = battleFileHandler.loadBattleFromFile(filename);
        if (loadedBattle != null) {
            battles.add(loadedBattle);
        }
    }

    private Droid selectDroid() {
        if (droids.isEmpty()) {
            System.out.println("Немає дроїдів для вибору.");
            return null;
        }
        showDroids();
        int index = getIntInput("Введіть номер дроїда: ") - 1;
        if (index < 0 || index >= droids.size()) {
            System.out.println("Недійсний вибір.");
            return null;
        }
        return droids.get(index);
    }

    private boolean teamIsAlive(List<Droid> team) {
        for (Droid droid : team) {
            if (droid.isAlive()) {
                return true;
            }
        }
        return false;
    }

    private Droid getAliveDroid(List<Droid> team) {
        for (Droid droid : team) {
            if (droid.isAlive()) {
                return droid;
            }
        }
        return null;
    }

    private Droid cloneDroid(Droid droid) {
        if (droid instanceof AttackDroid) {
            return new AttackDroid(droid.getName(), droid.getHealth(), droid.getDamage(), droid.getEnergy());
        } else if (droid instanceof SniperDroid) {
            SniperDroid sd = (SniperDroid) droid;
            return new SniperDroid(sd.getName(), sd.getHealth(), sd.getDamage(), sd.getEnergy(), sd.getAccuracy());
        } else if (droid instanceof InvisibleDroid) {
            InvisibleDroid id = (InvisibleDroid) droid;
            return new InvisibleDroid(id.getName(), id.getHealth(), id.getDamage(), id.getEnergy(), id.getInvisibilityDuration());
        } else if (droid instanceof FlyingDroid) {
            FlyingDroid fd = (FlyingDroid) droid;
            return new FlyingDroid(fd.getName(), fd.getHealth(), fd.getDamage(), fd.getEnergy(), fd.getFlightPeriod());
        } else if (droid instanceof HealerDroid) {
            HealerDroid hd = (HealerDroid) droid;
            return new HealerDroid(hd.getName(), hd.getHealth(), hd.getDamage(), hd.getEnergy(), hd.getHealingPower());
        } else {
            return new Droid(droid.getName(), droid.getHealth(), droid.getDamage(), droid.getEnergy());
        }
    }

    private int getIntInput(String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                value = Integer.parseInt(input);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Введіть ціле число.");
            }
        }
    }

    private double getDoubleInput(String prompt) {
        double value;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                value = Double.parseDouble(input);
                if (value < 0 || value > 1) {
                    System.out.println("Введіть число від 0 до 1.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Введіть число.");
            }
        }
    }
}
