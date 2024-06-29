package org.aston.utils;

import org.aston.enemies.*;
import org.aston.heroes.*;

import java.util.ArrayList;

public class Messages {
    private Messages() {}

    public static void greeting() {
        System.out.println("Добро пожаловать в зловещие глубины подземелий! Приготовьтесь к захватывающему приключению, где каждый шаг может стать последним!");
    }

    public static void partyIsReady(ArrayList<Hero> heroesParty) {
        System.out.printf("Ваш отряд готов! Воин %s, маг %s и лучник %s объединили свои силы. Вперед, к славе и победе! \n",
                heroesParty.get(0).getName(), heroesParty.get(1).getName(), heroesParty.get(2).getName());
    }

    public static void selectGameMode() {
        System.out.println("""
                Выберите режим:
                   1. Тренировочная площадка - отточите свои навыки.
                   2. Начало приключения - спуститесь в подземелье и сразитесь с порождениями Тьмы.""");
    }

    public static void selectDifficultyLevel() {
        System.out.println("""
               Выберите уровень сложности:
                   1. Легкая прогулка
                   2. Путь воина
                   3. Проклятые тропы
                   4. Адское испытание""");
    }

    public static void battlegroundBegins() {
        System.out.println("Приключение начинается! Отважные герои спускаются во мрак подземелий...");
    }

    public static void battlegroundProcced() {
        System.out.print("Хотите продолжить приключение и исследовать следующую область подземелья? (Y/N) ");
    }

    public static void turnHeroLog(int turnCounter) {
        System.out.printf("[ХОД %d][ГЕРОЙ] :: ", turnCounter);
    }
    public static void turnEnemyLog(int turnCounter) {
        System.out.printf("[ХОД %d][ВРАГ] :: ", turnCounter);
    }
    public static void infoLog() {
        System.out.print("[ИНФО] :: ");
    }
    public static void statusLog() {
        System.out.print("[CТАТУСНОЕ ВОЗДЕЙСТВИЕ] :: ");
    }

    public static void enemiesRemaining(int numberOfEnemies) {
        if (numberOfEnemies == 0)
            System.out.println("Победа! Отряд героев уничтожил всех врагов, стоявших на пути!");
        else
            System.out.printf("Осталось еще %d врагов.\n", numberOfEnemies);
    }

    public static void heroesRemaining(ArrayList<Hero> heroesParty) {
        if (heroesParty.size() == 2)
            System.out.printf("В живых остались лишь %s и %s.\n", heroesParty.get(0).getName(), heroesParty.get(1).getName());
        else if (heroesParty.size() == 1)
            System.out.printf("В живых остался только %s.\n", heroesParty.get(0).getName());
        else
            System.out.println("Поражение. Отряд героев уничтожен.");
    }

    public static void damageInflicted(String name, int damage, DamageType damageType) {
        System.out.printf("%s получил %s урон в размере %d единиц здоровья. ", name, damageType, Math.min(damage, 999));
    }
    public static void healthRemaining(Hero hero) {
        System.out.printf("У него осталось %d единиц здоровья.\n", hero.getHealth());
    }
    public static void healthRemaining(Enemy enemy) {
        System.out.printf("У него осталось %d единиц здоровья.\n", enemy.getHealth());
    }
    public static void death(Enemy enemy) {
        if (enemy instanceof Dummy)
            System.out.printf("%s порван в клочья.\n", enemy.toString());
        else
            System.out.printf("%s уничтожен.\n", enemy.toString());
    }
    public static void death(Hero hero) {
        System.out.printf("%s героически погиб в бою.\n", hero.getName());
    }

    public static void evadeAttack(Enemy enemy) {
        if (enemy instanceof Wraith)
            System.out.printf("Промах! %s абсолютно неуязвим к любым физическим атакам!\n", enemy.toString());
        else
            System.out.printf("Промах! %s успешно уклонился от атаки героя.\n", enemy.toString());
    }
    public static void evadeAttack(Hero hero) {
        System.out.printf("Промах! %s оказался проворным и успешно увернулся от вражеской атаки.\n", hero.getName());
    }

    public static void attackByHero(Hero hero) {
        if (hero instanceof Warrior)
            System.out.printf("%s атакует врага, обрушивая на него всю тяжесть своего огромного двуручного меча. ", hero.getName());
        else if (hero instanceof Mage)
            System.out.printf("%s кастует атакующее заклинание, и с его пальцев срываются искрящиеся потоки смертоносной энергии, нацеленные на врага. ", hero.getName());
        else if (hero instanceof Archer)
            System.out.printf("%s натягивает тетиву лука, и выпущенная стрела устремляется прямиком во врага! ", hero.getName());
    }

