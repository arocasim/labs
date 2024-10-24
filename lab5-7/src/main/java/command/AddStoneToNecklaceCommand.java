package command;

import gems.Necklace;
import gems.Stone;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddStoneToNecklaceCommand implements Command {
    private Necklace necklace;
    private Scanner scanner;

    public AddStoneToNecklaceCommand(Necklace necklace, Scanner scanner) {
        this.necklace = necklace;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("--- Select a stone to add to the necklace ---");
        necklace.showAvailableStones();

        if (necklace.getAvailableStones().isEmpty()) {
            System.out.println("No available stones to add.");
            return;
        }

        int index = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Select the stone number: ");
                index = scanner.nextInt() - 1;
                if (index >= 0 && index < necklace.getAvailableStones().size()) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a valid number: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number: ");
                scanner.next();
            }
        }

        scanner.nextLine();
        Stone stone = necklace.getAvailableStones().get(index);

        if (stone != null) {
            necklace.getNecklaceStones().add(stone);
            System.out.println(stone.getName() + " has been added to the necklace.");
        }
    }

    @Override
    public String printInfo() {
        return "Add a stone to the necklace";
    }
}
