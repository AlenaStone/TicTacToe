package at.fhj.msd;

/**
 * Handles the visual representation of the Tic Tac Toe game field.
 * Responsible for displaying the current state of the game board.
 */
public class GameLook {
    private final GameField gameField;

    /**
     * Creates a new GameLook instance with the specified game field.
     * @param gameField the game field to visualize
     */
    public GameLook(GameField gameField) {
        this.gameField = gameField;
    }

    /**
     * Prints the current state of the game field to the console.
     * Displays the board with row and column indicators and proper formatting.
     */
    public void printField() {
        char[][] field = gameField.getField();
        
        // Print column headers
        System.out.println(" " + 0 + " |  " + 1 + " | " + 2);
        printHorizontalDivider();
        
        for (int row = 0; row < field.length; row++) {
            printRow(field[row]);
            
            // Print divider between rows (except after last row)
            if (row < field.length - 1) {
                printHorizontalDivider();
            }
        }
        printHorizontalDivider();
    }

    /**
     * Prints a single row of the game field.
     * @param row the row to display
     */
    private void printRow(char[] row) {
        for (int col = 0; col < row.length; col++) {
            char cell = row[col] == ' ' ? '_' : row[col];
            
            if (col < row.length - 1) {
                System.out.print(" " + cell + " |");
            } else {
                System.out.print(" " + cell + " ");
            }
        }
        System.out.println();
    }

    /**
     * Prints a horizontal divider between rows.
     */
    private void printHorizontalDivider() {
        System.out.println("---+----+---");
    }
}