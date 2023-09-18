package ru.belov.battleship.players;

import ru.belov.battleship.Battlefield;

import java.util.Random;

public abstract class Player {
    protected Battlefield enemyField = new Battlefield();
    protected Battlefield ownField = new Battlefield();

    protected int hits = 0;

    public abstract int[] makeMove();

    public void checkHits() {
        if (this instanceof Bot && hits == 20) {
            System.out.println("Бот проиграл. Вы выиграли!");
            System.exit(0);
        } else if (this instanceof User && hits == 20) {
            System.out.println("Бот выиграл. Вы проиграли!");
            System.exit(0);
        }
    }

    public boolean approveMove(int row, int col) {
        if(ownField.getField()[row][col].equals("s")) {
            ownField.getField()[row][col] = "$";
            return true;
        } else {
            ownField.getField()[row][col] = "х";
            return false;
        }
    }

    public void updateEnemyField(int row, int col, String symbol) {
        enemyField.getField()[row][col] = symbol;
    }

    public void placeShips() {
        Random random = new Random();
        placeQuadrupleShips(random);
        placeSingleShips(random);
        placeDoubleShips(random);
        placeTripleShips(random);
    }

    private void placeSingleShips(Random random) {
        int count = 0;
        while (count != 4) {
            int x = random.nextInt(10) + 1;
            int y = random.nextInt(10) + 1;
            if (ownField.getField()[x][y].equals("-") && checkNeighbourCell(x, y, ownField.getField())) {
                ownField.getField()[x][y] = "s";
                count++;
            }
        }
    }

    private void placeDoubleShips(Random random) {
        int count = 0;
        String[][] bf = ownField.getField();
        while (count != 3) {
            int x1 = random.nextInt(10) + 1;
            int y1 = random.nextInt(10) + 1;
            int x2 = 0;
            int y2 = 0;
            int direction = random.nextInt(4);
            switch (direction) {
                case 0 -> {
                    x2 = x1 + 1;
                    y2 = y1;
                }
                case 1 -> {
                    x2 = x1 - 1;
                    y2 = y1;
                }
                case 2 -> {
                    x2 = x1;
                    y2 = y1 + 1;
                }
                case 3 -> {
                    x2 = x1;
                    y2 = y1 - 1;
                }
            }
            if (checkOutOfBounds(x2, y2, bf) && bf[x2][y2].equals("-") && checkNeighbourCell(x2, y2, bf) &&
                    bf[x1][y1].equals("-") && checkNeighbourCell(x1, y1, bf)
            ) {
                bf[x1][y1] = "s";
                bf[x2][y2] = "s";
                count++;
            }
        }
    }

    private void placeTripleShips(Random random) {
        int count = 0;
        String[][] bf = ownField.getField();
        while (count != 2) {
            int x1 = random.nextInt(10) + 1;
            int y1 = random.nextInt(10) + 1;
            int x2 = 0, y2 = 0, x3 = 0, y3 = 0;
            int direction = random.nextInt(4);
            switch (direction) {
                case 0 -> {
                    x2 = x1 + 1;
                    y2 = y1;
                    x3 = x2 + 1;
                    y3 = y1;
                }
                case 1 -> {
                    x2 = x1 - 1;
                    y2 = y1;
                    x3 = x2 - 1;
                    y3 = y1;
                }
                case 2 -> {
                    x2 = x1;
                    y2 = y1 + 1;
                    x3 = x1;
                    y3 = y2 + 1;
                }
                case 3 -> {
                    x2 = x1;
                    y2 = y1 - 1;
                    x3 = x1;
                    y3 = y2 - 1;
                }
            }
            if (checkOutOfBounds(x3, y3, bf) && bf[x3][y3].equals("-") && checkNeighbourCell(x3, y3, bf) &&
                    checkOutOfBounds(x2, y2, bf) && bf[x2][y2].equals("-") && checkNeighbourCell(x2, y2, bf) &&
                    bf[x1][y1].equals("-") && checkNeighbourCell(x1, y1, bf)
            ) {
                bf[x1][y1] = "s";
                bf[x2][y2] = "s";
                bf[x3][y3] = "s";
                count++;
            }
        }
    }

