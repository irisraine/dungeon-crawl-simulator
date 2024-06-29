package org.aston.homework.heroes;

import org.aston.homework.enemies.Enemy;
import org.aston.homework.utils.*;


public class Archer extends Hero{
    private final static int HEALTH_MAX_SIZE = 150;
    private final static int ARMOR_VALUE = 15;
    private final static int DAMAGE_SIZE = 20;
    private final static int GAUGE_TURN_RATIO = 75;
    private final static int SPECIAL_ABILITY_THRESHOLD = 10;
    private final static int EVADE_THRESHOLD = 15;

    public Archer(String name) {
        super(name, HEALTH_MAX_SIZE);
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
    public void takeDamage(int damage, DamageType damageType) {
        if (damageType == DamageType.PHYSICAL)
            damage = (int) (damage * ((100 - ARMOR_VALUE) / 100.0));
        super.takeDamage(damage, damageType);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        if (isUsingSpecialAbility) {
            Messages.specialAbility(this);
            enemy.takeDamage(DAMAGE_SIZE * 3, DamageType.MAGICAL);
            return;
        }
        Messages.attackByHero(this);
        if (enemy.isEvade()) {
            Messages.evadeAttack(enemy);
            return;
        }
        enemy.takeDamage(DAMAGE_SIZE, DamageType.PHYSICAL);
    }

    @Override
    public boolean isEvade() {
        return Dice.getChance(EVADE_THRESHOLD);
    }
}
