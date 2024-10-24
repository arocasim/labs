package menu;

import command.*;
import gems.Necklace;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");

    private Necklace necklace;
    private Scanner scanner;
    private Map<Integer, Command> commands;

    public Menu(Scanner scanner) {
        this.necklace = new Necklace();
        this.scanner = scanner;
        this.commands = new HashMap<>();
        initializeCommands();
    }

    public void showMenu() {
        while (true) {
            printMenu();
            int choice = getUserChoice();

            if (choice == 99) {
                System.out.println("Exiting...");
                fileLogger.info("User exited the application.");
                break;
            }

            Command command = commands.get(choice);
            if (command != null) {
                try {
                    command.execute();
                    fileLogger.info("Executed command: {}", command.printInfo());
                } catch (IOException e) {
                    System.out.println("Error executing command: " + e.getMessage());
                    errorLogger.error(ERROR_MARKER, "Error executing command: " + e.getMessage(), e);
                }
            } else {
                System.out.println("Invalid choice.");
                fileLogger.warn("User selected an invalid option.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- Menu ---");
        for (Map.Entry<Integer, Command> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().printInfo());
        }
        System.out.println("99. Exit");
    }

    private void initializeCommands() {
        commands.put(1, new AddStoneCommand(necklace, scanner));
        commands.put(2, new ShowAvailableStonesCommand(necklace));
        commands.put(3, new RemoveStoneCommand(necklace, scanner));
        commands.put(4, new AddStoneToNecklaceCommand(necklace, scanner));
        commands.put(5, new ShowNecklaceStonesCommand(necklace));
        commands.put(6, new CalculateNecklaceWeightValueCommand(necklace));
        commands.put(7, new SortStonesByValueCommand(necklace));
        commands.put(8, new FindStonesByTransparencyCommand(necklace, scanner));
        commands.put(9, new SaveStonesToFileCommand(necklace, scanner));
        commands.put(10, new LoadStonesFromFileCommand(necklace, scanner));
    }

    private int getUserChoice() {
        while (true) {
            System.out.print("\nChoose an option (0-11): ");
            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 0 && choice <= 10 || choice==99) {
                    return choice;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 0 and 12.");
                }
            } catch (NumberFormatException e) {
                errorLogger.error(ERROR_MARKER, "Enter a valid number: " + e.getMessage(), e);
            }
        }
    }
}
