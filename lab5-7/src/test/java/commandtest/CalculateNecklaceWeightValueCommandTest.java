package commandtest;

import command.CalculateNecklaceWeightValueCommand;
import gems.Necklace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CalculateNecklaceWeightValueCommandTest {

    private Necklace necklace;
    private CalculateNecklaceWeightValueCommand command;

    @BeforeEach
    public void setUp() {
        necklace = mock(Necklace.class);
        command = new CalculateNecklaceWeightValueCommand(necklace);
    }

    @Test
    public void testExecute() {
        when(necklace.getTotalWeight()).thenReturn(5.5);
        when(necklace.getTotalValue()).thenReturn(15000.0);

        command.execute();

        verify(necklace, times(1)).getTotalWeight();
        verify(necklace, times(1)).getTotalValue();
    }
}
