package commandtest;

import command.FindStonesByTransparencyCommand;
import gems.Necklace;
import gems.Stone;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FindStonesByTransparencyCommandTest {

    private Necklace necklace;
    private FindStonesByTransparencyCommand command;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        necklace = mock(Necklace.class);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testValidTransparencyRange() {
        List<Stone> stones = new ArrayList<>();
        stones.add(new Stone("Emerald", 1.0, 2000.0, 85.0, false, 0.7));
        stones.add(new Stone("Ruby", 2.0, 5000.0, 60.0, true, 0.8));
        when(necklace.getNecklaceStones()).thenReturn(stones);

        String input = "50\n90\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        command = new FindStonesByTransparencyCommand(necklace, scanner);
        command.execute();

        assertTrue(outContent.toString().contains("Emerald"));
        assertTrue(outContent.toString().contains("Ruby"));
        verify(necklace, times(1)).getNecklaceStones();
    }

    @Test
    public void testNoMatchingStones() {
        List<Stone> stones = new ArrayList<>();
        stones.add(new Stone("Emerald", 1.0, 2000.0, 85.0, false, 0.7));
        when(necklace.getNecklaceStones()).thenReturn(stones);

        String input = "90\n100\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        command = new FindStonesByTransparencyCommand(necklace, scanner);
        command.execute();

        assertFalse(outContent.toString().contains("Emerald"));
        assertFalse(outContent.toString().contains("No stones found matching the transparency range"));
        verify(necklace, times(1)).getNecklaceStones();
    }

    @Test
    public void testInvalidTransparencyRange() {
        String input = "90\n50\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        command = new FindStonesByTransparencyCommand(necklace, scanner);

        command.execute();
        assertTrue(outContent.toString().contains("Maximum transparency cannot be less than minimum transparency."));
        verify(necklace, never()).getNecklaceStones();
    }

    @Test
    public void testNonNumericInput() {
        String input = "abc\n10\n90\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        command = new FindStonesByTransparencyCommand(necklace, scanner);
        command.execute();
        assertTrue(outContent.toString().contains("Error: please enter a numeric value."));
    }

    @Test
    public void testTransparencyOutOfBounds() {
        String input = "-10\n120\n50\n90\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        command = new FindStonesByTransparencyCommand(necklace, scanner);
        command.execute();
        assertTrue(outContent.toString().contains("Transparency must be in the range of 0-100."));

        verify(necklace, times(1)).getNecklaceStones();
    }


    @Test
    public void testExecuteWithBoundaryTransparency() {
        List<Stone> stones = new ArrayList<>();
        stones.add(new Stone("Diamond", 1.0, 2000.0, 0.0, true, 0.5));
        stones.add(new Stone("Sapphire", 1.5, 1500.0, 100.0, true, 0.6));
        when(necklace.getNecklaceStones()).thenReturn(stones);
        String input = "0\n100\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        command = new FindStonesByTransparencyCommand(necklace, scanner);
        command.execute();

        assertTrue(outContent.toString().contains("Diamond"));
        assertTrue(outContent.toString().contains("Sapphire"));
        verify(necklace, times(1)).getNecklaceStones();
    }
}
