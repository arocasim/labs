package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import gems.Necklace;
import gems.Stone;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RemoveStoneCommand implements Command {
    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");

    private Necklace necklace;
    private Scanner scanner;

    public RemoveStoneCommand(Necklace necklace, Scanner scanner) {
        this.necklace = necklace;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("--- Remove stone from the list ---");
        necklace.showAvailableStones();

        if (necklace.getAvailableStones().isEmpty()) {
            System.out.println("There are no stones to remove.");
            fileLogger.info("No stones available to remove.");
            return;
        }

        int index = -1;
        boolean isValid = false;

        while (!isValid) {
            System.out.print("Choose the stone number to remove: ");
            try {
                index = scanner.nextInt() - 1;
                if (index >= 0 && index < necklace.getAvailableStones().size()) {
                    isValid = true;
                } else {
                    System.out.println("Invalid index. Please try again.");
                }
            } catch (InputMismatchException e) {
                errorLogger.error(ERROR_MARKER, "Error: Invalid numeric input. " + e.getMessage(), e);
                scanner.next();
            }
        }

        scanner.nextLine();
        Stone removedStone = necklace.getAvailableStones().remove(index);
        fileLogger.info("Removed stone: {}", removedStone.getName());
        System.out.println(removedStone.getName() + " has been removed from the available stones list.");
    }

    @Override
    public String printInfo() {
        return "Remove stone from the list";
    }
}
