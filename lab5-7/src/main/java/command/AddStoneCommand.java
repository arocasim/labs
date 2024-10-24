package command;

import gems.Necklace;
import gems.Stone;

import java.util.Scanner;

public class AddStoneCommand implements Command {
    private Necklace necklace;
    private Scanner scanner;

    public AddStoneCommand(Necklace necklace, Scanner scanner) {
        this.necklace = necklace;
        this.scanner = scanner;
    }

    @Override
    public void execute() {

        System.out.print("\nEnter the stone name: ");
        String name = scanner.nextLine();

        double weight = promptDouble("Enter weight (carats): ", 0, Double.MAX_VALUE);
        double value = promptDouble("Enter price (in dollars): ", 0, Double.MAX_VALUE);
        double transparency = promptDouble("Enter transparency (0 - 100): ", 0, 100);
        double rarity = promptDouble("Enter stone rarity (from 0 to 1): ", 0, 1);
        boolean isPrecious = promptIsPrecious();

        Stone stone = new Stone(name, weight, value, transparency, isPrecious, rarity);
        necklace.getAvailableStones().add(stone);
        System.out.println("Stone " + name + " has been added to the list of available stones.");
    }


    public double promptDouble(String message, double minValue, double maxValue) {
        double input = -1;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(message);
            try {
                input = Double.parseDouble(scanner.nextLine());
                if (input >= minValue && input <= maxValue) {
                    isValid = true;
                } else {
                    System.out.printf("Value must be in the range from %.2f to %.2f.%n", minValue, maxValue);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: please enter a valid numeric value.");
            }
        }
        return input;
    }

    public boolean promptIsPrecious() {
        while (true) {
            System.out.println("1. Precious stone");
            System.out.println("2. Semi-precious stone");
            System.out.print("Your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1 || choice == 2) {
                    return choice == 1;
                } else {
                    System.out.println("Please enter number 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter number 1 or 2.");
            }
        }
    }

    @Override
    public String printInfo() {
        return "Add stone";
    }
}
