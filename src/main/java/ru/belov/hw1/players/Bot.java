package ru.belov.hw1.players;

import java.util.Random;

public class Bot extends Player {
    @Override
    public int[] makeMove() {
        Random random = new Random();
        boolean flag = true;
        while (flag) {
            int x = random.nextInt(10) + 1;
            int y = random.nextInt(10) + 1;
            if (enemyField.getField()[x][y].equals("-")) {
                System.out.println("Бот походил " + (char) (y + 96) + x);
                return new int[]{x, y};
            }
        }
        return new int[0];
    }
}
