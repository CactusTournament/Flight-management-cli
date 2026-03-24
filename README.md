# Flight Management CLI

A Java command-line application for querying flight data from the Flight Management API via HTTP.

## Overview

This CLI application answers four key sprint questions about flights, airports, passengers, and aircraft using:

- **Java 21**
- **Custom HTTP ApiClient**
- **JUnit 5 & Mockito** for testing
- **GitHub Actions** for CI/CD

## Features

### Questions Answered

| # |               Question                     |           Endpoint              |
|---|--------------------------------------------|---------------------------------|
| 1 | What airports are in each city?            | `GET /cities/{id}/airports`     |
| 2 | What aircraft has each passenger flown on? | `GET /passengers/{id}/aircraft` |
| 3 | What airports do aircraft use?             | `GET /aircraft/{id}/airports`   |
| 4 | What airports have passengers used?        | `GET /passengers/{id}/airports` |

## Getting Started

### Prerequisites
- API running at `http://localhost:8080`

### Build & Run

```bash
mvn clean package
java -jar target/flight-cli-1.0.0.jar
```

## Testing

-  JUnit 5 test suite
-  Mockito API mocking
-  Input validation & error handling
-  Output verification
-  GitHub Actions CI

**Test Results:** 8 passed, 0 failures

## Project Structure

```
src/main/java/com/sprint/flightcli/
├── app/
├── http/
├── service/
└── model/
```

Author: Brandon Maloney & SD14