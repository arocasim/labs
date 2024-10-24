package commandtest;

import command.RemoveStoneCommand;
import gems.Necklace;
import gems.Stone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RemoveStoneCommandTest {

    private Necklace necklace;
    private RemoveStoneCommand command;
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        necklace = mock(Necklace.class);
    }

    @Test
    public void testRemoveStoneWithValidIndex() {
        List<Stone> availableStones = new ArrayList<>();
        Stone stone = new Stone("Emerald", 1.0, 2000.0, 85.0, false, 0.7);
        availableStones.add(stone);
        when(necklace.getAvailableStones()).thenReturn(availableStones);

        String input = "1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        command = new RemoveStoneCommand(necklace, scanner);
        command.execute();

        assertEquals(0, availableStones.size());
    }

    @Test
    public void testRemoveStoneWithEmptyList() {
        List<Stone> availableStones = new ArrayList<>();

        when(necklace.getAvailableStones()).thenReturn(availableStones);
        String input = "1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        command = new RemoveStoneCommand(necklace, scanner);
        command.execute();

        assertEquals(0, availableStones.size());
    }
    @Test
    public void testRemoveStoneWithNonNumericInput() {
        List<Stone> availableStones = new ArrayList<>();
        availableStones.add(new Stone("Diamond", 1.5, 3000.0, 90.0, true, 0.8));
        when(necklace.getAvailableStones()).thenReturn(availableStones);

        String input = "abc\n1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        command = new RemoveStoneCommand(necklace, scanner);
        command.execute();

        assertEquals(0, availableStones.size());
    }

    @Test
    public void testEmptyAvailableStones() {
        when(necklace.getAvailableStones()).thenReturn(new ArrayList<>());

        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        scanner = new Scanner(System.in);
        command = new RemoveStoneCommand(necklace, scanner);
        command.execute();

        verify(necklace).getAvailableStones();
        assertEquals(0, necklace.getAvailableStones().size());
    }

    @Test
    public void testRemoveStoneInputMismatch() {
        List<Stone> stones = new ArrayList<>();
        stones.add(new Stone("Ruby", 1.5, 3000.0, 90.0, true, 0.8));
        when(necklace.getAvailableStones()).thenReturn(stones);

        String input = "abc\n1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        scanner = new Scanner(System.in);
        command = new RemoveStoneCommand(necklace, scanner);
        command.execute();

        assertTrue(stones.isEmpty());
        verify(necklace, times(3)).getAvailableStones();
    }
}
