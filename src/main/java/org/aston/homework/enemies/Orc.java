package org.aston.homework.enemies;

import org.aston.homework.heroes.Hero;
import org.aston.homework.utils.*;


public class Orc extends Enemy {
    private final static int HEALTH_MAX_SIZE = 75;
    private final static int DAMAGE_SIZE = 30;
    private final static int GAUGE_TURN_RATIO = 45;
    private final static int EVADE_THRESHOLD = 18;

    public Orc() {
        super(HEALTH_MAX_SIZE);
    }

    @Override
    public boolean turn() {
        if (this.isGaugeFull()) {
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
        hero.takeDamage(DAMAGE_SIZE, DamageType.PHYSICAL);
    }

    @Override
    public String toString() {
        return "Орк";
    }

    @Override
    public boolean isEvade() {
        return Dice.getChance(EVADE_THRESHOLD);
    }
}
