package org.aston.enemies;

import org.aston.heroes.Hero;
import org.aston.utils.Messages;

public class Dummy extends Enemy {
    private final static int HEALTH_MAX_SIZE = 100;

    public Dummy() {
        super(HEALTH_MAX_SIZE);
    }

    public boolean turn() {
        return false;
    };

    @Override
    public void attackHero(Hero hero) {
        Messages.attackByEnemy(this);
    }

    @Override
    public boolean isEvade() {
        return false;
    }

    @Override
    public String toString() {
        return "Тренировочный болванчик";
    }
}
