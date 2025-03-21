# Reactive Blackjack Game API

This project is a backend implementation of a reactive Blackjack game API. It allows players to create accounts, play games, and track their statistics. The API is built using Spring Boot with reactive programming support.

## Features

- **Player Management**: Create, update, delete, and retrieve player profiles.
- **Game Management**: Create, update, delete, and retrieve game sessions.
- **Reactive Programming**: Fully asynchronous and non-blocking API using Project Reactor.
- **Statistics Tracking**: Tracks player statistics such as games played, victories, defeats, draws, and scores.

## Technologies Used

- **Spring Boot**: Framework for building the application.
  - `spring-boot-starter-data-r2dbc`: For reactive MySQL database operations.
  - `spring-boot-starter-data-mongodb-reactive`: For reactive MongoDB operations.
  - `spring-boot-starter-webflux`: For building reactive REST APIs.
- **R2DBC**: Reactive relational database connectivity for MySQL.
- **MongoDB**: NoSQL database for storing game data.
- **Lombok**: Reduces boilerplate code for models and services.
- **Java 21**: Latest Java version for modern features and performance.

## Project Structure

- **Controllers**: Handle HTTP requests and responses.
  - `PlayerController`: Manages player-related operations.
  - `GameController`: Manages game-related operations.
- **Services**: Contain business logic.
  - `PlayerService`: Handles player operations.
  - `GameService`: Handles game operations.
- **Repositories**: Interface with databases.
  - `PlayerRepository`: Reactive repository for player data in MySQL.
  - `GameRepository`: Reactive repository for game data in MongoDB.
- **Models**: Represent entities like `Player` and `Game`.
- **Enums**: Define constants like `GameResult`, `GameState`, `Figures`, and `Values`.

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd blackjack
   ```

2. **Configure Databases**:
   - MySQL:
     - Create a database named `blackjack_db`.
     - Update `spring.r2dbc.url`, `spring.r2dbc.username`, and `spring.r2dbc.password` in `application.properties`.
   - MongoDB:
     - Ensure MongoDB is running on `localhost:27017`.
     - Update `spring.data.mongodb.database`, `spring.data.mongodb.host`, and `spring.data.mongodb.port` in `application.properties`.

3. **Build the Project**:
   ```bash
   ./gradlew build
   ```

4. **Run the Application**:
   ```bash
   ./gradlew bootRun
   ```

5. **Access the API**:
   - Base URL: `http://localhost:8080`
   - Example Endpoints:
     - `POST /player`: Create a new player.
     - `GET /player/all`: Retrieve all players.
     - `POST /game`: Create a new game.

## Testing

Run the tests using:
```bash
./gradlew test
```

## Future Enhancements

- Add authentication and authorization.
- Implement advanced game rules.
- Add support for multiplayer games.
- Create a frontend for the game.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
