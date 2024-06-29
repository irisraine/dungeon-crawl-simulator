package org.aston.utils;

import java.util.ArrayList;
import org.aston.enemies.*;
import org.aston.heroes.*;


public class UnitGenerator {
    private UnitGenerator() {}

    public static ArrayList<Hero> createParty() {
        ArrayList<Hero> heroesParty = new ArrayList<>();
        heroesParty.add(new Warrior(Names.getWarriorName()));
        heroesParty.add(new Mage(Names.getMageName()));
        heroesParty.add(new Archer(Names.getArcherName()));

        return heroesParty;
    }
    public static ArrayList<Enemy> spawnEnemyGroup(int groupSize) {
        ArrayList<Enemy> enemyGroup = new ArrayList<>();
        int roll;

        for (int i = 0; i < groupSize; i++) {
            roll = Dice.getRoll();
            if (roll <= 11) {
                roll = Dice.getRoll();
                if (roll <= 7) {
                    enemyGroup.add(new Orc());
                } else if (roll <= 14) {
                    enemyGroup.add(new Goblin());
                } else {
                    enemyGroup.add(new Zombie());
                }
            } else if (roll <= 18) {
                roll = Dice.getRoll();
                if (roll <= 10) {
                    enemyGroup.add(new Wraith());
                } else {
                    enemyGroup.add(new Drow());
                }
            } else {
                enemyGroup.add(new Lich());
            }
        }
        return enemyGroup;
    }

    public static ArrayList<Enemy> setUpDummies() {
        ArrayList<Enemy> dummies = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            dummies.add(new Dummy());
        }
        return dummies;
    }
}
