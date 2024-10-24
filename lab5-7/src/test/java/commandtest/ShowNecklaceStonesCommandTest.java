package commandtest;

import command.ShowNecklaceStonesCommand;
import gems.Necklace;
import gems.Stone;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowNecklaceStonesCommandTest {

    private Necklace mockNecklace;
    private ShowNecklaceStonesCommand command;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    public void setUp() {
        mockNecklace = mock(Necklace.class);
        command = new ShowNecklaceStonesCommand(mockNecklace);
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testShowNecklaceStonesWhenEmpty() {
        when(mockNecklace.getNecklaceStones()).thenReturn(new ArrayList<>());

        command.execute();
        String expectedOutput = "The necklace is empty.";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
        verify(mockNecklace, times(1)).getNecklaceStones();
    }

    @Test
    public void testShowNecklaceStonesWithStones() {
        Stone stone1 = new Stone("Ruby", 1.0, 1000.0, 90.0, true, 0.5);
        Stone stone2 = new Stone("Sapphire", 2.0, 2000.0, 80.0, true, 0.6);
        when(mockNecklace.getNecklaceStones()).thenReturn(java.util.List.of(stone1, stone2));

        command.execute();

        String actualOutput = outputStreamCaptor.toString().trim().replaceAll("\\s+", " ");
        String expectedOutput = "Stones in the necklace: " +
                "Precious stone: Ruby [Weight: 1.00 carats, Price: 1000.00 USD, Transparency: 90.00%, Rarity: 0.50] " +
                "Precious stone: Sapphire [Weight: 2.00 carats, Price: 2000.00 USD, Transparency: 80.00%, Rarity: 0.60]";

        assertEquals(expectedOutput, actualOutput);
        verify(mockNecklace, times(2)).getNecklaceStones();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
}
