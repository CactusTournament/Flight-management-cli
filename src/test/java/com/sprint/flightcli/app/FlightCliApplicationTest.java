package com.sprint.flightcli.app;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FlightCliApplicationTest {

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream outContent;
    private ByteArrayInputStream inContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testMainMenuExitImmediately() {
        // Simulate user entering '6' to exit
        inContent = new ByteArrayInputStream("6\n".getBytes());
        System.setIn(inContent);
        assertDoesNotThrow(() -> FlightCliApplication.main(new String[]{}));
        String output = outContent.toString();
        assertTrue(output.contains("Flight CLI"));
        assertTrue(output.contains("Exit"));
    }

    @Test
    void testMainMenuHelpAndExit() {
        // Simulate user entering '5' (help), then '6' (exit)
        inContent = new ByteArrayInputStream("5\n6\n".getBytes());
        System.setIn(inContent);
        assertDoesNotThrow(() -> FlightCliApplication.main(new String[]{}));
        String output = outContent.toString();
        assertTrue(output.contains("Help"));
        assertTrue(output.contains("Exit"));
    }

    @Test
    void testMainMenuInvalidInputThenExit() {
        // Simulate user entering invalid input, then '6' to exit
        inContent = new ByteArrayInputStream("abc\n6\n".getBytes());
        System.setIn(inContent);
        assertDoesNotThrow(() -> FlightCliApplication.main(new String[]{}));
        String output = outContent.toString();
        assertTrue(output.contains("Invalid input"));
        assertTrue(output.contains("Exit"));
    }
}
