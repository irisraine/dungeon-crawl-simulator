package org.aston.homework.enemies;

import org.aston.homework.heroes.Hero;
import org.aston.homework.utils.*;


public class Goblin extends Enemy{
    private final static int HEALTH_MAX_SIZE = 30;
    private final static int DAMAGE_SIZE = 15;
    private final static int GAUGE_TURN_RATIO = 75;
    private final static int SPECIAL_ABILITY_THRESHOLD = 15;
    private final static int EVADE_THRESHOLD = 12;

    public Goblin() {
        super(HEALTH_MAX_SIZE);
    }

    @Override
    public boolean turn() {
        if (this.isGaugeFull()) {
            isUsingSpecialAbility = Dice.getChance(SPECIAL_ABILITY_THRESHOLD);
            this.setGauge(0);
            return super.turn();
        } else {
            this.setGauge(GAUGE_TURN_RATIO);
            return false;
        }
    }

    @Override
    public void attackHero(Hero hero) {
        if (isUsingSpecialAbility) {
            Messages.specialAbility(this);
            hero.inflictStatus(Status.POISON);
            hero.takeDamage(DAMAGE_SIZE * 2, DamageType.PHYSICAL);
            return;
        }
        Messages.attackByEnemy(this);
        if (hero.isEvade()) {
            Messages.evadeAttack(hero);
            return;
        }
        hero.takeDamage(DAMAGE_SIZE, DamageType.PHYSICAL);
    }

    @Override
    public String toString() {
        return "Гоблин";
    }

    @Override
    public boolean isEvade() {
        return Dice.getChance(EVADE_THRESHOLD);
    }
}
