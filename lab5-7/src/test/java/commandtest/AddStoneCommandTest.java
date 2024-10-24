package commandtest;

import command.AddStoneCommand;
import gems.Necklace;
import gems.Stone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class AddStoneCommandTest {
    private Necklace necklace;
    private AddStoneCommand addStoneCommand;
    private List<Stone> mockStoneList;

    @BeforeEach
    public void setUp() {
        necklace = mock(Necklace.class);
        mockStoneList = mock(List.class);
        when(necklace.getAvailableStones()).thenReturn(mockStoneList);
    }

    @Test
    public void testExecuteWithValidInput() {
        String input = "Emerald\n1.0\n1500.0\n50.0\n0.8\n1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        addStoneCommand = new AddStoneCommand(necklace, scanner);
        addStoneCommand.execute();

        verify(necklace, times(1)).getAvailableStones();
        verify(mockStoneList, times(1)).add(any(Stone.class));
    }

    @Test
    public void testExecuteWithInvalidWeight() {
        String input = "Emerald\n-5.0\n5.00\n1.0\n1500.0\n50.0\n0.8\n1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        addStoneCommand = new AddStoneCommand(necklace, scanner);
        addStoneCommand.execute();

        verify(mockStoneList, times(1)).add(any(Stone.class));
    }

    @Test
    public void testExecuteWithInvalidTransparency() {
        String input = "Sapphire\n1.0\n1500.0\n-10.0\n10.0\n0.8\n1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        addStoneCommand = new AddStoneCommand(necklace, scanner);
        addStoneCommand.execute();

        verify(mockStoneList, times(1)).add(any(Stone.class));
    }

    @Test
    public void testExecuteWithInvalidIsPrecious() {
        String input = "Topaz\n1.0\n1500.0\n50.0\n0.8\n3\n1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        addStoneCommand = new AddStoneCommand(necklace, scanner);
        addStoneCommand.execute();

        verify(mockStoneList, times(1)).add(any(Stone.class));
    }

    @Test
    public void testPromptDoubleWithValidInput() {
        String input = "50.0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        addStoneCommand = new AddStoneCommand(necklace, scanner);
        verifyNoMoreInteractions(necklace);
    }
    @Test
    public void testPromptDoubleWithInvalidInput() {
        String input = "invalid\n50.0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        addStoneCommand = new AddStoneCommand(necklace, scanner);
        verifyNoMoreInteractions(necklace);
    }


    @Test
    public void testPromptIsPreciousWithInvalidInput() {
        String input = "invalid\n1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        addStoneCommand = new AddStoneCommand(necklace, scanner);
        verifyNoMoreInteractions(necklace);
    }

    @Test
    public void testPromptIsPreciousWithOutOfRangeInput() {
        String input = "3\n1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        addStoneCommand = new AddStoneCommand(necklace, scanner);
        verifyNoMoreInteractions(necklace);
    }
}
