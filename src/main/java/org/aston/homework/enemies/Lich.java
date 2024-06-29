package org.aston.homework.enemies;

import org.aston.homework.heroes.Hero;
import org.aston.homework.utils.*;


public class Lich extends Enemy {
    private final static int HEALTH_MAX_SIZE = 100;
    private final static int DAMAGE_SIZE = 50;
    private final static int GAUGE_TURN_RATIO = 40;
    private final static int SPECIAL_ABILITY_THRESHOLD = 19;
    private final static int EVADE_THRESHOLD = 20;

    public Lich() {
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
            hero.takeDamage(Integer.MAX_VALUE, DamageType.MAGICAL);
            return;
        }
        Messages.attackByEnemy(this);
        if (hero.isEvade()) {
            Messages.evadeAttack(hero);
            return;
        }
        hero.takeDamage(DAMAGE_SIZE, DamageType.MAGICAL);
    }

    @Override
    public String toString() {
        return "Лич";
    }

    @Override
    public boolean isEvade() {
        return Dice.getChance(EVADE_THRESHOLD);
    }
}
