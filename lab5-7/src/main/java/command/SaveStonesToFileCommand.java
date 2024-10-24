package command;

import gems.Necklace;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class SaveStonesToFileCommand implements Command {
    public Necklace necklace;
    public Scanner scanner;

    public SaveStonesToFileCommand(Necklace necklace, Scanner scanner) {
        this.necklace = necklace;
        this.scanner = scanner;
    }

    @Override
    public void execute() throws IOException {

        System.out.print("Enter filename to save: ");
        String filename = scanner.nextLine();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(necklace);
            System.out.println("Data saved to file " + filename);
        } catch (IOException e) {
            throw new IOException("Error saving file: " + e.getMessage(), e);
        }
    }

    @Override
    public String printInfo() {
        return "Save stones to file";
    }
}
