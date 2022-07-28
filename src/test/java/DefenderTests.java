import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class Rookie extends Warrior {
    private static final int ATTACK = 1;

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    public String toString() {
        return "Rookie{" +
                "health=" + getHealth() +
                "attack=" + ATTACK +
                '}';
    }
}

public class DefenderTests {
    @Test
    @DisplayName("Smoke show test to check if code works")
    void smokeShow() {
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
                .addUnits("Defender", 1);

        var enemyArmy = new Army()
                .addUnits("Warrior", 2);

        var army3 = new Army()
                .addUnits("Warrior", 1)
                .addUnits("Defender", 1);

        var army4 = new Army()
                .addUnits("Warrior", 2);
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

    //class Rookie only for testing


    @Test
    @DisplayName("Given battle between a Warrior and a Knight then Warrior loses")
    void test01() {
        var carl = new Warrior();
        var jim = new Knight();
        var result = Battle.fight(carl, jim);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given battle between a Knight and a Warrior then Knight wins")
    void test02() {
        var ramon = new Knight();
        var slevin = new Warrior();
        var result = Battle.fight(ramon, slevin);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle between a Warrior and a Warrior then first Warrior to attack survives")
    void test03() {
        var bob = new Warrior();
        var mars = new Warrior();
        Battle.fight(bob, mars);
        var result = bob.isAlive();
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle between a Knight and a Warrior then Knight survives")
    void test04() {
        var zeus = new Knight();
        var godkiller = new Warrior();
        Battle.fight(zeus, godkiller);
        var result = zeus.isAlive();
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle between a Warrior and a Warrior then second to attack is dead")
    void test05() {
        var husband = new Warrior();
        var wife = new Warrior();
        Battle.fight(husband, wife);
        var result = wife.isAlive();
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given battle between a Warrior and a Knight then knight survives")
    void test06() {
        var dragon = new Warrior();
        var knight = new Knight();
        Battle.fight(dragon, knight);
        var result = knight.isAlive();
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle between a Warrior and a Knight then this Knight and new Warrior then new Warrior wins")
    void test07() {
        var unit1 = new Warrior();
        var unit2 = new Knight();
        var unit3 = new Warrior();
        Battle.fight(unit1, unit2);
        var result = Battle.fight(unit2, unit3);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given battle between a Defender and a Rookie then the Defender' health is 60")
    void test08() {
        var unit1 = new Defender();
        var unit2 = new Rookie();
        Battle.fight(unit1, unit2);
        var result = unit1.getHealth();
        var expectedHealth = 60;
        Assertions.assertSame(expectedHealth, result);
    }

    @Test
    @DisplayName("Given battle between a Defender and a Rookie then a battle between the Rookie and a new Warrior" +
            "then Defender wins all")
    void test09() {
        var unit1 = new Defender();
        var unit2 = new Rookie();
        var unit3 = new Warrior();
        Battle.fight(unit1, unit2);
        var result = Battle.fight(unit1, unit3);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 1 Warrior unit and second with 2 Warrior units then" +
            " first army loses")
    void test10() {
        var army1 = new Army()
                .addUnits("Warrior", 1);
        var army2 = new Army()
                .addUnits("Warrior", 2);
        var result = Battle.fight(army1, army2);
        Assertions.assertFalse(result);

    }

    @Test
    @DisplayName("Given battle of two armies one wtih 2 Warrior units and second with 3 Warrior units" +
            "then first army loses")
    void test11() {
        var army1 = new Army()
                .addUnits("Warrior", 2);
        var army2 = new Army()
                .addUnits("Warrior", 3);
        var result = Battle.fight(army1, army2);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 5 Warrior units and second with 7 Warrior units" +
            "then first army loses")
    void test12() {
        var army1 = new Army()
                .addUnits("Warrior", 5);
        var army2 = new Army()
                .addUnits("Warrior", 7);
        var result = Battle.fight(army1, army2);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 20 Warrior units and second with 21 Warrior units" +
            "then first army wins")
    void test13() {
        var army1 = new Army()
                .addUnits("Warrior", 20);
        var army2 = new Army()
                .addUnits("Warrior", 21);
        var result = Battle.fight(army1, army2);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 10 Warrior units and second with 11 Warrior units" +
            "then first army wins")
    void test14() {
        var army1 = new Army()
                .addUnits("Warrior", 10);
        var army2 = new Army()
                .addUnits("Warrior", 11);
        var result = Battle.fight(army1, army2);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 11 Warrior units and second with 7 Warrior units" +
            "then first army wins")
    void test15() {
        var army1 = new Army()
                .addUnits("Warrior", 11);
        var army2 = new Army()
                .addUnits("Warrior", 7);
        var result = Battle.fight(army1, army2);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 5 Warriors, 4 Defenders, 5 Defenders and second " +
            "with 4 Warrior units then first army wins")
    void test16() {
        var army1 = new Army()
                .addUnits("Warrior", 5)
                .addUnits("Defender", 4)
                .addUnits("Defender", 5);
        var army2 = new Army()
                .addUnits("Warrior", 4);
        var result = Battle.fight(army1, army2);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 5 Defenders, 20 Warriors, 4 Defenders and second" +
            "with 21 Warriors then first army wins")
    void test17() {
        var army1 = new Army()
                .addUnits("Defender", 5)
                .addUnits("Warrior", 20);
        var army2 = new Army()
                .addUnits("Warrior", 21);

        army1.addUnits("Defender", 4);

        var result = Battle.fight(army1, army2);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 10 Warriors, 5 Defenders, 10 Defenders and second" +
            "with 5 Warriors then first army wins")
    void test18() {
        var army1 = new Army()
                .addUnits("Warrior", 10)
                .addUnits("Defender", 5);
        var army2 = new Army()
                .addUnits("Warrior", 5);

        army1.addUnits("Defender", 10);

        var result = Battle.fight(army1, army2);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 2 Defenders, 1 Warrior and 1 Defender and second" +
            "with 5 Warriors then first army loses")
    void test19() {
        var army1 = new Army()
                .addUnits("Defender", 2)
                .addUnits("Warrior", 1)
                .addUnits("Defender", 1);
        var army2 = new Army()
                .addUnits("Warrior", 5);
        var result = Battle.fight(army1, army2);
        Assertions.assertFalse(result);
    }
}
