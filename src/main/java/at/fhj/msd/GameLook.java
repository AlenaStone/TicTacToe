package at.fhj.msd;

public class GameLook {

    private GameField gameField; // Поле игры

    public GameLook(GameField gameField) {
        this.gameField = gameField; // Инициализация поля игры
    }

    public void printField() {
        char[][] field = gameField.getField(); // Получаем текущее состояние поля
        System.out.println(" " + 0 + " |  " + 1 + " | " + 2);
        System.out.println("---+----+---+");
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (j < 2) {
                    if (field[i][j] == ' ') {
                        System.out.print(" _ | ");
                    } else {
                        System.out.print(" " + field[i][j] + " | ");
                    }
                }

                if (j == 2) {
                    if (field[i][j] == ' ') {
                        System.out.print(" _ ");
                    } else {
                        System.out.print(" " + field[i][j] + " ");
                    }
                }

            }
            // Печатаем разделитель между строками

            if (i < 2) {
                System.out.println();
                System.out.println("---+----+---+");
            }
            if (i == 2) {
                System.out.println();
                System.out.println("---+----+---+");

            }
        }
    }
}
