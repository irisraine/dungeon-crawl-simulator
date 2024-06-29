package org.aston.homework.utils;

import java.util.Random;


public class Dice {
    private Dice() {}

    static Random random = new Random();

    public static boolean getChance(int threshold) {
        return random.nextInt(0, 21) >= threshold;
    }
    public static int getRandom(int upperBound) {
        if (upperBound == 0)
            return 0;
        return random.nextInt(0, upperBound);
    }
    public static int getRoll() {
        return random.nextInt(1, 21);
    }
}
