package com.sprint.flightcli.service;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QueryServiceInputValidationTest {

    @Test
    void testReadLongValidation() {
        QueryService service = new QueryService(null); // ApiClient not needed

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Simulate invalid input then valid input
        Scanner scanner = new Scanner("abc\n123\n");

        long result = service.readLongForTest(scanner); // We'll expose a test-only wrapper

        String output = out.toString();

        assertTrue(output.contains("Invalid input"));
        assertTrue(result == 123);
    }
}