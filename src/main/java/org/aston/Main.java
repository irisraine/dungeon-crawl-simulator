package org.aston;

import java.util.*;
import org.aston.heroes.Hero;
import org.aston.modes.*;
import org.aston.utils.*;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int gameMode;
        int difficultyLevel;
        boolean readyToNextDungeon;

        Messages.greeting();
        ArrayList<Hero> heroesParty = UnitGenerator.createParty();
        Messages.partyIsReady(heroesParty);
        Messages.selectGameMode();
        do {
            gameMode = scanner.nextInt();
        } while (gameMode != 1 && gameMode != 2);
        if (gameMode == 1) {
            TrainingGround trainingGround = new TrainingGround(heroesParty);
            trainingGround.main();
        }
        if (gameMode == 2) {
            Messages.selectDifficultyLevel();
            do {
                difficultyLevel = scanner.nextInt();
            } while (difficultyLevel <= 0 || difficultyLevel > 4);
            scanner.nextLine();
            Messages.battlegroundBegins();
            do {
                BattleGround battleGround = new BattleGround(heroesParty, difficultyLevel);
                battleGround.main();
                readyToNextDungeon = false;
                if (!heroesParty.isEmpty()) {
                    Messages.battlegroundProcced();
                    readyToNextDungeon = scanner.nextLine().equalsIgnoreCase("Y");
                }
            } while (readyToNextDungeon);
        }
    }
}