package ru.belov.hw1;

import ru.belov.hw1.players.Bot;
import ru.belov.hw1.players.Player;
import ru.belov.hw1.players.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    List<Player> players;
    private int counter;

    public Game() {
        players = new ArrayList<>();
        players.add(new Bot());
        players.add(new User());
        players.get(0).placeShips();
        players.get(1).placeShips();
        Random random = new Random();
        counter = random.nextInt(2);
        startMove();
    }

    private void startMove() {
        int indexOfMove = counter % 2;
        int indexOfApprove = 0;
        Player movePlayer = players.get(indexOfMove);
        int[] res = movePlayer.makeMove();
        if (indexOfMove == 0) {
            indexOfApprove = 1;
        }
        Player approvePlayer = players.get(indexOfApprove);
        if(approvePlayer.approveMove(res[0], res[1])) {
            movePlayer.updateEnemyField(res[0], res[1], "$");
            approvePlayer.setHits(approvePlayer.getHits()+1);
            approvePlayer.checkHits();
            startMove();
        } else {
            movePlayer.updateEnemyField(res[0], res[1], "x");
            counter++;
            startMove();
        }
    }
}
