package command;

import gems.Necklace;

public class ShowAvailableStonesCommand implements Command {
    private Necklace necklace;

    public ShowAvailableStonesCommand(Necklace necklace) {
        this.necklace = necklace;
    }

    @Override
    public void execute() {
        if (necklace.getAvailableStones().isEmpty()) {
            System.out.println("No available stones.");
        } else {
            necklace.showAvailableStones();
        }
    }

    @Override
    public String printInfo() {
        return "Show all available stones";
    }
}
