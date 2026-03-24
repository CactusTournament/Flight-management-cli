package com.sprint.flightcli.service;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import com.sprint.flightcli.http.ApiClient;

public class QueryServiceCityTest {

    @Test
    void testQueryAirportsInCity() {
        ApiClient mockApi = Mockito.mock(ApiClient.class);

        when(mockApi.get("/cities/1"))
                .thenReturn("{\"id\":1,\"name\":\"Toronto\",\"state\":\"ON\",\"population\":3000000}");

        when(mockApi.get("/cities/1/airports"))
                .thenReturn("[{\"name\":\"Toronto Pearson\",\"code\":\"YYZ\"}," +
                             "{\"name\":\"Billy Bishop\",\"code\":\"YTZ\"}]");

        QueryService service = new QueryService(mockApi);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Scanner scanner = new Scanner("1\n");
        service.queryAirportsInCity(scanner);

        String output = out.toString();

        assertTrue(output.contains("City: Toronto"));
        assertTrue(output.contains("Toronto Pearson"));
        assertTrue(output.contains("Billy Bishop"));
    }
}