package org.aston.homework.enemies;

import org.aston.homework.heroes.Hero;
import org.aston.homework.utils.*;


public class Wraith extends Enemy{
    private final static int HEALTH_MAX_SIZE = 80;
    private final static int DAMAGE_SIZE = 10;
    private final static int GAUGE_TURN_RATIO = 35;

    public Wraith() {
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
        hero.takeDamage(DAMAGE_SIZE, DamageType.MAGICAL);
    }

    @Override
    public void takeDamage(int damage, DamageType damageType) {
        if (damageType != DamageType.MAGICAL) {
            Messages.evadeAttack(this);
            return;
        }
        super.takeDamage(damage, damageType);
    }

    @Override
    public String toString() {
        return "Призрак";
    }

    @Override
    public boolean isEvade() {
        return false;
    }
}
