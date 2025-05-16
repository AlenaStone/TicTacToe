package at.fhj.msd;

import java.util.Scanner;

public class Game {

    Scanner scanner = new Scanner(System.in);
    private GameField gameField; // Поле игры
    private GameLook gameLook; // Визуализация игры

    public Game() {
        gameField = new GameField();
        gameLook = new GameLook(gameField); // Инициализация визуализации игры
        gameField.initField();
    }

    public void play() {

        System.out.println("Welcome to Tic Tac Toe!"); // Приветствие
        System.out.println("Player 1, please enter your name: "); // Запрос имени первого игрока
        String name1 = scanner.next(); // Считываем имя первого игрока
        System.out.println(name1 + " chooses your symbol (X or O): "); // Запрос символа первого игрока
        char symbol = scanner.next().charAt(0); // Считываем символ
        symbol = Character.toUpperCase(symbol); // Приводим символ к верхнему регистру
        // Проверяем, что символ игрока - X или O
        // Если символ не X или O, то запрашиваем его снова
        while (symbol != 'X' && symbol != 'O') {

            System.out.println("Invalid symbol! Please choose X or O.");
            System.out.println(name1 + " chooses your symbol (X or O): "); // Запрос символа первого игрока
            symbol = scanner.next().charAt(0); // Считываем символ
            symbol = Character.toUpperCase(symbol); // Приводим символ к верхнему регистру

        }
        Gamer player1 = new Gamer(name1, symbol); // Создание первого игрока
        char symbol1 = player1.getSymbol(); // Получение символа первого игрока
        System.out.println("Player 2, please enter your name: "); // Запрос имени второго игрока
        Gamer player2 = new Gamer(scanner.next(), symbol1 == 'X' ? 'O' : 'X'); // Создание второго игрока

        char currentPlayer = player1.getSymbol(); // Текущий игрок
        Gamer currentGamer = player1; // Текущий игрок (игрок 1)

        while (true) {

            System.out.println("Current player: " + currentGamer.getName() + " (" + currentPlayer + ")"); // Вывод текущего игрока
            System.out.println("Currently Field:");
            gameLook.printField();

            try {
                System.out.println("Player " + currentGamer.getName() + ", please enter your coordinates (row and column): ");
                System.out.println("Enter row (0-2): ");
                int row = scanner.nextInt(); // Считываем координаты строки
                System.out.println("Enter column (0-2): ");
                int column = scanner.nextInt(); // Считываем координаты столбца
                boolean validMove = gameField.doStep(row, column, currentPlayer);

                if (!validMove) {
                    System.out.println("Cell is occupied or invalid coordinates, try again!");
                    continue; // Если ход не валиден, продолжаем цикл
                }

                if (gameField.checkWin(currentPlayer)) {
                    System.out.println("Player " + currentGamer.getName() + " (" + currentPlayer + ") wins!");
                    gameLook.printField(); // Печатаем финальное поле
                    break; // Завершаем игру, если игрок выиграл

                }
                // Проверяем на ничью
                if (gameField.isDraw()) {
                    System.out.println("It's a draw!");
                    gameLook.printField(); // Печатаем финальное поле
                    break; // Завершаем игру, если ничья
                }

                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Меняем игрока
                currentGamer = (currentGamer == player1) ? player2 : player1; // Меняем текущего игрока

            } catch (Exception e) {
                System.out.println("Invalid input! Please enter numbers only.");
                scanner.nextLine(); // Очищаем буфер ввода
                continue; // Если ввод некорректен, продолжаем цикл
            }

        }
    }
}
