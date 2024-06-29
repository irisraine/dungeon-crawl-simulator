package org.aston.modes;

import org.aston.enemies.Enemy;
import org.aston.heroes.Hero;
import org.aston.utils.*;

import java.util.ArrayList;

public class TrainingGround {
    private ArrayList<Hero> heroesParty;
    private ArrayList<Enemy> dummies;

    public TrainingGround(ArrayList<Hero> heroesParty) {
        this.heroesParty = heroesParty;
        this.dummies = UnitGenerator.setUpDummies();
    }

    public void main() throws InterruptedException {
        int numberOfDummies = this.dummies.size();
        Hero currentHero;
        Enemy targetedDummy;
        boolean isReady;
        int turnCounter = 1;

        TRAINING: while (true) {
            for (Hero hero : heroesParty) {
                currentHero = hero;
                isReady = currentHero.turn();
                if (!isReady) continue;
                Messages.turnHeroLog(turnCounter++);
                targetedDummy = this.dummies.get(Dice.getRandom(numberOfDummies - 1));
                currentHero.attackEnemy(targetedDummy);
                if (!targetedDummy.isAlive()) {
                    this.dummies.remove(targetedDummy);
                    numberOfDummies--;
                    Messages.infoLog();
                    Messages.enemiesRemaining(numberOfDummies);
                    if (numberOfDummies == 0) {
                        Messages.infoLog();
                        Messages.finishTraining();
                        break TRAINING;
                    }
                }
                Thread.sleep(500);
            }
        }
    }
}
