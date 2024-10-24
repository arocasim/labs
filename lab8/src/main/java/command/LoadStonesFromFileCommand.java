package command;

import gems.Necklace;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class LoadStonesFromFileCommand implements Command {
    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");

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
            errorLogger.error(ERROR_MARKER, "Filename cannot be empty.");
            return;
        }

        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("File not found: " + filename);
            errorLogger.error(ERROR_MARKER, "File not found: " + filename);
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Necklace loadedNecklace = (Necklace) ois.readObject();
            addStones(loadedNecklace);
            System.out.println("Data loaded from file " + filename);
            fileLogger.info("Data loaded from file: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading file: " + e.getMessage());
            errorLogger.error(ERROR_MARKER, "Error loading file: " + e.getMessage(), e);
        }
    }

    private void addStones(Necklace newStones) {
        necklace.getNecklaceStones().addAll(newStones.getNecklaceStones());
        necklace.getAvailableStones().addAll(newStones.getAvailableStones());
        System.out.println("Stones added from the file.");
        fileLogger.info("Stones added from file to necklace and available stones.");
    }

    @Override
    public String printInfo() {
        return "Load stones from file";
    }
}
