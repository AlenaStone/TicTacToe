package at.fhj.msd;

/**
 * Represents a player in the Tic Tac Toe game.
 * Stores player information including name and game symbol (X or O).
 */
public class Gamer {
    private String name;
    private char symbol;

    /**
     * Creates a new player with specified name and symbol.
     * @param name the player's name
     * @param symbol the player's symbol (must be 'X' or 'O')
     */
    public Gamer(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    /**
     * Sets the player's symbol.
     * @param symbol the symbol to set (should be 'X' or 'O')
     */
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Sets the player's name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the player's name.
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's symbol.
     * @return the player's symbol ('X' or 'O')
     */
    public char getSymbol() {
        return symbol;
    }
}