package command;

import gems.Necklace;
import gems.Stone;

import java.util.Scanner;

public class FindStonesByTransparencyCommand implements Command {
    private Necklace necklace;
    private Scanner scanner;

    public FindStonesByTransparencyCommand(Necklace necklace, Scanner scanner) {
        this.necklace = necklace;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        double minTransparency = getValidTransparency("Enter minimum transparency (0-100): ");
        double maxTransparency = getValidTransparency("Enter maximum transparency (0-100): ");

        if (maxTransparency < minTransparency) {
            System.out.println("Maximum transparency cannot be less than minimum transparency.");
            return;
        }

        System.out.println("Stones matching the transparency range:");
        for (Stone stone : necklace.getNecklaceStones()) {
            if (stone.getTransparency() >= minTransparency && stone.getTransparency() <= maxTransparency) {
                System.out.println(stone);
            }
        }
    }

    private double getValidTransparency(String prompt) {
        double transparency = -1;
        while (transparency < 0 || transparency > 100) {
            System.out.print(prompt);
            while (!scanner.hasNextDouble()) {
                System.out.println("Error: please enter a numeric value.");
                scanner.next();
            }
            transparency = scanner.nextDouble();
            if (transparency < 0 || transparency > 100) {
                System.out.println("Transparency must be in the range of 0-100.");
            }
        }
        return transparency;
    }

    @Override
    public String printInfo() {
        return "Find stones by transparency";
    }
}