    public static void attackByEnemy(Enemy enemy) {
        if (enemy instanceof Zombie)
            System.out.printf("%s издает глухой стон и набрасывается на героя, повинуясь воле сил зла, поддерживающих жизнь в его полусгнившей плоти. ", enemy.toString());
        else if (enemy instanceof Goblin)
            System.out.printf("%s хихикает и атакует героя стремительным и быстрым ударом короткого кинжала. ", enemy.toString());
        else if (enemy instanceof Lich)
            System.out.printf("%s читает зловещее заклинание из своего арсенала черной магии, и поток потусторонних гибельных сил устремляется к герою. ", enemy.toString());
        else if (enemy instanceof Drow)
            System.out.printf("%s молниеносно выхватывает два изящных черных клинка и атакует героя. ", enemy.toString());
        else if (enemy instanceof Orc)
            System.out.printf("%s с ревом бросается на героя, размахивая ржавым угловатым ятаганом. ", enemy.toString());
        else if (enemy instanceof Wraith)
            System.out.printf("%s пронзительно воет, медленно приближается к герою и касается его своей эфемерной плотью, высасывая жизненную энергию. ", enemy.toString());
        else if (enemy instanceof Dummy)
            System.out.printf("%s безвольно ожидает атаки героя. ", enemy.toString());
    }

    public static void finishTraining() {
        System.out.println("Все болванчики изорваны в клочья, изрезаны мечами, сожжены магическими заклинаниями и истыканы стрелами. Тренировка завершена!");
    }

    public static void specialAbility(Hero hero) {
        if (hero instanceof Warrior)
            System.out.printf("Используя свою огромную физическую силу, %s наносит целую серию мощнейших ударов мечом, способных разрубить врага на куски! ", hero.getName());
        else if (hero instanceof Mage)
            System.out.printf("%s использует заклинание поддержки - ", hero.getName());
        else if (hero instanceof Archer)
            System.out.printf("За считанные мгновения %s обрушивает на врага целый ливень из особых зачарованных стрел. ", hero.getName());

    }

    public static void specialAbility(Enemy enemy) {
        if (enemy instanceof Zombie)
            System.out.print("С ужасом герои становятся свидетелями того, как зомби воскресает после смерти. Он снова в строю! \n");
        else if (enemy instanceof Goblin)
            System.out.print("Гоблин метает в героя отравленный дротик! Герой чувствует, как страшный яд растекается по жилам. ");
        else if (enemy instanceof Lich)
            System.out.print("Лич зловеще шепчет слова своего самого страшного заклятья, и героя мгновенно пожирает сама первородная Тьма. ");
        else if (enemy instanceof Drow)
            System.out.print("Изощренный удар клинков дроу вызывает обильное кровотечение у героя. ");

    }

    public static void mageSupportSpells(Status status) {
        if (status == Status.SHIELD)
            System.out.println("магический щит. Вокруг героя формируется прозрачный переливающийся кокон, защищающий его от атак. ");
        else if (status == Status.HASTE)
            System.out.println("ускорение. Герой чувствует, как обострились его рефлексы. Подготовка к очередной атаке теперь занимает у него вдвое меньше времени. ");
        else if (status == Status.REGEN)
            System.out.println("регенерация. Исцеляющие энергии будут постепенно восстанавливать здоровье героя. ");
    }

    public static void statusEffect(Status status, Hero hero) {
        if (status == Status.BLEEDING)
            System.out.printf("Кровотечение у героя. Нанесенные раны продолжают кровоточить, и %s чувствует слабость. ", hero.getName());
        else if (status == Status.POISON)
            System.out.printf("Отравление у героя. %s чувствует, как страшный яд словно разъедает его тело изнутри. ", hero.getName());
        else if (status == Status.SHIELD)
            System.out.printf("Магический щит вспыхивает ярким светом, отражая половину наносимого %s урона. ", hero.getName());
        else if (status == Status.REGEN)
            System.out.printf("%s ощущает, как потоки светлых магических энергий исцеляют его раны. Теперь у него %d единиц здоровья. \n", hero.getName(), hero.getHealth());
    }

    public static void statusDispel(Status status, Hero hero) {
        if (status == Status.BLEEDING)
            System.out.printf("Кровотечение у %s прекратилось. \n", hero.getName());
        else if (status == Status.POISON)
            System.out.printf("Организм %s очистился от яда. \n", hero.getName());
        else if (status == Status.SHIELD)
            System.out.printf("Магический щит рассеялся, и больше не защищает %s. \n", hero.getName());
        else if (status == Status.HASTE)
            System.out.printf("Рефлексы %s вернулись к норме. \n", hero.getName());
        else if (status == Status.REGEN)
            System.out.printf("Действие регенерации на %s закончилось. \n", hero.getName());
    }
}
