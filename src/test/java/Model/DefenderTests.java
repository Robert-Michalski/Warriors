package Model;

import Service.Battle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class Rookie extends Warrior {
    private static final int ATTACK = 1;

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    public String toString() {
        return "Model.Rookie{" +
                "health=" + getHealth() +
                "attack=" + ATTACK +
                '}';
    }
}

class DefenderTests {

    Logger logger = LoggerFactory.getLogger(DefenderTests.class);
    @Test
    @DisplayName("Smoke show test to check if code works")
    void smokeShow() {
        //GIVEN
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();
        var bob = new Defender();
        var mike = new Knight();
        var rog = new Warrior();
        var lancelot = new Defender();

        var myArmy = new Army()
                .addUnits(Unit.UnitType.DEFENDER, 1);

        var enemyArmy = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 2);

        var army3 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.DEFENDER, 1);

        var army4 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 2);

        //THEN
        Assertions.assertAll(
                () -> Assertions.assertTrue(Battle.fight(chuck, bruce)),
                () -> Assertions.assertFalse(Battle.fight(dave, carl)),
                () -> Assertions.assertTrue(chuck.isAlive()),
                () -> Assertions.assertFalse(bruce.isAlive()),
                () -> Assertions.assertTrue(carl.isAlive()),
                () -> Assertions.assertFalse(dave.isAlive()),
                () -> Assertions.assertFalse(Battle.fight(carl, mark)),
                () -> Assertions.assertFalse(carl.isAlive()),
                () -> Assertions.assertFalse(Battle.fight(bob, mike)),
                () -> Assertions.assertTrue(Battle.fight(lancelot, rog)),
                () -> Assertions.assertFalse(Battle.fight(myArmy, enemyArmy)),
                () -> Assertions.assertTrue(Battle.fight(army3, army4))
        );


    }

    //class Model.Rookie only for testing


    @Test
    @DisplayName("Given battle between a Warrior and a Knight then Warrior loses")
    void test01() {
        //GIVEN
        var carl = new Warrior();
        var jim = new Knight();
        logger.info("Warrior {} vs Knight {}", carl.getClass().getName(), jim.getClass().getName());
        //THEN
        var result = Battle.fight(carl, jim);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given battle between a Knight and a Warrior then Knight wins")
    void test02() {
        //GIVEN
        var ramon = new Knight();
        var slevin = new Warrior();
        //THEN
        var result = Battle.fight(ramon, slevin);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle between a Warrior and a Warrior then first Warrior to attack survives")
    void test03() {
        //GIVEN
        var bob = new Warrior();
        var mars = new Warrior();
        //THEN
        Battle.fight(bob, mars);
        var result = bob.isAlive();
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle between a Knight and a Warrior then Knight survives")
    void test04() {
        //GIVEN
        var zeus = new Knight();
        var godkiller = new Warrior();
        //THEN
        Battle.fight(zeus, godkiller);
        var result = zeus.isAlive();
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle between a Warrior and a Warrior then second to attack is dead")
    void test05() {
        //GIVEN
        var husband = new Warrior();
        var wife = new Warrior();
        //THEN
        Battle.fight(husband, wife);
        var result = wife.isAlive();
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given battle between a Warrior and a Knight then knight survives")
    void test06() {
        //GIVEN
        var dragon = new Warrior();
        var knight = new Knight();
        //THEN
        Battle.fight(dragon, knight);
        var result = knight.isAlive();
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle between a Warrior and a Knight then this Knight and new Warrior then new Warrior wins")
    void test07() {
        //GIVEN
        var unit1 = new Warrior();
        var unit2 = new Knight();
        var unit3 = new Warrior();
        //THEN
        Battle.fight(unit1, unit2);
        var result = Battle.fight(unit2, unit3);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given battle between a Defender and a Model.Rookie then the Defender' health is 60")
    void test08() {
        //GIVEN
        var unit1 = new Defender();
        var unit2 = new Rookie();
        var expectedHealth = 60;
        //THEN
        Battle.fight(unit1, unit2);
        var result = unit1.getHealth();
        Assertions.assertSame(expectedHealth, result);
    }

    @Test
    @DisplayName("Given battle between a Defender and a Model.Rookie then a battle between the Model.Rookie and a new Warrior" +
            "then Defender wins all")
    void test09() {
        //GIVEN
        var unit1 = new Defender();
        var unit2 = new Rookie();
        var unit3 = new Warrior();
        //THEN
        Battle.fight(unit1, unit2);
        var result = Battle.fight(unit1, unit3);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 1 Warrior unit and second with 2 Warrior units then" +
            " first army loses")
    void test10() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 2);
        //THEN
        var result = Battle.fight(army1, army2);
        Assertions.assertFalse(result);

    }

    @Test
    @DisplayName("Given battle of two armies one wtih 2 Warrior units and second with 3 Warrior units" +
            "then first army loses")
    void test11() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 2);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 3);
        //THEN
        var result = Battle.fight(army1, army2);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 5 Warrior units and second with 7 Warrior units" +
            "then first army loses")
    void test12() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 5);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 7);
        //THEN
        var result = Battle.fight(army1, army2);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 20 Warrior units and second with 21 Warrior units" +
            "then first army wins")
    void test13() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 20);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 21);
        //THEN
        var result = Battle.fight(army1, army2);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 10 Warrior units and second with 11 Warrior units" +
            "then first army wins")
    void test14() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 10);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 11);
        //THEN
        var result = Battle.fight(army1, army2);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 11 Warrior units and second with 7 Warrior units" +
            "then first army wins")
    void test15() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 11);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 7);
        //THEN
        var result = Battle.fight(army1, army2);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 5 Warriors, 4 Defenders, 5 Defenders and second " +
            "with 4 Warrior units then first army wins")
    void test16() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 5)
                .addUnits(Unit.UnitType.DEFENDER, 4)
                .addUnits(Unit.UnitType.DEFENDER, 5);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 4);
        //THEN
        var result = Battle.fight(army1, army2);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 5 Defenders, 20 Warriors, 4 Defenders and second" +
            "with 21 Warriors then first army wins")
    void test17() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.DEFENDER, 5)
                .addUnits(Unit.UnitType.WARRIOR, 20);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 21);

        army1.addUnits(Unit.UnitType.DEFENDER, 4);
        //THEN
        var result = Battle.fight(army1, army2);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 10 Warriors, 5 Defenders, 10 Defenders and second" +
            "with 5 Warriors then first army wins")
    void test18() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 10)
                .addUnits(Unit.UnitType.DEFENDER, 5);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 5);

        army1.addUnits(Unit.UnitType.DEFENDER, 10);
        //THEN
        var result = Battle.fight(army1, army2);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 2 Defenders, 1 Warrior and 1 Defender and second" +
            "with 5 Warriors then first army loses")
    void test19() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.DEFENDER, 2)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.DEFENDER, 1);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 5);
        //THEN
        var result = Battle.fight(army1, army2);
        Assertions.assertFalse(result);
    }
    
}
