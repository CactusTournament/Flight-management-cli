package com.sprint.flightcli.service;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import com.sprint.flightcli.http.ApiClient;

public class QueryServiceErrorsTest {

    @Test
    void testPassengerNotFound() {
        ApiClient mockApi = Mockito.mock(ApiClient.class);

        when(mockApi.get("/passengers/999"))
                .thenReturn("null");

        QueryService service = new QueryService(mockApi);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Scanner scanner = new Scanner("999\n");
        service.queryAircraftByPassenger(scanner);

        String output = out.toString();

        assertTrue(output.contains("Passenger not found"));
    }
}