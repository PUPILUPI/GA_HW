package ru.belov.battleship.players;
import java.util.Scanner;

public class User extends Player {

    @Override
    public int[] makeMove() {
        this.enemyField.showField();
        System.out.println("Ваше поле ниже:");
        this.ownField.showField();
        System.out.println("введите координаты для хода\n(букву a-j и число 1-10 через пробел): ");
        Scanner sc = new Scanner(System.in);
        String move = sc.nextLine();
        String[] moves = move.split(" ");
        int ascii = moves[0].charAt(0);
        int col = ascii - 96;
        int row = Integer.parseInt(moves[1]);
        return new int[]{row, col};
    }
}
