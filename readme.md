# Review polling service

## Description

This service will poll the iTunes API for reviews of a specific app and store them in a `reviews.json` file.

It exposes an endpoint that returns the reviews of the app left by the users within the last 48 hours.

## Requirements

- The service should be written in Kotlin using ktor.

## API

`GET /reviews`

## How to test

- Run the tests using `./gradlew test`

## How to build

- Build the service using `./gradlew build`

## How to run

- Clone the repository
- Run the service using `./gradlew run`
- The service will be available at `http://localhost:8080`
- The reviews can be accessed at `http://localhost:8080/reviews`
