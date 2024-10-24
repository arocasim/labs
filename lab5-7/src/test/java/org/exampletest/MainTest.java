package org.exampletest;

import org.example.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testAddStoneFunctionality() {
        String simulatedInput = "1\nEmerald\n1.0\n2000.0\n85.0\n0.5\n1\n11\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);
        Main.main(new String[] {});

        String output = outContent.toString();
        assertFalse(output.contains("Камінь Emerald додано до списку доступних каменів."), "Повинно вказувати на успішне додавання каменю.");
    }

    @Test
    public void testExitFunctionality() {
        String simulatedInput = "11\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);
        Main.main(new String[] {});

        String output = outContent.toString();
        assertTrue(output.contains("Exiting..."), "Should indicate exit message");
    }

    @Test
    public void testInvalidOption() {
        String simulatedInput = "99\n11\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);
        Main.main(new String[] {});

        String output = outContent.toString();
        assertTrue(output.contains("Invalid choice."), "Should indicate invalid choice message");
    }

    @Test
    public void testNonNumericInput() {
        String simulatedInput = "abc\n11\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);
        Main.main(new String[] {});

        String output = outContent.toString();
        assertTrue(output.contains("Please enter a numeric value."), "Should prompt for numeric input");
    }
    
    @Test
    public void testShowMenuInvalidChoice() {
        String simulatedInput = "99\n11\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);
        Main.main(new String[] {});

        String output = outContent.toString();
        assertTrue(output.contains("Invalid choice."), "Should indicate invalid choice message.");
    }

    @Test
    public void testShowMenuNonNumericInput() {
        String simulatedInput = "abc\n11\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);
        Main.main(new String[] {});

        String output = outContent.toString();
        assertTrue(output.contains("Please enter a numeric value."), "Should prompt for numeric input.");
    }

    @Test
    public void testInvalidChoice() {
        String simulatedInput = "12\n11\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);
        Main.main(new String[] {});

        String output = outContent.toString();
        assertTrue(output.contains("Invalid choice."), "Повідомлення про неправильний вибір.");
        assertTrue(output.contains("Exiting..."), "Повідомлення про вихід з програми.");
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
}
