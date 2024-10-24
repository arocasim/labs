package commandtest;

import command.ShowAvailableStonesCommand;
import gems.Necklace;
import gems.Stone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShowAvailableStonesCommandTest {

    private Necklace realNecklace;
    private ShowAvailableStonesCommand command;

    @BeforeEach
    public void setUp() {
        realNecklace = new Necklace();
        command = new ShowAvailableStonesCommand(realNecklace);
    }

    @Test
    public void testExecuteWithNoStones() {
        command.execute();
        assertTrue(realNecklace.getAvailableStones().isEmpty());
    }

    @Test
    public void testExecuteWithStones() {
        realNecklace.getAvailableStones().add(new Stone("Emerald", 1.0, 3000.0, 85.0, false, 0.7));
        realNecklace.getAvailableStones().add(new Stone("Ruby", 2.0, 5000.0, 90.0, true, 0.9));

        command.execute();

        assertEquals(2, realNecklace.getAvailableStones().size());
        assertEquals("Emerald", realNecklace.getAvailableStones().get(0).getName());
        assertEquals("Ruby", realNecklace.getAvailableStones().get(1).getName());
    }
}
