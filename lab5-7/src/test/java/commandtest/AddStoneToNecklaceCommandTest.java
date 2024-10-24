package commandtest;

import command.AddStoneToNecklaceCommand;
import gems.Necklace;
import gems.Stone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.List;

import static org.mockito.Mockito.*;

public class AddStoneToNecklaceCommandTest {

    private Necklace necklace;
    private AddStoneToNecklaceCommand command;
    private Stone stone1;
    private List<Stone> mockAvailableStones;
    private List<Stone> mockNecklaceStones;

    @BeforeEach
    public void setUp() {
        necklace = mock(Necklace.class);
        mockAvailableStones = mock(List.class);
        mockNecklaceStones = mock(List.class);

        stone1 = new Stone("Emerald", 1.0, 2000.0, 85.0, false, 0.7);

        when(necklace.getAvailableStones()).thenReturn(mockAvailableStones);
        when(mockAvailableStones.size()).thenReturn(1);
        when(mockAvailableStones.get(0)).thenReturn(stone1);
        when(necklace.getNecklaceStones()).thenReturn(mockNecklaceStones);
    }

    @Test
    public void testAddStoneToNecklaceValidIndex() {
        String userInput = "1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(userInput.getBytes()));
        command = new AddStoneToNecklaceCommand(necklace, scanner);
        command.execute();

        verify(necklace, atLeastOnce()).getAvailableStones();
        verify(mockNecklaceStones, times(1)).add(stone1);
    }


    @Test
    public void testAddStoneToNecklaceInvalidIndex() {
        String userInput = "2\n1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(userInput.getBytes()));

        command = new AddStoneToNecklaceCommand(necklace, scanner);
        command.execute();

        verify(mockNecklaceStones, times(1)).add(stone1);
    }

    @Test
    public void testAddStoneInputMismatch() {
        String userInput = "abc\n1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(userInput.getBytes()));
        command = new AddStoneToNecklaceCommand(necklace, scanner);
        command.execute();

        verify(mockNecklaceStones, times(1)).add(stone1);
    }

    @Test
    public void testEmptyAvailableStones() {
        when(mockAvailableStones.isEmpty()).thenReturn(true);
        String userInput = "1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(userInput.getBytes()));
        command = new AddStoneToNecklaceCommand(necklace, scanner);
        command.execute();

        verify(mockNecklaceStones, times(0)).add(any(Stone.class));
        verify(mockAvailableStones, times(1)).isEmpty();
    }

}
