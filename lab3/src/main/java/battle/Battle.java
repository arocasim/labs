package battle;

import droids.Droid;
import java.util.ArrayList;
import java.util.List;

public class Battle {
    private List<Droid> team1;
    private List<Droid> team2;
    private String result;
    private List<Round> rounds;
    private StringBuilder battleLog = new StringBuilder();

    public Battle(List<Droid> team1, List<Droid> team2, String result) {
        this.team1 = team1;
        this.team2 = team2;
        this.result = result;
        this.rounds = new ArrayList<>();
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void logAction(String action) {
        battleLog.append(action).append("\n");
    }

    public String getBattleLog() {
        return battleLog.toString();
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void addRound(Round round) {
        this.rounds.add(round);
        logAction("Раунд " + round.getRoundNumber() + ":\n" + round.getActions());
    }

    public void showBattleDetails() {
        System.out.println("\n--- Деталі бою ---");
        System.out.println(getBattleLog());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Команда 1:\n");
        for (Droid d : team1) {
            sb.append(d.toString()).append("\n");
        }
        sb.append("Команда 2:\n");
        for (Droid d : team2) {
            sb.append(d.toString()).append("\n");
        }
        sb.append("Результат: ").append(result).append("\n");
        sb.append("\n--- Деталі бою ---\n");
        sb.append(getBattleLog());
        return sb.toString();
    }
}
