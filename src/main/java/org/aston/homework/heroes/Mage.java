package org.aston.homework.heroes;

import org.aston.homework.enemies.Enemy;
import org.aston.homework.utils.*;


public class Mage extends Hero{
    private final static int HEALTH_MAX_SIZE = 200;
    private final static int ARMOR_VALUE = 10;
    private final static int DAMAGE_SIZE = 50;
    private final static int GAUGE_TURN_RATIO = 40;
    private final static int SPECIAL_ABILITY_THRESHOLD = 15;
    private final static int EVADE_THRESHOLD = 20;

    public Mage(String name) {
        super(name, HEALTH_MAX_SIZE);
    }

    @Override
    public boolean turn() {
        if (this.isGaugeFull()) {
            isUsingSpecialAbility = Dice.getChance(SPECIAL_ABILITY_THRESHOLD);
            this.setGauge(0);
            return super.turn();
        } else {
            if (this.hasStatus(Status.HASTE))
                this.setGauge(GAUGE_TURN_RATIO * 2);
            else
                this.setGauge(GAUGE_TURN_RATIO);
            return false;
        }
    }

    @Override
    public void takeDamage(int damage, DamageType damageType) {
        if (damageType == DamageType.PHYSICAL)
            damage = (int) (damage * ((100 - ARMOR_VALUE) / 100.0));
        if (this.hasStatus(Status.SHIELD) && damageType != DamageType.STATUS_RELATED){
            damage /= 2;
            Messages.statusEffect(Status.SHIELD, this);
        }
        super.takeDamage(damage, damageType);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        if (isUsingSpecialAbility) {
            this.castSupportSpell();
            return;
        }
        Messages.attackByHero(this);
        if (enemy.isEvade()) {
            Messages.evadeAttack(enemy);
            return;
        }
        enemy.takeDamage(DAMAGE_SIZE, DamageType.MAGICAL);
    }

    public void castSupportSpell() {
        int spellType = Dice.getRandom(3);
        Messages.specialAbility(this);
        switch (spellType) {
            case 0:
                if (!this.hasStatus(Status.HASTE)) {
                    Messages.mageSupportSpells(Status.HASTE);
                    this.inflictStatus(Status.HASTE);
                    break;
                }
            case 1:
                if (this.getHealth() < HEALTH_MAX_SIZE / 4 * 3) {
                    Messages.mageSupportSpells(Status.REGEN);
                    this.inflictStatus(Status.REGEN);
                    break;
                };
            case 2:
                Messages.mageSupportSpells(Status.SHIELD);
                this.inflictStatus(Status.SHIELD);
                break;
        }
    }

    @Override
    public boolean isEvade() {
        return Dice.getChance(EVADE_THRESHOLD);
    }
}
