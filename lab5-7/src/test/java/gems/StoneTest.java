package gemstest;

import gems.Stone;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StoneTest {

    @Test
    public void testGetters() {
        Stone stone = new Stone("Diamond", 1.5, 5000.0, 99.0, true, 0.9);

        assertEquals("Diamond", stone.getName());
        assertEquals(1.5, stone.getWeight());
        assertEquals(5000.0, stone.getValue());
        assertEquals(99.0, stone.getTransparency());
        assertTrue(stone.isPrecious());
        assertEquals(0.9, stone.getRarity());
    }

    @Test
    public void testToStringPrecious() {
        Stone preciousStone = new Stone("Ruby", 2.0, 10000.0, 95.0, true, 0.8);
        String expectedString = "Precious stone: Ruby [Weight: 2.00 carats, Price: 10000.00 USD, Transparency: 95.00%, Rarity: 0.80]";
        assertEquals(expectedString, preciousStone.toString());
    }

    @Test
    public void testToStringSemiPrecious() {
        Stone semiPreciousStone = new Stone("Amethyst", 3.0, 2000.0, 85.0, false, 0.5);
        String expectedString = "Semi-precious stone: Amethyst [Weight: 3.00 carats, Price: 2000.00 USD, Transparency: 85.00%, Rarity: 0.50]";
        assertEquals(expectedString, semiPreciousStone.toString());
    }

    @Test
    public void testSetPrecious() {
        Stone stone = new Stone("Topaz", 1.0, 1500.0, 80.0, false, 0.6);
        assertFalse(stone.isPrecious());

        stone.setPrecious(true);
        assertTrue(stone.isPrecious());
    }
}
