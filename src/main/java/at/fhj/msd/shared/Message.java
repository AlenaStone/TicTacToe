package at.fhj.msd.shared;

import java.io.Serializable;

/**
 * Represents a message exchanged between client and server in the TicTacToe game.
 * Contains game state information including move details and current game field.
 * Implements Serializable for network transmission.
 */
public class Message implements Serializable {
    private final String type;
    private final String playerName;
    private final char symbol;
    private final int row;
    private final int column;
    private final char[][] field;

    /**
     * Constructs a new Message with validation for all parameters.
     *
     * @param type the message type (START, MOVE, UPDATE, WIN, DRAW, ERROR)
     * @param playerName the name of the player sending the message
     * @param symbol the player's symbol (X or O)
     * @param row the row index of the move (0-2)
     * @param column the column index of the move (0-2)
     * @param field the current game field (3x3 array)
     */
    public Message(String type, String playerName, char symbol, int row, int column, char[][] field) {
        this.type = validateType(type);
        this.symbol = validateSymbol(symbol);
        this.playerName = validatePlayerName(playerName);
        this.row = row;
        this.column = column;
        this.field = validateAndCopyField(field);
    }

    /**
     * Validates and sets the message type.
     * @param type the message type to validate
     * @return validated type or "ERROR" if invalid
     */
    private String validateType(String type) {
        if (type == null) {
            System.err.println("Error: Message type cannot be null");
            return "ERROR";
        }
        return type;
    }

    /**
     * Validates the player symbol.
     * @param symbol the symbol to validate (must be X or O)
     * @return validated symbol or '\0' if invalid
     */
    private char validateSymbol(char symbol) {
        if (symbol != 'O' && symbol != 'X') {
            System.err.println("Error: Symbol must be O or X");
            return '\0';
        }
        return symbol;
    }

    /**
     * Validates the player name.
     * @param name the name to validate
     * @return validated name or "Unknown" if invalid
     */
    private String validatePlayerName(String name) {
        if (name == null) {
            System.err.println("Error: Player name cannot be null");
            return "Unknown";
        }
        return name;
    }

    /**
     * Validates and creates a defensive copy of the game field.
     * @param field the field to validate and copy
     * @return validated copy of the field or null if invalid
     */
    private char[][] validateAndCopyField(char[][] field) {
        if (!isValidField(field)) {
            System.err.println("Invalid field - must be 3x3 with no null rows");
            return null;
        }
        
        char[][] copy = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(field[i], 0, copy[i], 0, 3);
        }
        return copy;
    }

    /**
     * Checks if a game field is valid (3x3 non-null array).
     * @param field the field to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidField(char[][] field) {
        return field != null &&
               field.length == 3 &&
               field[0] != null && field[1] != null && field[2] != null &&
               field[0].length == 3 && field[1].length == 3 && field[2].length == 3;
    }

    /**
     * @return the message type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @return the player symbol (X or O)
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * @return the move row index
     */
    public int getRow() {
        return row;
    }

    /**
     * @return the move column index
     */
    public int getColumn() {
        return column;
    }

    /**
     * @return a defensive copy of the game field, or null if invalid
     */
    public char[][] getField() {
        if (field == null) {
            return null;
        }

        char[][] copy = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(field[i], 0, copy[i], 0, 3);
        }
        return copy;
    }
}