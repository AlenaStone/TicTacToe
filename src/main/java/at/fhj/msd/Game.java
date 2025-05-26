package at.fhj.msd;

import java.util.Scanner;

/**
 * Main game controller class for Tic Tac Toe.
 * Manages game flow, player turns, and win/draw conditions.
 */
public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private final GameField gameField;
    private final GameLook gameLook;

    /**
     * Initializes a new game with fresh game field and display.
     */
    public Game() {
        gameField = new GameField();
        gameLook = new GameLook(gameField);
        gameField.initField();
    }

    /**
     * Starts and manages the main game loop.
     * Handles player setup, turns, and game state checks.
     */
    public void play() {
        System.out.println("Welcome to Tic Tac Toe!");
        
        // Player 1 setup
        System.out.println("Player 1, please enter your name: ");
        String name1 = scanner.next();
        char symbol = getValidSymbol(name1);
        Gamer player1 = new Gamer(name1, symbol);
        
        // Player 2 setup
        System.out.println("Player 2, please enter your name: ");
        Gamer player2 = new Gamer(scanner.next(), player1.getSymbol() == 'X' ? 'O' : 'X');

        Gamer currentPlayer = player1;
        
        // Main game loop
        while (true) {
            displayCurrentTurn(currentPlayer);
            gameLook.printField();

            try {
                int[] coordinates = getPlayerMove(currentPlayer);
                int row = coordinates[0];
                int column = coordinates[1];

                if (!gameField.doStep(row, column, currentPlayer.getSymbol())) {
                    System.out.println("Cell is occupied or invalid coordinates, try again!");
                    continue;
                }

                if (checkGameEndCondition(currentPlayer)) {
                    break;
                }

                currentPlayer = switchPlayer(currentPlayer, player1, player2);

            } catch (Exception e) {
                System.out.println("Invalid input! Please enter numbers only.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Validates and returns player's symbol choice.
     */
    private char getValidSymbol(String playerName) {
        System.out.println(playerName + ", choose your symbol (X or O): ");
        char symbol = Character.toUpperCase(scanner.next().charAt(0));
        
        while (symbol != 'X' && symbol != 'O') {
            System.out.println("Invalid symbol! Please choose X or O.");
            System.out.println(playerName + ", choose your symbol (X or O): ");
            symbol = Character.toUpperCase(scanner.next().charAt(0));
        }
        return symbol;
    }

    /**
     * Displays current player's turn information.
     */
    private void displayCurrentTurn(Gamer player) {
        System.out.println("Current player: " + player.getName() + 
                         " (" + player.getSymbol() + ")");
    }

    /**
     * Gets valid move coordinates from player.
     */
    private int[] getPlayerMove(Gamer player) {
        System.out.println("Player " + player.getName() + 
                         ", please enter your coordinates:");
        System.out.println("Enter row (0-2): ");
        int row = scanner.nextInt();
        System.out.println("Enter column (0-2): ");
        int column = scanner.nextInt();
        return new int[]{row, column};
    }

    /**
     * Checks win/draw conditions and handles game end.
     * @return true if game should end, false otherwise
     */
    private boolean checkGameEndCondition(Gamer player) {
        if (gameField.checkWin(player.getSymbol())) {
            System.out.println("Player " + player.getName() + 
                             " (" + player.getSymbol() + ") wins!");
            gameLook.printField();
            return true;
        }
        
        if (gameField.isDraw()) {
            System.out.println("It's a draw!");
            gameLook.printField();
            return true;
        }
        
        return false;
    }

    /**
     * Switches to the other player.
     */
    private Gamer switchPlayer(Gamer current, Gamer player1, Gamer player2) {
        return (current == player1) ? player2 : player1;
    }
}