    private void placeQuadrupleShips(Random random) {
        int count = 0;
        String[][] bf = ownField.getField();
        while (count != 1) {
            int x1 = random.nextInt(10) + 1;
            int y1 = random.nextInt(10) + 1;
            int x2 = 0, y2 = 0, x3 = 0, y3 = 0, x4 = 0, y4 = 0;
            int direction = random.nextInt(4);
            switch (direction) {
                case 0 -> {
                    y4 = y3 = y2 = y1;
                    x2 = x1 + 1;
                    x3 = x2 + 1;
                    x4 = x3 + 1;
                }
                case 1 -> {
                    y4 = y3 = y2 = y1;
                    x2 = x1 - 1;
                    x3 = x2 - 1;
                    x4 = x3 - 1;
                }
                case 2 -> {
                    x4 = x3 = x2 = x1;
                    y2 = y1 + 1;
                    y3 = y2 + 1;
                    y4 = y3 + 1;
                }
                case 3 -> {
                    x4 = x3 = x2 = x1;
                    y2 = y1 - 1;
                    y3 = y2 - 1;
                    y4 = y3 - 1;
                }
            }
            if (checkOutOfBounds(x4, y4, bf) && bf[x4][y4].equals("-") && checkNeighbourCell(x4, y4, bf) &&
                    checkOutOfBounds(x3, y3, bf) && bf[x3][y3].equals("-") && checkNeighbourCell(x3, y3, bf) &&
                    checkOutOfBounds(x2, y2, bf) && bf[x2][y2].equals("-") && checkNeighbourCell(x2, y2, bf) &&
                    bf[x1][y1].equals("-") && checkNeighbourCell(x1, y1, bf)
            ) {
                bf[x1][y1] = "s";
                bf[x2][y2] = "s";
                bf[x3][y3] = "s";
                bf[x4][y4] = "s";
                count++;
            }
        }
    }


    public Battlefield getOwnField() {
        return ownField;
    }

    private boolean checkNeighbourCell(int row, int col, String[][] bf) {
        return checkTopCell(row, col, bf) &&
                checkBottomCell(row, col, bf) &&
                checkLeftCell(row, col, bf) &&
                checkRightCell(row, col, bf) &&
                checkTopLeftCell(row, col, bf) &&
                checkTopRightCell(row, col, bf) &&
                checkBottomLeftCell(row, col, bf) &&
                checkBottomRightCell(row, col, bf);
    }

    private boolean checkCell(String cell) {
        return !cell.equals("s");
    }

    private boolean checkTopCell(int row, int col, String[][] bf) {
        if (row > 1) {
            String cell = bf[row - 1][col];
            return checkCell(cell);
        }
        return true;
    }

    private boolean checkLeftCell(int row, int col, String[][] bf) {
        if (col > 1) {
            String cell = bf[row][col - 1];
            return checkCell(cell);
        }
        return true;
    }

    private boolean checkRightCell(int row, int col, String[][] bf) {
        if (col < bf.length - 1) {
            String cell = bf[row][col + 1];
            return checkCell(cell);
        }
        return true;
    }

    private boolean checkBottomCell(int row, int col, String[][] bf) {
        if (row < bf.length - 1) {
            String cell = bf[row + 1][col];
            return checkCell(cell);
        }
        return true;
    }

    private boolean checkTopLeftCell(int row, int col, String[][] bf) {
        if (row > 1 && col > 1) {
            String cell = bf[row - 1][col - 1];
            return checkCell(cell);
        }
        return true;
    }

    private boolean checkTopRightCell(int row, int col, String[][] bf) {
        if (row > 1 && col < bf.length - 1) {
            String cell = bf[row - 1][col + 1];
            return checkCell(cell);
        }
        return true;
    }

    private boolean checkBottomLeftCell(int row, int col, String[][] bf) {
        if (row < bf.length - 1 && col > 1) {
            String cell = bf[row + 1][col - 1];
            return checkCell(cell);
        }
        return true;
    }

    private boolean checkBottomRightCell(int row, int col, String[][] bf) {
        if (row < bf.length - 1 && col < bf.length - 1) {
            String cell = bf[row + 1][col + 1];
            return checkCell(cell);
        }
        return true;
    }

    private boolean checkOutOfBounds(int x, int y, String[][] bf) {
        return x < bf.length && y < bf.length && x > 1 && y > 1;
    }

    public Battlefield getEnemyField() {
        return enemyField;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }
}
