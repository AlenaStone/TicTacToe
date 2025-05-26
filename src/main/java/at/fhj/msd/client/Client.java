
package at.fhj.msd.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

import at.fhj.msd.shared.Message;

public class Client {
    public static void main(String[] args) { // Переменные для связи и ввода
        Socket socket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        Scanner scanner = new Scanner(System.in);
        char mySymbol = '\0';

        try {
            // Подключаемся к серверу
            socket = new Socket("localhost", 5020);
            System.out.println("Подключено к серверу localhost:5020");
            System.out.println("Клиент успешно подключился к серверу."); // Настраиваем потоки
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            // Ждём START
            Message msg = (Message) in.readObject();
            if ("START".equals(msg.getType())) {
                mySymbol = msg.getSymbol();
                System.out.println("Мой символ: " + mySymbol);
            } else {
                System.err.println("Ожидалось START, получено: " + msg.getType());
                return;
            }

            // Цикл игры
            while (true) {
                // Читаем сообщение
                msg = (Message) in.readObject();
                System.out.println("Получено: " + msg.getType());

                switch (msg.getType()) {
                    case "UPDATE":
                        // Показываем поле
                        printField(msg.getField());
                        // Запрашиваем ход
                        int row = -1, col = -1;
                        boolean validInput = false;
                        while (!validInput) {
                            try {
                                System.out.print("Ваш ход! Введите строку (0-2): ");
                                row = scanner.nextInt();
                                System.out.print("Введите столбец (0-2): ");
                                col = scanner.nextInt();
                                validInput = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка: введите числа от 0 до 2!");
                                scanner.nextLine(); // Очищаем ввод
                            }
                        }

                        // Отправляем MOVE
                        Message move = new Message("MOVE", "Player", mySymbol, row, col, null);
                        out.writeObject(move);
                        out.flush();
                        System.out.println("Отправлен ход: [" + row + "," + col + "]");
                        break;

                    case "WIN":
                        printField(msg.getField());
                        System.out.println("Игрок " + msg.getPlayerName() + " победил!");
                        return; // Выходим

                    case "DRAW":
                        printField(msg.getField());
                        System.out.println("Ничья!");
                        return; // Выходим

                    case "ERROR":
                        System.out.println("Ошибка: " + msg.getPlayerName());
                        break;

                    default:
                        System.out.println("Неизвестное сообщение: " + msg.getType());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка клиента: " + e.getMessage());
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
                if (socket != null)
                    socket.close();
                scanner.close();
            } catch (IOException e) {
                System.err.println("Ошибка при закрытии: " + e.getMessage());
            }
        }
    }

    private static void printField(char[][] f) {
        if (f == null) {
            System.out.println("Поле пустое!");
            return;
        }
        System.out.println("  0 1 2"); // Номера столбцов
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " "); // Номер строки
            for (int j = 0; j < 3; j++) {
                char c = f[i][j] == ' ' ? '-' : f[i][j];
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println("---------");
    }

}