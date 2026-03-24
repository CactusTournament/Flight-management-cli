package com.sprint.flightcli.http;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ApiClientTest {

    @Test
    void testGetReturnsResponseBody() throws Exception {
        // Mock HttpClient + HttpResponse
        HttpClient mockClient = mock(HttpClient.class);
        HttpResponse<String> mockResponse = mock(HttpResponse.class);

        when(mockResponse.body()).thenReturn("[{\"id\":1}]");
        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        // Inject mock client using reflection
        ApiClient api = new ApiClient();
        var clientField = ApiClient.class.getDeclaredField("client");
        clientField.setAccessible(true);
        clientField.set(api, mockClient);

        String result = api.get("/cities");

        assertEquals("[{\"id\":1}]", result);
    }

    @Test
    void testGetThrowsRuntimeExceptionOnFailure() throws Exception {
        HttpClient mockClient = mock(HttpClient.class);

        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenThrow(new RuntimeException("Network error"));

        ApiClient api = new ApiClient();
        var clientField = ApiClient.class.getDeclaredField("client");
        clientField.setAccessible(true);
        clientField.set(api, mockClient);

        assertThrows(RuntimeException.class, () -> api.get("/bad"));
    }
}