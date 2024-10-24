package command;

import gems.Necklace;

public class CalculateNecklaceWeightValueCommand implements Command {
    private Necklace necklace;

    public CalculateNecklaceWeightValueCommand(Necklace necklace) {
        this.necklace = necklace;
    }

    @Override
    public void execute() {
        double totalWeight = necklace.getTotalWeight();
        double totalValue = necklace.getTotalValue();
        System.out.println("Total weight of the necklace: " + totalWeight + " carats.");
        System.out.println("Total value of the necklace: " + totalValue + " dollars.");
    }

    @Override
    public String printInfo() {
        return "Calculate total weight and value";
    }
}
