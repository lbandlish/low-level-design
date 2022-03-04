package com.sal.models;

import java.util.Random;

public class Dice {

    Random random = new Random();

    public int roll() { 
        return random.nextInt(7)+1;
    }
}