package ru.belov.battleship;

public class Battlefield {
    private String[][] field = new String[11][11];

    public Battlefield() {
        fillFieldWithoutShips();
    }

    public String[][] getField() {
        return field;
    }

    private void fillFieldWithoutShips() {
        String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        field[0][0] = " ";
        for (int i = 0; i < letters.length; i++) {
            field[0][i + 1] = letters[i];
            field[i + 1][0] = String.valueOf(i + 1);
        }
        for (int i = 1; i < field.length; i++) {
            for (int j = 1; j < field[i].length; j++) {
                field[i][j] = "-";
            }
        }
    }
    public void showField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (i == 10 && j == 0) {
                    System.out.print(field[i][j] + " ");
                } else {
                    System.out.print(field[i][j] + "  ");
                }
            }
            System.out.println();
        }
    }
}
