package com.sal.services;

import com.sal.models.*;
import com.sal.models.jump.*;
import java.util.List;

public class Game {

    private final Board board;
    private List<Player> players;
    private final Dice dice;

    public Game(List<Snake> snakes, List<Ladder> ladders, List<Player> players) {
        this.board = new Board(snakes, ladders);
        this.players = players;
        this.dice = new Dice();
    }

    public void playTillCompletion() {
        while (true) {
            for (Player player : players) {
                board.move(player, dice.roll());
                if (board.won(player)) {
                    System.out.println(player.getName() + " wins the game");
                    return;
                }
            }
        }
    }
}
