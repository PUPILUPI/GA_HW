package ru.belov.hw4;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CommandExecutor {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService mainExecutor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            System.out.println("Асинхронный привет!");
            try {
                Thread.sleep(5000); // Ждем 5 секунд
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Асинхронный пока!");
        }, 0, 10, TimeUnit.SECONDS);
        mainExecutor.scheduleAtFixedRate(() -> {
            System.out.println("Работает основная программа");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, 0, 1, TimeUnit.SECONDS);
    }
}
