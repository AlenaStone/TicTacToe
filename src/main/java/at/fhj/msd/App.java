package at.fhj.msd;

/**
 * Main application class for the TicTacToe game.
 * Serves as the entry point for starting the game.
 * 
 * @author Alena Vodopianova
 */
public class App {

    /**
     * Main method that serves as the entry point for the application.
     * Creates a new game instance and starts the game.
     * 
     * @param args command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
        System.out.println("Game over!");
    }
}