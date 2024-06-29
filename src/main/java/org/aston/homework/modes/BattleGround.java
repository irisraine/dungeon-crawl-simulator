package org.aston.homework.modes;

import org.aston.homework.enemies.Enemy;
import org.aston.homework.heroes.Hero;
import org.aston.homework.utils.*;
import java.util.ArrayList;


public class BattleGround {
    private ArrayList<Hero> heroesParty;
    private ArrayList<Enemy> enemiesGroup;

    public BattleGround(ArrayList<Hero> heroesParty, int difficultyLevel) {
        this.heroesParty = heroesParty;
        this.enemiesGroup = UnitGenerator.spawnEnemyGroup(3 * difficultyLevel);
    }

    public void main() throws InterruptedException {
        int numberOfHeroes = this.heroesParty.size();
        int numberOfEnemies = this.enemiesGroup.size();
        Hero currentHero, targetedHero;
        Enemy currentEnemy, targetedEnemy;
        boolean isReady;
        int turnCounter = 1;

        BATTLE: while (true) {
            for (int heroIndex = 0; heroIndex < numberOfHeroes; heroIndex++) {
                currentHero = this.heroesParty.get(heroIndex);
                isReady = currentHero.turn();
                if (!currentHero.isAlive()) {
                    this.heroesParty.remove(currentHero);
                    numberOfHeroes--;
                    Messages.infoLog();
                    Messages.heroesRemaining(heroesParty);
                    if (numberOfHeroes == 0) break BATTLE;
                    continue;
                }
                if (!isReady) continue;
                Messages.turnHeroLog(turnCounter++);
                targetedEnemy = this.enemiesGroup.get(Dice.getRandom(numberOfEnemies - 1));
                currentHero.attackEnemy(targetedEnemy);
                if (!targetedEnemy.isAlive()) {
                    this.enemiesGroup.remove(targetedEnemy);
                    numberOfEnemies--;
                    Messages.infoLog();
                    Messages.enemiesRemaining(numberOfEnemies);
                    if (numberOfEnemies == 0) break BATTLE;
                }
                Thread.sleep(500);
            }
            for (int enemyIndex = 0; enemyIndex < numberOfEnemies; enemyIndex++) {
                currentEnemy = this.enemiesGroup.get(enemyIndex);
                isReady = currentEnemy.turn();
                if (!isReady) continue;
                Messages.turnEnemyLog(turnCounter++);
                targetedHero = this.heroesParty.get(Dice.getRandom(numberOfHeroes - 1));
                currentEnemy.attackHero(targetedHero);
                if (!targetedHero.isAlive()){
                    this.heroesParty.remove(targetedHero);
                    numberOfHeroes--;
                    Messages.infoLog();
                    Messages.heroesRemaining(heroesParty);
                    if (numberOfHeroes == 0) break BATTLE;
                }
                Thread.sleep(500);
            }

        }
    }
}
