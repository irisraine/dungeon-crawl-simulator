package org.aston.enemies;

import org.aston.heroes.Hero;
import org.aston.interfaces.*;
import org.aston.utils.*;

public abstract class Enemy implements Mortal, Evadable {
    private int health;
    private int gaugeTurn;
    boolean isUsingSpecialAbility;

    public abstract void attackHero(Hero hero);

    public Enemy(int health) {
        this.health = health;
        this.gaugeTurn = 100;
    }

    public int getHealth() {
        return health;
    }

    public boolean turn() {
        return true;
    };

    public void takeDamage(int damage, DamageType damageType) {
        this.health -= damage;
        Messages.damageInflicted(this.toString(), damage, damageType);
        if (this.isAlive())
            Messages.healthRemaining(this);
        else
            Messages.death(this);
    };

    public void heal(int health) {
        this.health = health;
    }

    public void setGauge(int gaugeTurnRatio) {
        if (gaugeTurnRatio == 0)
            this.gaugeTurn = 0;
        else {
            this.gaugeTurn += gaugeTurnRatio;
            this.gaugeTurn = Math.min(this.gaugeTurn, 100);
        }
    }

    public boolean isGaugeFull() {
        return this.gaugeTurn == 100;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }
}
