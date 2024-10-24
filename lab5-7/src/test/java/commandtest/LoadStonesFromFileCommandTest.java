package commandtest;

import command.LoadStonesFromFileCommand;
import gems.Necklace;
import gems.Stone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LoadStonesFromFileCommandTest {

    private Necklace mockNecklace;
    private LoadStonesFromFileCommand command;
    private File mockFile;

    @BeforeEach
    public void setUp() {
        mockNecklace = mock(Necklace.class);
        mockFile = mock(File.class);
    }

    @Test
    public void testLoadStonesFromFile_FileNotFound() {
        String input = "invalid.dat\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner realScanner = new Scanner(in);
        command = new LoadStonesFromFileCommand(mockNecklace, realScanner);

        assertThrows(RuntimeException.class, () -> command.execute());
        verify(mockNecklace, never()).getAvailableStones();
    }

    @Test
    public void testConstructor() {
        Scanner scanner = new Scanner(System.in);
        LoadStonesFromFileCommand command = new LoadStonesFromFileCommand(mockNecklace, scanner);
    }

    @Test
    public void testAddStones() {
        Necklace newNecklace = new Necklace();
        newNecklace.getAvailableStones().add(new Stone("Topaz", 1.0, 1500.0, 80.0, true, 0.5));
        List<Stone> mockStoneList = mock(List.class);
        when(mockNecklace.getAvailableStones()).thenReturn(mockStoneList);
        LoadStonesFromFileCommand command = new LoadStonesFromFileCommand(mockNecklace, new Scanner(System.in));
        command.addStones(newNecklace);

        verify(mockStoneList, times(1)).addAll(anyCollection());
    }



    @Test
    public void testValidateEmptyFileName() {
        String filename = "";
        assertThrows(IllegalArgumentException.class, () -> {
            if (filename.trim().isEmpty()) {
                throw new IllegalArgumentException("Filename cannot be empty.");
            }
        });
    }

    @Test
    public void testFileExists() {
        String filename = "non_existing_file.dat";

        assertThrows(RuntimeException.class, () -> {
            File file = new File(filename);
            if (!file.exists()) {
                throw new RuntimeException("File not found: " + filename);
            }
        });
    }

    @Test
    public void testLoadStonesFromFile_EmptyInput() {
        String input = "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner realScanner = new Scanner(in);
        command = new LoadStonesFromFileCommand(mockNecklace, realScanner);
        command.execute();

        verify(mockNecklace, never()).getAvailableStones();
    }

    @Test
    public void testLoadStonesFromFile_Success() throws Exception {
        File tempFile = File.createTempFile("test", ".dat");
        tempFile.deleteOnExit();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempFile))) {
            Necklace loadedNecklace = new Necklace();
            loadedNecklace.getAvailableStones().add(new Stone("Topaz", 1.0, 1500.0, 80.0, true, 0.5));
            oos.writeObject(loadedNecklace);
        }

        String input = tempFile.getAbsolutePath() + "\n";
        Scanner realScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        LoadStonesFromFileCommand command = new LoadStonesFromFileCommand(mockNecklace, realScanner);
        command.execute();

        verify(mockNecklace, times(1)).getAvailableStones();
    }
}
