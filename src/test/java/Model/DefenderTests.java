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

        //WHEN
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
    @DisplayName("Given battle between a Warrior and a Knight WHEN Warrior loses")
    void test01() {
        //GIVEN
        var carl = new Warrior();
        var jim = new Knight();
        logger.info("Warrior {} vs Knight {}", carl.getClass().getName(), jim.getClass().getName());
        //WHEN
        var result = Battle.fight(carl, jim);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given battle between a Knight and a Warrior WHEN Knight wins")
    void test02() {
        //GIVEN
        var ramon = new Knight();
        var slevin = new Warrior();
        //WHEN
        var result = Battle.fight(ramon, slevin);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle between a Warrior and a Warrior WHEN first Warrior to attack survives")
    void test03() {
        //GIVEN
        var bob = new Warrior();
        var mars = new Warrior();
        //WHEN
        Battle.fight(bob, mars);
        var result = bob.isAlive();
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle between a Knight and a Warrior WHEN Knight survives")
    void test04() {
        //GIVEN
        var zeus = new Knight();
        var godkiller = new Warrior();
        //WHEN
        Battle.fight(zeus, godkiller);
        var result = zeus.isAlive();
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle between a Warrior and a Warrior WHEN second to attack is dead")
    void test05() {
        //GIVEN
        var husband = new Warrior();
        var wife = new Warrior();
        //WHEN
        Battle.fight(husband, wife);
        var result = wife.isAlive();
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given battle between a Warrior and a Knight WHEN knight survives")
    void test06() {
        //GIVEN
        var dragon = new Warrior();
        var knight = new Knight();
        //WHEN
        Battle.fight(dragon, knight);
        var result = knight.isAlive();
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle between a Warrior and a Knight WHEN this Knight and new Warrior WHEN new Warrior wins")
    void test07() {
        //GIVEN
        var unit1 = new Warrior();
        var unit2 = new Knight();
        var unit3 = new Warrior();
        //WHEN
        Battle.fight(unit1, unit2);
        var result = Battle.fight(unit2, unit3);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given battle between a Defender and a Model.Rookie WHEN the Defender' health is 60")
    void test08() {
        //GIVEN
        var unit1 = new Defender();
        var unit2 = new Rookie();
        var expectedHealth = 60;
        //WHEN
        Battle.fight(unit1, unit2);
        var result = unit1.getHealth();
        //THEN
        Assertions.assertSame(expectedHealth, result);
    }

    @Test
    @DisplayName("Given battle between a Defender and a Model.Rookie WHEN a battle between the Model.Rookie and a new Warrior" +
            "WHEN Defender wins all")
    void test09() {
        //GIVEN
        var unit1 = new Defender();
        var unit2 = new Rookie();
        var unit3 = new Warrior();
        //WHEN
        Battle.fight(unit1, unit2);
        var result = Battle.fight(unit1, unit3);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 1 Warrior unit and second with 2 Warrior units WHEN" +
            " first army loses")
    void test10() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 2);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertFalse(result);

    }

    @Test
    @DisplayName("Given battle of two armies one wtih 2 Warrior units and second with 3 Warrior units" +
            "WHEN first army loses")
    void test11() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 2);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 3);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 5 Warrior units and second with 7 Warrior units" +
            "WHEN first army loses")
    void test12() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 5);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 7);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 20 Warrior units and second with 21 Warrior units" +
            "WHEN first army wins")
    void test13() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 20);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 21);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 10 Warrior units and second with 11 Warrior units" +
            "WHEN first army wins")
    void test14() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 10);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 11);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 11 Warrior units and second with 7 Warrior units" +
            "WHEN first army wins")
    void test15() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 11);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 7);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 5 Warriors, 4 Defenders, 5 Defenders and second " +
            "with 4 Warrior units WHEN first army wins")
    void test16() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 5)
                .addUnits(Unit.UnitType.DEFENDER, 4)
                .addUnits(Unit.UnitType.DEFENDER, 5);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 4);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 5 Defenders, 20 Warriors, 4 Defenders and second" +
            "with 21 Warriors WHEN first army wins")
    void test17() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.DEFENDER, 5)
                .addUnits(Unit.UnitType.WARRIOR, 20);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 21);

        army1.addUnits(Unit.UnitType.DEFENDER, 4);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 10 Warriors, 5 Defenders, 10 Defenders and second" +
            "with 5 Warriors WHEN first army wins")
    void test18() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 10)
                .addUnits(Unit.UnitType.DEFENDER, 5);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 5);

        army1.addUnits(Unit.UnitType.DEFENDER, 10);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 2 Defenders, 1 Warrior and 1 Defender and second" +
            "with 5 Warriors WHEN first army loses")
    void test19() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.DEFENDER, 2)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.DEFENDER, 1);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 5);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertFalse(result);
    }
    
}
