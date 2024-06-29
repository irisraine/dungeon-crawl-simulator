package org.aston.homework.heroes;

import org.aston.homework.enemies.Enemy;
import org.aston.homework.interfaces.*;
import org.aston.homework.utils.*;
import java.util.*;


public abstract class Hero implements Mortal, Evadable {
    private final String name;
    private int health;
    private int gaugeTurn;
    private HashMap<Status, Integer> statuses = new HashMap<>();
    boolean isUsingSpecialAbility;

    public Hero(String name, int health) {
        this.name = name;
        this.health = health;
        this.gaugeTurn = 100;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public abstract void attackEnemy(Enemy enemy);

    public boolean turn() {
        this.getStatusEffects();
        return true;
    };

    public void takeDamage(int damage, DamageType damageType) {
        this.health -= damage;
        Messages.damageInflicted(this.getName(), damage, damageType);
        if (this.isAlive())
            Messages.healthRemaining(this);
        else
            Messages.death(this);
    };

    public void inflictStatus(Status status) {
        statuses.put(status, 4);
    }

    public void getStatusEffects() {
        int changeHealthPerTurn = 15;

        for (Map.Entry<Status, Integer> status : this.statuses.entrySet()) {
            if (status.getValue() == 0) continue;
            statuses.put(status.getKey(), status.getValue() - 1);
            if (status.getValue() == 0) {
                Messages.statusLog();
                Messages.statusDispel(status.getKey(), this);
                continue;
            }
            if ((status.getKey() == Status.BLEEDING || status.getKey() == Status.POISON)) {
                Messages.statusLog();
                Messages.statusEffect(status.getKey(), this);
                int damageOnCurrentTurn = (status.getKey() == Status.BLEEDING) ? (changeHealthPerTurn - status.getValue() * 2) : changeHealthPerTurn;
                this.takeDamage(damageOnCurrentTurn, DamageType.STATUS_RELATED);
                if (!this.isAlive()) break;
            }
            else if (status.getKey() == Status.REGEN) {
                Messages.statusLog();
                Messages.statusEffect(status.getKey(), this);
                this.health += changeHealthPerTurn;
            }
        }
    }

    public void setGauge(int gaugeTurnRatio) {
        if (gaugeTurnRatio == 0)
            this.gaugeTurn = 0;
        else {
            this.gaugeTurn += gaugeTurnRatio;
            this.gaugeTurn = Math.min(this.gaugeTurn, 100);
        }
    }

    public boolean hasStatus(Status status) {
        return (statuses.containsKey(status) && statuses.get(status) > 0);
    }

    public boolean isGaugeFull() {
        return this.gaugeTurn == 100;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }
}
