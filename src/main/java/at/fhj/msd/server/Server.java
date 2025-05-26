package at.fhj.msd.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import at.fhj.msd.GameField;
import at.fhj.msd.shared.Message;

/**
 * Server class for TicTacToe game that manages game sessions between two players.
 * Handles game logic, player turns, and communication between clients.
 */
public class Server {

    /**
     * Main method that starts the server and manages game sessions.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        GameField gameField = new GameField();
        gameField.initField();

        try (
            ServerSocket serverSocket = new ServerSocket(5020);
            Socket player1Socket = serverSocket.accept();
            Socket player2Socket = serverSocket.accept();
            ObjectOutputStream out1 = new ObjectOutputStream(player1Socket.getOutputStream());
            ObjectInputStream in1 = new ObjectInputStream(player1Socket.getInputStream());
            ObjectOutputStream out2 = new ObjectOutputStream(player2Socket.getOutputStream());
            ObjectInputStream in2 = new ObjectInputStream(player2Socket.getInputStream());
        ) {
            System.out.println("Player 1 connected!");
            System.out.println("Player 2 connected!");

            out1.writeObject(new Message("START", "Player1", 'X', -1, -1, null));
            out1.flush();
            out2.writeObject(new Message("START", "Player2", 'O', -1, -1, null));
            out2.flush();

            Message initUpdate = new Message("UPDATE", "Server", '\0', -1, -1, gameField.getField());
            out1.writeObject(initUpdate);
            out1.flush();
            out2.writeObject(initUpdate);
            out2.flush();

            int currentPlayer = 0;

            while (true) {
                ObjectInputStream in = (currentPlayer == 0) ? in1 : in2;
                ObjectOutputStream outCurrent = (currentPlayer == 0) ? out1 : out2;
                ObjectOutputStream outOpponent = (currentPlayer == 0) ? out2 : out1;

                System.out.println("Waiting for player " + (currentPlayer + 1) + " move...");

                Message msg = (Message) in.readObject();

                if ("MOVE".equals(msg.getType())) {
                    int row = msg.getRow();
                    int col = msg.getColumn();
                    char symbol = msg.getSymbol();

                    if (!isValidMove(gameField, row, col)) {
                        Message error = new Message("ERROR", "Invalid move: cell is not empty or out of bounds", symbol, row, col, gameField.getField());
                        outCurrent.writeObject(error);
                        outCurrent.flush();
                        continue;
                    }

                    System.out.println("Player " + (currentPlayer + 1) + " moved at (" + row + "," + col + ")");

                    gameField.doStep(row, col, symbol);

                    Message update = new Message("UPDATE", msg.getPlayerName(), symbol, row, col, gameField.getField());
                    outCurrent.writeObject(update);
                    outCurrent.flush();
                    outOpponent.writeObject(update);
                    outOpponent.flush();

                    if (gameField.checkWin(symbol)) {
                        Message win = new Message("WIN", msg.getPlayerName(), '\0', row, col, gameField.getField());
                        outCurrent.writeObject(win);
                        outCurrent.flush();
                        outOpponent.writeObject(win);
                        outOpponent.flush();
                        System.out.println("Player " + (currentPlayer + 1) + " won!");
                        break;
                    }

                    if (gameField.isDraw()) {
                        Message draw = new Message("DRAW", "Server", '\0', -1, -1, gameField.getField());
                        out1.writeObject(draw);
                        out1.flush();
                        out2.writeObject(draw);
                        out2.flush();
                        System.out.println("Game ended in a draw.");
                        break;
                    }

                    currentPlayer = 1 - currentPlayer;

                } else {
                    System.out.println("Received unknown message type: " + msg.getType());
                    Message error = new Message("ERROR", "Unknown message type: " + msg.getType(), '\0', -1, -1, null);
                    outCurrent.writeObject(error);
                    outCurrent.flush();
                }
            }

            System.out.println("Game over. Closing connections.");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    /**
     * Validates if a move is legal on the current game field.
     * 
     * @param field the current game field
     * @param row the row index of the move
     * @param col the column index of the move
     * @return true if the move is valid, false otherwise
     */
    private static boolean isValidMove(GameField field, int row, int col) {
        return row >= 0 && row < 3 &&
               col >= 0 && col < 3 &&
               field.getField()[row][col] == ' ';
    }
}