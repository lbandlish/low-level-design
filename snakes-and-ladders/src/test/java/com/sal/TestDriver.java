package com.sal;

import java.util.List;
import java.util.ArrayList;
import com.sal.models.*;
import com.sal.models.jump.*;
import com.sal.services.Game;

public class TestDriver {

    @Test
    public testGame() {
        List<Snake> snakes = new ArrayList<>();
        Snake snake = new Snake(10, 2);
        snakes.add(snake);

        List<Player> players = new ArrayList<>();
        Player player = new Player("Lakshay");
        players.add(player);

        Game game = new game(snakes, new ArrayList<>(), players);
        game.playTillCompletion();
    }

}
