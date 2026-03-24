package com.sprint.flightcli.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sprint.flightcli.http.ApiClient;
import com.sprint.flightcli.model.Aircraft;
import com.sprint.flightcli.model.Airport;
import com.sprint.flightcli.model.City;
import com.sprint.flightcli.model.Passenger;

public class QueryService {

    private final ApiClient api;

    public QueryService(ApiClient api) {
        this.api = api;
    }

    private final Gson gson = new Gson();

    // ANSI Colors
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";
    private static final String RESET = "\u001B[0m";

    // Divider
    private static final String LINE = CYAN + "----------------------------------------" + RESET;

    // -------------------------------
    // 1. Airports in a City
    // -------------------------------
    public void queryAirportsInCity(Scanner scanner) {
        System.out.print("Enter city ID: ");
        long id = readLong(scanner);

        String cityJson = api.get("/cities/" + id);
        City city = gson.fromJson(cityJson, City.class);

        if (city == null || city.name == null) {
            System.out.println(RED + "City not found." + RESET);
            return;
        }

        System.out.println("\n" + BLUE + "City: " + city.name + RESET);
        System.out.println(LINE);

        String json = api.get("/cities/" + id + "/airports");
        Type listType = new TypeToken<List<Airport>>(){}.getType();
        List<Airport> airports = gson.fromJson(json, listType);

        if (airports.isEmpty()) {
            System.out.println(YELLOW + "No airports found for this city." + RESET);
            return;
        }

        airports.forEach(a ->
            System.out.println(GREEN + a.name + RESET + " (" + a.code + ")")
        );
    }

    // -------------------------------
    // 2. Aircraft flown by a Passenger
    // -------------------------------
    public void queryAircraftByPassenger(Scanner scanner) {
        System.out.print("Enter passenger ID: ");
        long id = readLong(scanner);

        String passengerJson = api.get("/passengers/" + id);
        Passenger passenger = gson.fromJson(passengerJson, Passenger.class);

        if (passenger == null || passenger.firstName == null) {
            System.out.println(RED + "Passenger not found." + RESET);
            return;
        }

        System.out.println("\n" + BLUE + "Passenger: " 
                + passenger.firstName + " " + passenger.lastName + RESET);
        System.out.println(LINE);

        String json = api.get("/passengers/" + id + "/aircraft");
        Type listType = new TypeToken<List<Aircraft>>(){}.getType();
        List<Aircraft> aircraft = gson.fromJson(json, listType);

        if (aircraft.isEmpty()) {
            System.out.println(YELLOW + "This passenger has not flown on any aircraft." + RESET);
            return;
        }

        aircraft.forEach(a ->
            System.out.println(GREEN + a.type + RESET + " - " + a.airlineName)
        );
    }

    // -------------------------------
    // 3. Airports used by an Aircraft
    // -------------------------------
    public void queryAirportsByAircraft(Scanner scanner) {
        System.out.print("Enter aircraft ID: ");
        long id = readLong(scanner);

        String aircraftJson = api.get("/aircraft/" + id);
        Aircraft aircraftInfo = gson.fromJson(aircraftJson, Aircraft.class);

        if (aircraftInfo == null || aircraftInfo.type == null) {
            System.out.println(RED + "Aircraft not found." + RESET);
            return;
        }

        System.out.println("\n" + BLUE + "Aircraft: " 
                + aircraftInfo.type + RESET + " (" + aircraftInfo.airlineName + ")");
        System.out.println(LINE);

        String json = api.get("/aircraft/" + id + "/airports");
        Type listType = new TypeToken<List<Airport>>(){}.getType();
        List<Airport> airports = gson.fromJson(json, listType);

        if (airports.isEmpty()) {
            System.out.println(YELLOW + "No airports found for this aircraft." + RESET);
            return;
        }

        airports.forEach(a ->
            System.out.println(GREEN + a.name + RESET + " (" + a.code + ")")
        );
    }

    // -------------------------------
    // 4. Airports used by a Passenger
    // -------------------------------
    public void queryAirportsByPassenger(Scanner scanner) {
        System.out.print("Enter passenger ID: ");
        long id = readLong(scanner);

        String passengerJson = api.get("/passengers/" + id);
        Passenger passenger = gson.fromJson(passengerJson, Passenger.class);

        if (passenger == null || passenger.firstName == null) {
            System.out.println(RED + "Passenger not found." + RESET);
            return;
        }

        System.out.println("\n" + BLUE + "Passenger: " 
                + passenger.firstName + " " + passenger.lastName + RESET);
        System.out.println(LINE);

        String json = api.get("/passengers/" + id + "/airports");
        Type listType = new TypeToken<List<Airport>>(){}.getType();
        List<Airport> airports = gson.fromJson(json, listType);

        if (airports.isEmpty()) {
            System.out.println(YELLOW + "This passenger has not used any airports." + RESET);
            return;
        }

        airports.forEach(a ->
            System.out.println(GREEN + a.name + RESET + " (" + a.code + ")")
        );
    }

    public void showHelp() {
        System.out.println("\n" + BLUE + "=== HELP MENU ===" + RESET);
        System.out.println(LINE);

        System.out.println(GREEN + "1. List airports in a city" + RESET);
        System.out.println("   Shows all airports located in a given city.\n");

        System.out.println(GREEN + "2. List aircraft flown by a passenger" + RESET);
        System.out.println("   Shows all aircraft a passenger has flown on.\n");

        System.out.println(GREEN + "3. List airports used by an aircraft" + RESET);
        System.out.println("   Shows all airports an aircraft has visited.\n");

        System.out.println(GREEN + "4. List airports used by a passenger" + RESET);
        System.out.println("   Shows all airports a passenger has used.\n");

        System.out.println(GREEN + "5. Opens the Help menu" + RESET);
        System.out.println("   Shows this help menu.\n");

        System.out.println(GREEN + "6. Exit" + RESET);
        System.out.println("   Closes the application.\n");

        System.out.println(LINE);
        System.out.println(YELLOW + "Tip: Enter numbers only. Invalid input will prompt again." + RESET);
    }


    // -------------------------------
    // Input Validation Helper
    // -------------------------------
    private long readLong(Scanner scanner) {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print(RED + "Invalid input. Enter a number: " + RESET);
            }
        }
    }

    public long readLongForTest(Scanner scanner) {
        return readLong(scanner);
    }

}