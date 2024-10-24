package commandtest;

import command.SaveStonesToFileCommand;
import gems.Necklace;
import gems.Stone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class SaveStonesToFileCommandTest {

    private Necklace mockNecklace;
    private SaveStonesToFileCommand command;

    @BeforeEach
    public void setUp() {
        mockNecklace = mock(Necklace.class);
        when(mockNecklace.getAvailableStones()).thenReturn(java.util.List.of(new Stone("Emerald", 1.0, 2000.0, 85.0, true, 0.5)));
    }

    @Test
    public void testSaveToFile() throws IOException {
        String input = "testfile.ser\n";
        Scanner realScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        Necklace realNecklace = new Necklace();
        Stone stone = new Stone("Emerald", 1.0, 2000.0, 85.0, true, 0.5);
        realNecklace.getAvailableStones().add(stone);

        SaveStonesToFileCommand command = new SaveStonesToFileCommand(realNecklace, realScanner);

        command.execute();

        File file = new File("testfile.ser");
        assertTrue(file.exists());

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Necklace loadedNecklace = (Necklace) ois.readObject();
            assertEquals(1, loadedNecklace.getAvailableStones().size());
            assertEquals(stone.getName(), loadedNecklace.getAvailableStones().get(0).getName());
        } catch (ClassNotFoundException e) {
            fail("ClassNotFoundException thrown while reading the object from file");
        }

        file.delete();
    }

    @Test
    public void testConstructor() {
        Scanner scanner = new Scanner(System.in);
        SaveStonesToFileCommand command = new SaveStonesToFileCommand(mockNecklace, scanner);

        assertNotNull(command);
        assertEquals(mockNecklace, command.necklace);
        assertEquals(scanner, command.scanner);
    }

    @Test
    public void testInvalidInput() throws IOException {
        Scanner realScanner = new Scanner(new ByteArrayInputStream("\ninvalidpath?\n".getBytes()));
        SaveStonesToFileCommand command = new SaveStonesToFileCommand(mockNecklace, realScanner);

        assertThrows(IOException.class, command::execute);
    }
}
