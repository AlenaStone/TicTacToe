
A simple console-based TicTacToe game written in Java.

---

## Project Description

This is a classic TicTacToe game for two players. The game is played on a 3x3 grid where players alternate turns placing their symbols (X or O). The goal is to get three of your symbols in a row — horizontally, vertically, or diagonally.

---

## Features

- Two-player game (X and O).
- Input validation for moves (checks for occupied cells and invalid coordinates).
- Win detection across rows, columns, and diagonals.
- Draw detection when the board is full with no winner.
- Simple console interface showing the game board.
- Object-oriented design with separation of concerns:
  - `GameField` class manages the game board.
  - `Game` class handles the game flow.
  - `GameLook` class handles displaying the board.
  - `Gamer` class represents players.

---

### Local (Console) Version

1. Compile all `.java` files.
2. Run the `Game` class.
3. Follow the prompts in the console to enter moves.

### Client-Server Version

1. Open three terminal windows.
2. In the first terminal, start the server:


```bash
mvn exec:java "-Dexec.mainClass=at.fhj.msd.server.Server"
```

3. In the second and third terminal, start the client:

```bash
mvn exec:java "-Dexec.mainClass=at.fhj.msd.client.Client"
```

4. Follow the prompts in the client console to play.

**Note:** Make sure the server is running before starting the client.

---
## Controls

- Players enter row and column numbers to place their symbol.
- Invalid moves are rejected with an error message.
- Game ends when a player wins or the game is a draw.

---

## Requirements

- Java 17 or higher.
- Maven for building and running the project.
- Console for input and output.

---

## Project Structure

- `src/main/java/at/fhj/msd/game/GameField.java` — manages the 3x3 board state.
- `src/main/java/at/fhj/msd/game/Game.java` — controls the game loop and logic.
- `src/main/java/at/fhj/msd/game/GameLook.java` — displays the board.
- `src/main/java/at/fhj/msd/game/Gamer.java` — stores player information.
- `src/main/java/at/fhj/msd/server/Server.java` — server for networked play.
- `src/main/java/at/fhj/msd/client/Client.java` — client for networked play.
- `src/main/java/at/fhj/msd/shared/Message.java` — serializable messages for client-server communication.

---

## Possible Improvements

- Add AI player.
- Enhance UI with colors or graphics.
- Add move undo functionality.
- Support different board sizes.
- Add support for multiple concurrent games on the server.

---

## Contact

Alena Vodopianova
alonsoy75@gmail.com

For questions or suggestions, feel free to contact me.

---

Enjoy the game! 🕹️
