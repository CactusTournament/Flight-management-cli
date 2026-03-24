package com.sprint.flightcli.service;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import com.sprint.flightcli.http.ApiClient;

public class QueryServiceAircraftAirportsTest {

    @Test
    void testQueryAirportsByAircraft() {
        ApiClient mockApi = Mockito.mock(ApiClient.class);

        when(mockApi.get("/aircraft/1"))
                .thenReturn("{\"id\":1,\"type\":\"Boeing 737\",\"airlineName\":\"Air Canada\"}");

        when(mockApi.get("/aircraft/1/airports"))
                .thenReturn("[{\"name\":\"Toronto Pearson\",\"code\":\"YYZ\"}," +
                             "{\"name\":\"Montreal Trudeau\",\"code\":\"YUL\"}]");

        QueryService service = new QueryService(mockApi);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Scanner scanner = new Scanner("1\n");
        service.queryAirportsByAircraft(scanner);

        String output = out.toString();

        assertTrue(output.contains("Aircraft: Boeing 737"));
        assertTrue(output.contains("Toronto Pearson"));
        assertTrue(output.contains("Montreal Trudeau"));
    }
}