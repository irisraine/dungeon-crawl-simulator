package org.aston.utils;

public enum DamageType {
    PHYSICAL("физический"),
    MAGICAL("магический"),
    STATUS_RELATED("статусный");

    private final String name;

    DamageType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
