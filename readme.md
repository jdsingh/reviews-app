# Review polling service

This service polls the iTunes API for reviews of a specific app and store them in a `reviews.json` file.

It exposes an endpoint that returns the reviews of the app left by the users within the last 48 hours.

## API

`GET /api/reviews`

## How to test

### Frontend

- CD into the `frontend` directory
- Install the dependencies using `npm install`
- Run the tests using `npm run test`

### Backend

- Run the tests using `./gradlew test`

## How to build

### Frontend

- CD into the `frontend` directory
- Install the dependencies using `npm install`
- Build the frontend using `npm run build`

### Backend

- Build the service using `./gradlew build`

## How to run

- Clone the repository
- CD into the `frontend` directory
- Install the dependencies using `npm install`
- Build the frontend app using `npm run build`
- Run the service using `./gradlew run`
- The frontend react app will be available at `http://localhost:8080`
- The reviews API can be accessed at `http://localhost:8080/api/reviews`
