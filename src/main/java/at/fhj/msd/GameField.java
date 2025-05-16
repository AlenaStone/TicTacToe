package at.fhj.msd;

public class GameField {

    private char[][] field = new char[3][3]; // Создаем поле 3x3

    public char[][] getField() {
        return this.field; // Возвращаем текущее состояние поля
    }

    public void initField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = ' ';  // Заполняем каждую ячейку символом ' '
            }
        }
    }

    public boolean doStep(int row, int column, char symbol) {
        // Проверка на допустимые координаты
        if (row > 2 || column > 2 || row < 0 || column < 0) {
            System.out.println("Error: Invalid coordinates!");
            return false;  // Завершаем метод, если координаты не валидны
        }
        if (symbol != 'X' && symbol != 'O') {
            System.out.println("Error: Invalid symbol!");
            return false; // Завершаем метод, если символ не валиден
        }
        // Проверка на занятость ячейки
        if (field[row][column] != ' ') {
            System.out.println("Sorry, this cell is already occupied.");
            return false; // Завершаем метод, если ячейка уже занята
        } else {
            field[row][column] = symbol; // Заполняем ячейку символом игрока
            return true; // Возвращаем true, если ход успешен
        }
    }

    public boolean checkWin(char symbol) {

        for (int i = 0; i < field.length; i++) {
            if (field[i][0] == symbol && field[i][1] == symbol && field[i][2] == symbol) {
                return true; // Проверка по строкам
            }
            if (field[0][i] == symbol && field[1][i] == symbol && field[2][i] == symbol) {
                return true; // Проверка по столбцам
            }
        }
        if (field[0][0] == symbol && field[1][1] == symbol && field[2][2] == symbol) {
            return true; // Проверка по диагонали
        }
        if (field[0][2] == symbol && field[1][1] == symbol && field[2][0] == symbol) {
            return true; // Проверка по диагонали
        }

        return false; // Если ни одно из условий не выполнено, возвращаем false
    }

    public boolean isDraw() {

        if (checkWin('X') || checkWin('O')) {
            return false; // 
        }
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == ' ') {
                    return false; // Если есть пустая ячейка, игра не закончена
                }

            }
        }
        return true; // Если нет пустых ячеек и нет победителя, игра закончена вничью
    }
}
