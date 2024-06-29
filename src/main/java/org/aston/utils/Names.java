package org.aston.utils;

public class Names {
    private Names() {}

    public static final String[] WARRIOR_NAMES = {"Эльрик", "Кратос", "Конан", "Геральт", "Зигфрид",
            "Гатс", "Фенрис", "Аларик", "Лотар", "Брайанн", "Дзирт до Уорден", "Алистер", "Скволл Леонхарт"};
    public static final String[] MAGE_NAMES = {"Дамблдор", "Гэндальф", "Рейстлин", "Орион", "Андерс",
            "Виви Орнитир", "Гесер", "Мерлин", "Гарри Поттер"};
    public static final String[] ARCHER_NAMES = {"Леголас", "Ван Хельсинг", "Гэррет", "Робин Гуд", "Тауриэль",
            "Линк", "Зеленая стрела", "Рианнон"};

    public static String getWarriorName() {
        return WARRIOR_NAMES[Dice.getRandom(WARRIOR_NAMES.length)];
    }
    public static String getMageName() {
        return MAGE_NAMES[Dice.getRandom(MAGE_NAMES.length)];
    }
    public static String getArcherName() {
        return ARCHER_NAMES[Dice.getRandom(ARCHER_NAMES.length)];
    }

}
