package command;

import gems.Necklace;

public class ShowNecklaceStonesCommand implements Command {
    private Necklace necklace;

    public ShowNecklaceStonesCommand(Necklace necklace) {
        this.necklace = necklace;
    }

    @Override
    public void execute() {
        if (necklace.getNecklaceStones().isEmpty()) {
            System.out.println("The necklace is empty.");
        } else {
            System.out.println("Stones in the necklace:");
            for (var stone : necklace.getNecklaceStones()) {
                System.out.println(stone);
            }
        }
    }

    @Override
    public String printInfo() {
        return "Show stones in the necklace";
    }
}
