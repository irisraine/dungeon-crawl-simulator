package org.aston.homework.enemies;

import org.aston.homework.heroes.Hero;
import org.aston.homework.utils.*;


public class Zombie extends Enemy {
    private final static int HEALTH_MAX_SIZE = 40;
    private final static int DAMAGE_SIZE = 25;
    private final static int GAUGE_TURN_RATIO = 35;
    private final static int SPECIAL_ABILITY_THRESHOLD = 15;
    private final static int EVADE_THRESHOLD = 20;

    public Zombie() {
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
        hero.takeDamage(DAMAGE_SIZE, DamageType.PHYSICAL);
    }

    @Override
    public void takeDamage(int damage, DamageType damageType) {
        super.takeDamage(damage, damageType);
        if (!this.isAlive() && isUsingSpecialAbility) {
            this.heal(HEALTH_MAX_SIZE);
            Messages.infoLog();
            Messages.specialAbility(this);
        }
    }

    @Override
    public String toString() {
        return "Зомби";
    }

    @Override
    public boolean isEvade() {
        return Dice.getChance(EVADE_THRESHOLD);
    }
}
