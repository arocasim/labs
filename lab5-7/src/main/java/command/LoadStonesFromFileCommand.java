package command;

import gems.Necklace;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class LoadStonesFromFileCommand implements Command {
    private Necklace necklace;
    private Scanner scanner;

    public LoadStonesFromFileCommand(Necklace necklace, Scanner scanner) {
        this.necklace = necklace;
        this.scanner = scanner;
    }

    @Override
    public void execute() {

        System.out.print("Enter the filename to load: ");
        String filename = scanner.nextLine();

        if (filename.trim().isEmpty()) {
            System.out.println("Error: filename cannot be empty.");
            return;
        }

        File file = new File(filename);

        if (!file.exists()) {
            throw new RuntimeException("File not found: " + filename);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Necklace loadedNecklace = (Necklace) ois.readObject();
            System.out.println("Data loaded from file " + filename);
            addStones(loadedNecklace);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    public void addStones(Necklace newStones) {
        necklace.getNecklaceStones().addAll(newStones.getNecklaceStones());
        necklace.getAvailableStones().addAll(newStones.getAvailableStones());
        System.out.println("Stones added from the file.");
    }

    @Override
    public String printInfo() {
        return "Load stones from file";
    }
}
