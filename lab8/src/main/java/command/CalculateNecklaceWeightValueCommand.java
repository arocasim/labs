package command;

import gems.Necklace;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalculateNecklaceWeightValueCommand implements Command {

    private static final Logger logger = LogManager.getLogger("FileOnlyLogger");
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
        logger.info("Calculated total weight: " + totalWeight + " carats, total value: " + totalValue + " dollars.");
    }

    @Override
    public String printInfo() {
        return "Calculate total weight and value";
    }
}
