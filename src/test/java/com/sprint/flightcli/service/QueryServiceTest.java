package com.sprint.flightcli.service;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import com.sprint.flightcli.http.ApiClient;

public class QueryServiceTest {

    private String stripAnsi(String input) {
        return input.replaceAll("\u001B\\[[;\\d]*m", "");
    }

    @Test
    void testQueryAircraftByPassenger() {
        ApiClient mockApi = Mockito.mock(ApiClient.class);

        when(mockApi.get("/passengers/1"))
                .thenReturn("{\"id\":1,\"firstName\":\"Brandon\",\"lastName\":\"Smith\",\"phoneNumber\":\"555\"}");

        when(mockApi.get("/passengers/1/aircraft"))
                .thenReturn("[{\"type\":\"Boeing 737\",\"airlineName\":\"Air Canada\"}]");

        QueryService service = new QueryService(mockApi);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Scanner scanner = new Scanner("1\n");
        service.queryAircraftByPassenger(scanner);

        String output = stripAnsi(out.toString());

        assertTrue(output.contains("Passenger: Brandon Smith"));
        assertTrue(output.contains("Boeing 737 - Air Canada"));
    }
}