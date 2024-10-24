package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import gems.Necklace;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class SaveStonesToFileCommand implements Command {
    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");

    private Necklace necklace;
    private Scanner scanner;

    public SaveStonesToFileCommand(Necklace necklace, Scanner scanner) {
        this.necklace = necklace;
        this.scanner = scanner;
    }

    @Override
    public void execute() throws IOException {
        System.out.print("Enter filename to save: ");
        String filename = scanner.nextLine();

        if (filename.trim().isEmpty()) {
            System.out.println("Error: filename cannot be empty.");
            errorLogger.error(ERROR_MARKER, "Error: filename cannot be empty.");
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(necklace);
            fileLogger.info("Data saved to file: {}", filename);
            System.out.println("Data saved to file " + filename);
        } catch (IOException e) {
            errorLogger.error(ERROR_MARKER, "Error saving file. " + e.getMessage(), e);
            throw new IOException("Error saving file: " + e.getMessage(), e);
        }
    }

    @Override
    public String printInfo() {
        return "Save stones to file";
    }
}
