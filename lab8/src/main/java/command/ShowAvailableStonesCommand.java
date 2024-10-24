package command;

import gems.Necklace;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowAvailableStonesCommand implements Command {

    private static final Logger logger = LogManager.getLogger("FileOnlyLogger");
    private Necklace necklace;

    public ShowAvailableStonesCommand(Necklace necklace) {
        this.necklace = necklace;
    }

    @Override
    public void execute() {
        if (necklace.getAvailableStones().isEmpty()) {
            System.out.println("No available stones.");
            logger.info("No available stones to display.");
        } else {
            necklace.showAvailableStones();
            logger.info("Displayed available stones.");
        }
    }

    @Override
    public String printInfo() {
        return "Show all available stones";
    }
}
