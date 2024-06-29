package org.aston.enemies;

import org.aston.heroes.Hero;
import org.aston.utils.*;


public class Drow extends Enemy {
    private final static int HEALTH_MAX_SIZE = 50;
    private final static int DAMAGE_SIZE = 35;
    private final static int GAUGE_TURN_RATIO = 65;
    private final static int SPECIAL_ABILITY_THRESHOLD = 15;
    private final static int EVADE_THRESHOLD = 13;

    public Drow() {
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
        Messages.attackByEnemy(this);
        if (hero.isEvade()) {
            Messages.evadeAttack(hero);
            return;
        }
        if (isUsingSpecialAbility) {
            Messages.specialAbility(this);
            hero.inflictStatus(Status.BLEEDING);
            hero.takeDamage(DAMAGE_SIZE * 3 / 2, DamageType.PHYSICAL);
            return;
        }
        hero.takeDamage(DAMAGE_SIZE, DamageType.PHYSICAL);
    }

    @Override
    public String toString() {
        return "Дроу";
    }

    @Override
    public boolean isEvade() {
        return Dice.getChance(EVADE_THRESHOLD);
    }
}
