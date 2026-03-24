package com.sprint.flightcli.app;

import java.util.Scanner;

import com.sprint.flightcli.http.ApiClient;
import com.sprint.flightcli.service.QueryService;

public class FlightCliApplication {

    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        QueryService service = new QueryService(new ApiClient());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Flight CLI ===");
            System.out.println("1. List airports in a city");
            System.out.println("2. List aircraft flown by a passenger");
            System.out.println("3. List airports used by an aircraft");
            System.out.println("4. List airports used by a passenger");
            System.out.println("5. Help");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");

            int choice = readInt(scanner);   

            switch (choice) {
                case 1 -> service.queryAirportsInCity(scanner);
                case 2 -> service.queryAircraftByPassenger(scanner);
                case 3 -> service.queryAirportsByAircraft(scanner);
                case 4 -> service.queryAirportsByPassenger(scanner);
                case 5 -> service.showHelp();


                case 6 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println(RED + "Invalid choice." + RESET);
            }
        }
    }

    // -------------------------------
    // Input Validation Helper
    // -------------------------------
    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print(RED + "Invalid input. Enter a number: " + RESET);
            }
        }
    }
}