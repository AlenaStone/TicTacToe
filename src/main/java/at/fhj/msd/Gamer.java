package at.fhj.msd;

public class Gamer {

    private String name;
    private char symbol; // Символ игрока (X или O)

    public Gamer(String name, char symbol) {
        this.name = name; // Инициализируем имя игрока
        this.symbol = symbol; // Инициализируем символ игрока
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol; // Устанавливаем символ игрока
    }

    public void setName(String name) {
        this.name = name; // Устанавливаем имя игрока
    }

    public String getName() {
        return name; // Возвращаем имя игрока
    }

    public char getSymbol() {
        return symbol; // Возвращаем символ игрока
    }
}
