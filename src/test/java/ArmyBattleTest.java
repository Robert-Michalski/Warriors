import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArmyBattleTest {
    @Test
    @DisplayName("Smoke show test to check if code works")
    void smokeShow() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();

        var myArmy = new Army();
        myArmy.addUnits("Knight", 3);

        var enemyArmy = new Army();
        enemyArmy.addUnits("Warrior", 3);

        var army3 = new Army();
        army3.addUnits("Warrior", 20);
        army3.addUnits("Knight", 5);

        var army4 = new Army();
        army4.addUnits("Warrior", 30);
        Assertions.assertAll(
                () -> Assertions.assertTrue(Battle.fight(chuck, bruce)),
                () -> Assertions.assertFalse(Battle.fight(dave, carl)),
                () -> Assertions.assertTrue(chuck.isAlive()),
                () -> Assertions.assertFalse(bruce.isAlive()),
                () -> Assertions.assertTrue(carl.isAlive()),
                () -> Assertions.assertFalse(dave.isAlive()),
                () -> Assertions.assertFalse(Battle.fight(carl, mark)),
                () -> Assertions.assertFalse(carl.isAlive()),
                () -> Assertions.assertTrue(Battle.fight(myArmy, enemyArmy)),
                () -> Assertions.assertFalse(Battle.fight(army3, army4))
        );

    }

    @Test
    @DisplayName("Creating warrior creates a warrior")
    void test02() {
        WarriorFactory warriorFactory = new WarriorFactory();
        Army army = new Army();
        army.addUnits("Warrior", 1);
        Assertions.assertAll(
                () -> Assertions.assertTrue(army.getArmy().get(0).getClass().getName() == "Warrior"),
                () -> Assertions.assertFalse(army.getArmy().get(0).getClass().getName() == "Knight")
        );
    }

    @Test
    @DisplayName("Creating knight creates a knight")
    void test03() {
        WarriorFactory warriorFactory = new WarriorFactory();
        Army army = new Army();
        army.addUnits("Knight", 1);
        Assertions.assertAll(
                () -> Assertions.assertFalse(army.getArmy().get(0).getClass().getName() == "Warrior"),
                () -> Assertions.assertTrue(army.getArmy().get(0).getClass().getName() == "Knight")
        );

    }

    @Test
    @DisplayName("Given two armies - one having a Warrior second having a Knight then army with Knight should win")
    void test04() {
        WarriorFactory warriorFactory = new WarriorFactory();
        Army myArmy = new Army();
        myArmy.addUnits("Warrior", 1);
        Army enemyArmy = new Army();
        enemyArmy.addUnits("Knight", 1);

        Assertions.assertFalse(Battle.fight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Given two armies - one with two warriors and second with one warriors then first army should win")
    void test05() {
        WarriorFactory warriorFactory = new WarriorFactory();
        Army myArmy = new Army();
        myArmy.addUnits("Warrior", 2);
        Army enemyArmy = new Army();
        enemyArmy.addUnits("Warrior", 1);

        Assertions.assertTrue(Battle.fight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Given two armies - one with one warrior and second with two warriors then second army should win")
    void test06() {
        WarriorFactory warriorFactory = new WarriorFactory();
        Army myArmy = new Army();
        myArmy.addUnits("Warrior", 1);
        Army enemyArmy = new Army();
        enemyArmy.addUnits("Warrior", 2);

        Assertions.assertFalse(Battle.fight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Given two armies - one with 2 warriors second with 3 warriors then first loses")
    void test07() {
        WarriorFactory warriorFactory = new WarriorFactory();
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits("Warrior", 2);
        army2.addUnits("Warrior", 3);
        Assertions.assertFalse(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Given two armies - one with 5 warriors second with 7 warriors then first loses")
    void test08() {
        WarriorFactory warriorFactory = new WarriorFactory();
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits("Warrior", 5);
        army2.addUnits("Warrior", 7);
        Assertions.assertFalse(Battle.fight(army1,army2));
    }

    @Test
    @DisplayName("Given two armies - one with 20 warriors second with 21 warriors then first wins")
    void test09() {
        WarriorFactory warriorFactory = new WarriorFactory();
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits("Warrior", 20);
        army2.addUnits("Warrior", 21);
        Assertions.assertTrue(Battle.fight(army1,army2));
    }
    @Test
    @DisplayName("Given two armies - one with 10 warriors second with 11 warriors then first wins")
    void test10() {
        WarriorFactory warriorFactory = new WarriorFactory();
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits("Warrior", 10);
        army2.addUnits("Warrior", 11);
        Assertions.assertTrue(Battle.fight(army1,army2));
    }
    @Test
    @DisplayName("Given two armies - one with 11 warriors second with 7 warriors then first wins")
    void test11() {
        WarriorFactory warriorFactory = new WarriorFactory();
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits("Warrior", 11);
        army2.addUnits("Warrior", 7);
        Assertions.assertTrue(Battle.fight(army1,army2));
    }

    @Test
    @DisplayName("Given two armies - one with 5 warriors second with 4 knights then first loses")
    void test12() {
        WarriorFactory warriorFactory = new WarriorFactory();
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits("Warrior", 5);
        army2.addUnits("Knight", 7);
        Assertions.assertFalse(Battle.fight(army1,army2));
    }

}
