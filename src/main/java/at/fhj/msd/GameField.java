package at.fhj.msd;

/**
 * Represents the game field for Tic Tac Toe.
 * Manages the 3x3 grid, validates moves, and checks game state.
 */
public class GameField {
    private final char[][] field = new char[3][3];

    /**
     * Gets a copy of the current game field state.
     * @return 3x3 char array representing the game field
     */
    public char[][] getField() {
        char[][] copy = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(field[i], 0, copy[i], 0, 3);
        }
        return copy;
    }

    /**
     * Initializes the game field with empty spaces.
     */
    public void initField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = ' ';
            }
        }
    }

    /**
     * Attempts to make a move on the game field.
     * @param row the row index (0-2)
     * @param column the column index (0-2)
     * @param symbol the player symbol (X or O)
     * @return true if move was successful, false otherwise
     */
    public boolean doStep(int row, int column, char symbol) {
        if (!isValidCoordinate(row, column)) {
            System.out.println("Error: Coordinates must be between 0-2!");
            return false;
        }
        
        if (!isValidSymbol(symbol)) {
            System.out.println("Error: Symbol must be X or O!");
            return false;
        }
        
        if (isCellOccupied(row, column)) {
            System.out.println("Error: Cell is already occupied!");
            return false;
        }
        
        field[row][column] = symbol;
        return true;
    }

    /**
     * Checks if the specified symbol has won the game.
     * @param symbol the symbol to check (X or O)
     * @return true if the symbol has a winning line, false otherwise
     */
    public boolean checkWin(char symbol) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (checkLine(field[i][0], field[i][1], field[i][2], symbol) || 
                checkLine(field[0][i], field[1][i], field[2][i], symbol)) {
                return true;
            }
        }
        
        // Check diagonals
        return checkLine(field[0][0], field[1][1], field[2][2], symbol) || 
               checkLine(field[0][2], field[1][1], field[2][0], symbol);
    }

    /**
     * Checks if the game has ended in a draw.
     * @return true if all cells are filled with no winner, false otherwise
     */
    public boolean isDraw() {
        if (checkWin('X') || checkWin('O')) {
            return false;
        }
        
        for (char[] row : field) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidCoordinate(int row, int column) {
        return row >= 0 && row < 3 && column >= 0 && column < 3;
    }

    private boolean isValidSymbol(char symbol) {
        return symbol == 'X' || symbol == 'O';
    }

    private boolean isCellOccupied(int row, int column) {
        return field[row][column] != ' ';
    }

    private boolean checkLine(char a, char b, char c, char symbol) {
        return a == symbol && b == symbol && c == symbol;
    }
}