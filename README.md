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
- API running at `http://localhost:8080` (or your deployed API endpoint)
- Java 21 (or use Docker, see below)

### Build & Run (Locally)

```bash
mvn clean package
java -jar target/flight-cli-1.0.0.jar
```

### Run with Docker

```bash
# Build the Docker image
docker build -t flight-cli .

# Run the CLI (replace API URL if needed)
docker run --rm -it flight-cli
```


## Testing & CI

- JUnit 5 test suite
- Mockito API mocking
- Input validation & error handling
- Output verification
- GitHub Actions CI (runs on every PR and push to main)

**Test Results:** 8 passed, 0 failures

## Docker & CI/CD

- Dockerfile included for containerized builds and runs
- GitHub Actions workflows for build, test, and verification

## Project Status

- All required features implemented (CRUD, API integration, authentication, tests)
- Dockerized and CI-enabled
- Ready for deployment and demonstration

## Project Structure

```
src/main/java/com/sprint/flightcli/
├── app/
├── http/
├── service/
└── model/
```

Author: Brandon Maloney & SD14