package battle;

public class Round {
    private int roundNumber;
    private String actions;

    public Round(int roundNumber, String actions) {
        this.roundNumber = roundNumber;
        this.actions = actions;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public String getActions() {
        return actions;
    }
}
