package com.sal;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import com.sal.models.*;
import com.sal.models.jump.*;
import com.sal.services.Game;

public class Driver {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);

        List<Snake> snakes = new ArrayList<>();
        List<Ladder> ladders = new ArrayList<>();
        List<Player> players = new ArrayList<>();

        int numSnakes = scanner.nextInt();
        for (int i = 0; i < numSnakes; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            snakes.add(new Snake(start, end));
        }

        int numLadders = scanner.nextInt();
        for (int i = 0; i < numLadders; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            ladders.add(new Ladder(start, end));
        }

        int numPlayers = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numPlayers; i++) {
            String name = scanner.nextLine();
            players.add(new Player(name));
        }

        Game game = new Game(snakes, ladders, players);
        game.playTillCompletion();
    }
}
