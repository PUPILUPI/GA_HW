package ru.belov.hw2;

public class MyOutOfBoundsException extends Exception{
    public MyOutOfBoundsException(String message) {
        super(message);
    }
    @Override
    public synchronized Throwable fillInStackTrace() {
        // Переопределяем этот метод, чтобы не сохранять стек вызовов исключения
        return this;
    }

    @Override
    public String getMessage() {
        // добавляем название класса к ошибке
        return "MyArrayListException: " + super.getMessage();
    }
}
