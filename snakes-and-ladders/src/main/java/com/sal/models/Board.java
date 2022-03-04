package com.sal.models;

import com.sal.models.Player;
import com.sal.models.jump.*;
import java.util.List;
import java.util.ArrayList;

public class Board {

    private static final int BOARD_SIZE = 100;

    List<Point> points = new ArrayList<>();
    
    public Board(List<Snake> snakes, List<Ladder> ladders) {
        for (int i = 0; i <= BOARD_SIZE; i++) {
            points.add(new Point());
        }

        for (Snake snake : snakes) {
            points.get(snake.getStart()).setJumpable(snake);
        }

        for (Ladder ladder : ladders) {
            points.get(ladder.getStart()).setJumpable(ladder);
        } 
    }

    public void move(Player player, int roll) {
        int oldPosition = player.getPosition();
        int newPosition = oldPosition;
        
        if (oldPosition + roll <= BOARD_SIZE) {
            newPosition = oldPosition + roll;

            while (points.get(newPosition).getJumpable() != null) {
                newPosition = points.get(newPosition).getJumpable().getEnd();
            }
        }
        
        player.setPosition(newPosition);
        System.out.println(player.getName() + " rolled a " + roll + " and moved from " + oldPosition + " to " + newPosition);
    }

    public boolean won(Player player) {
        return player.getPosition() == BOARD_SIZE;
    }

    private class Point {
        Jumpable jumpable;
        
        Jumpable getJumpable() {
            return jumpable;
        }

        void setJumpable(Jumpable jumpable) {
            this.jumpable = jumpable;
        }
    }
}
