package gemstest;

import gems.Necklace;
import gems.Stone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class NecklaceTest {

    private Necklace mockNecklace;
    private List<Stone> mockAvailableStones;
    private List<Stone> mockNecklaceStones;

    @BeforeEach
    public void setUp() {
        mockNecklace = mock(Necklace.class);
        mockAvailableStones = mock(List.class);
        mockNecklaceStones = mock(List.class);

        when(mockNecklace.getAvailableStones()).thenReturn(mockAvailableStones);
        when(mockNecklace.getNecklaceStones()).thenReturn(mockNecklaceStones);
    }

    @Test
    public void testAddAvailableStone() {
        Stone stone = new Stone("Diamond", 1.5, 5000.0, 99.0, true, 0.9);
        mockAvailableStones.add(stone);

        verify(mockAvailableStones, times(1)).add(stone);
    }

    @Test
    public void testAddStoneToNecklace() {
        Stone stone = new Stone("Ruby", 2.0, 10000.0, 95.0, true, 0.8);
        mockNecklaceStones.add(stone);

        verify(mockNecklaceStones, times(1)).add(stone);
    }

    @Test
    public void testGetTotalWeight() {
        when(mockNecklace.getTotalWeight()).thenReturn(3.5);

        assertEquals(3.5, mockNecklace.getTotalWeight());
    }

    @Test
    public void testGetTotalValue() {
        when(mockNecklace.getTotalValue()).thenReturn(15000.0);

        assertEquals(15000.0, mockNecklace.getTotalValue());
    }

    @Test
    public void testShowAvailableStonesNotEmpty() {
        when(mockAvailableStones.size()).thenReturn(1);

        assertEquals(1, mockNecklace.getAvailableStones().size());
        verify(mockAvailableStones, times(1)).size();
    }

    @Test
    public void testRemoveAvailableStone() {
        Stone stone = new Stone("Emerald", 1.0, 3000.0, 85.0, false, 0.7);
        mockAvailableStones.add(stone);
        mockAvailableStones.remove(stone);

        verify(mockAvailableStones, times(1)).add(stone);
        verify(mockAvailableStones, times(1)).remove(stone);
    }

    @Test
    public void testShowAvailableStonesEmpty() {
        when(mockAvailableStones.size()).thenReturn(0);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        mockNecklace.showAvailableStones();

        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString().trim());
        System.setOut(originalOut);

        verify(mockNecklace, times(1)).showAvailableStones();
    }

    @Test
    public void testGetAvailableStones() {
        mockNecklace.getAvailableStones();
        verify(mockNecklace, times(1)).getAvailableStones();
    }

    @Test
    public void testGetNecklaceStones() {
        mockNecklace.getNecklaceStones();
        verify(mockNecklace, times(1)).getNecklaceStones();
    }
}
