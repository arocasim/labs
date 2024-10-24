package commandtest;

import command.SortStonesByValueCommand;
import gems.Necklace;
import gems.Stone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SortStonesByValueCommandTest {

    private Necklace necklace;
    private SortStonesByValueCommand command;

    @BeforeEach
    public void setUp() {
        necklace = mock(Necklace.class);
        command = new SortStonesByValueCommand(necklace);
    }

    @Test
    public void testExecute() {
        List<Stone> stones = new ArrayList<>();
        stones.add(new Stone("Emerald", 1.0, 2000.0, 85.0, false, 0.7));
        stones.add(new Stone("Ruby", 2.0, 5000.0, 95.0, true, 0.8));
        stones.add(new Stone("Diamond", 1.5, 10000.0, 99.0, true, 0.9));

        when(necklace.getNecklaceStones()).thenReturn(stones);
        command.execute();

        assertEquals(10000.0, stones.get(0).getValue());
        assertEquals(5000.0, stones.get(1).getValue());
        assertEquals(2000.0, stones.get(2).getValue());
        verify(necklace, times(1)).getNecklaceStones();
    }
}
