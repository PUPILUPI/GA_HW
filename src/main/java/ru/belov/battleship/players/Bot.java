package ru.belov.battleship.players;

import java.util.Random;

public class Bot extends Player {
    @Override
    public int[] makeMove() {
        Random random = new Random();
        boolean flag = true;
        while (flag) {
            int x = random.nextInt(10) + 1;
            int y = random.nextInt(10) + 1;
            System.out.println("Бот походил " + (char) (y + 96) + x);
            if (enemyField.getField()[x][y].equals("-")) {
                return new int[]{x, y};
            }
        }
        return new int[0];
    }
}
