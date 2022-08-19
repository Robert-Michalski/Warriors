package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import service.Battle;

import java.util.stream.Stream;


class BattleTests {
    @Test
    @DisplayName("Smoke show")
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
        var eric = new Vampire();
        var adam = new Vampire();
        var richard = new Defender();
        var ogre = new Warrior();
        var freelancer = new Lancer();
        var vampire = new Vampire();
        var myArmy = new Army()
                .addUnits(Unit.UnitType.DEFENDER, 2)
                .addUnits(Unit.UnitType.HEALER, 1)
                .addUnits(Unit.UnitType.VAMPIRE, 2)
                .addUnits(Unit.UnitType.LANCER, 2)
                .addUnits(Unit.UnitType.HEALER, 1)
                .addUnits(Unit.UnitType.WARRIOR, 1);
        var enemyArmy = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 2)
                .addUnits(Unit.UnitType.LANCER, 4)
                .addUnits(Unit.UnitType.HEALER, 1)
                .addUnits(Unit.UnitType.DEFENDER, 2)
                .addUnits(Unit.UnitType.VAMPIRE, 3)
                .addUnits(Unit.UnitType.HEALER, 1);
        var army3 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.LANCER, 1)
                .addUnits(Unit.UnitType.HEALER, 1)
                .addUnits(Unit.UnitType.DEFENDER, 2)
                .lineUp();
        var army4 = new Army()
                .addUnits(Unit.UnitType.VAMPIRE, 3)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.HEALER, 1)
                .addUnits(Unit.UnitType.LANCER, 2)
                .lineUp();
        var army5 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 10);
        var army6 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 6)
                .addUnits(Unit.UnitType.LANCER, 5);
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
                () -> Assertions.assertFalse(Battle.fight(eric, richard)),
                () -> Assertions.assertTrue(Battle.fight(ogre, adam)),
                () -> Assertions.assertTrue(Battle.fight(freelancer, vampire)),
                () -> Assertions.assertTrue(freelancer.isAlive()),
                () -> Assertions.assertFalse(Battle.fight(myArmy, enemyArmy)),
                () -> Assertions.assertTrue(Battle.fight(army3, army4)),
                () -> Assertions.assertFalse(Battle.straightFight(army5, army6))
        );

    }



    @Test
    @DisplayName("Given Lancer hits Warrior then Warrior looses correct amount of health")
    void GivenLancerHitsWarriorThenWarriorLoosesCorrectAmountOfHealth() {
        //GIVEN
        var lancer = new Lancer();
        var warrior = new Warrior();
        var expectedHealth = warrior.getHealth() - lancer.getAttack();
        //WHEN
        lancer.hit(warrior);
        //THEN
        Assertions.assertSame(expectedHealth, warrior.getHealth());
    }

    @Test
    @DisplayName("Given Lancer hits Defender then Defender looses correct amount of health")
    void GivenLancerHitsDefenderThenDefenderLoosesCorrectAmountOfHealth() {
        //GIVEN
        var lancer = new Lancer();
        var defender = new Defender();
        var expectedHealth = defender.getHealth() - (lancer.getAttack() - defender.getDefense());
        //WHEN
        lancer.hit(defender);
        //THEN
        Assertions.assertSame(expectedHealth, defender.getHealth());
    }

    @Test
    @DisplayName("Given Army with 1 Lancer fight Army with 2 Warriors both of them loose health")
    void GivenFightOfArmyWith1LancerAndArmyWith2WarriorsThenBothWarriorsLooseHealth() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.LANCER, 1);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 2)
                .lineUp();
        //WHEN
        Battle.fight(army1.getTroops().get(0), army2
                .getTroops().get(0));
        //THEN
        System.out.println(army2.getTroops());
        Assertions.assertNotSame(50, army2.getTroops().get(1).getHealth());
    }

    @Test
    @DisplayName("Given battle between a Warrior and a Knight then Warrior should lose")
    void GivenBattleBetweenWarriorAndKnightThenWarriorLoses() {
        //GIVEN
        var warrior = new Warrior();
        var knight = new Knight();
        //WHEN
        var result = Battle.fight(warrior, knight);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given battle between a Knight and a Warrior then Knight wins")
    void GivenBattleBetweenKnightAndWarriorThenKnightWins() {
        //GIVEN
        var ramon = new Knight();
        var slevin = new Warrior();
        //WHEN
        var result = Battle.fight(ramon, slevin);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle between a Warrior and a Warrior then first Warrior to attack survives")
    void GivenBattleBetweenWarriorAndWarriorThenFirstToAttackSurvives() {
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
    @DisplayName("Given battle between a Knight and a Warrior then Knight survives")
    void GivenBattleBetweenKnightAndWarriorThenKnightSurvives() {
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
    @DisplayName("Given battle between a Warrior and a Warrior then second to attack is dead")
    void GivenBattleBetweenWarriorAndWarriorThenSecondToAttackIsDead() {
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
    @DisplayName("Given battle between a Warrior and a Knight then knight survives")
    void GivenBattleBetweenWarriorAndKnightThenKnightSurvives() {
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
    @DisplayName("Given battle between a Warrior and a Knight then this Knight and new Warrior then new Warrior wins")
    void GivenBattleBetweenWarriorAndKnightThenBattleBetweenThisKnightAndNewWarriorThenNewWarriorWins() {
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
    @DisplayName("Given battle between a Defender and a Rookie then the Defender' health is his initial health")
    void GivenBattleBetweenDefenderAndRookieThenDefendersHealthIsHisInitialHealth() {
        //GIVEN
        var unit1 = new Defender();
        var unit2 = new Rookie();
        var expectedHealth = unit1.getInitialHealth();
        //WHEN
        Battle.fight(unit1, unit2);
        var result = unit1.getHealth();
        //THEN
        Assertions.assertSame(expectedHealth, result);
    }

    @Test
    @DisplayName("Given battle between a Defender and a Rookie then a battle between the Defender and a new Warrior" +
            "then Defender wins all")
    void GivenBattleBetweenDefenderAndRookieThenBattleBetweenDefenderAndNewWarriorThenDefenderWinsAll() {
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

    @ParameterizedTest
    @MethodSource
    @DisplayName("Given straight battle of two armies check if expected output of straight fight matches real output")
    void straightFightProvider(Army army1, Army army2, boolean expected) {
        Assertions.assertSame(Battle.straightFight(army1, army2), expected);
    }

    private static Stream<Arguments> straightFightProvider() {
        return Stream.of(
                Arguments.of(new Army()
                                .addUnits(Unit.UnitType.LANCER, 7)
                                .addUnits(Unit.UnitType.VAMPIRE, 3)
                                .addUnits(Unit.UnitType.WARRIOR, 4)
                                .addUnits(Unit.UnitType.DEFENDER, 2),
                        new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 4)
                                .addUnits(Unit.UnitType.DEFENDER, 4)
                                .addUnits(Unit.UnitType.VAMPIRE, 6)
                                .addUnits(Unit.UnitType.LANCER, 4),
                        true),
                Arguments.of(new Army()
                                .addUnits(Unit.UnitType.LANCER, 7)
                                .addUnits(Unit.UnitType.VAMPIRE, 3)
                                .addUnits(Unit.UnitType.HEALER, 1)
                                .addUnits(Unit.UnitType.WARRIOR, 4)
                                .addUnits(Unit.UnitType.HEALER, 1)
                                .addUnits(Unit.UnitType.DEFENDER, 2),
                        new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 4)
                                .addUnits(Unit.UnitType.DEFENDER, 4)
                                .addUnits(Unit.UnitType.HEALER, 1)
                                .addUnits(Unit.UnitType.VAMPIRE, 6)
                                .addUnits(Unit.UnitType.LANCER, 4),
                        false),
                Arguments.of(new Army()
                                .addUnits(Unit.UnitType.LANCER, 4)
                                .addUnits(Unit.UnitType.WARRIOR, 3)
                                .addUnits(Unit.UnitType.HEALER, 1)
                                .addUnits(Unit.UnitType.WARRIOR, 4)
                                .addUnits(Unit.UnitType.HEALER, 1)
                                .addUnits(Unit.UnitType.KNIGHT, 2),
                        new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 4)
                                .addUnits(Unit.UnitType.DEFENDER, 4)
                                .addUnits(Unit.UnitType.HEALER, 1)
                                .addUnits(Unit.UnitType.VAMPIRE, 2)
                                .addUnits(Unit.UnitType.LANCER, 4),
                        true)
        );

    }


    @ParameterizedTest
    @MethodSource
    @DisplayName("Given battle of two armies check if expected output matches real output")
    void provideWarriorsForArmies(Army army1, Army army2, boolean expected) {
        Assertions.assertSame(Battle.fight(army1, army2), expected);
    }

    private static Stream<Arguments> provideWarriorsForArmies() {
        return Stream.of(
                Arguments.of(new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 1),
                        new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 2)
                        , false),
                Arguments.of(new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 2),
                        new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 3)
                        , false),
                Arguments.of(new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 5),
                        new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 7),
                        false),
                Arguments.of(new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 20),
                        new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 21)
                        , true),
                Arguments.of(new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 10),
                        new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 11)
                        , true),
                Arguments.of(new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 11),
                        new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 7)
                        , true),
                Arguments.of(new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 5)
                                .addUnits(Unit.UnitType.DEFENDER, 4)
                                .addUnits(Unit.UnitType.DEFENDER, 5),
                        new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 4)
                        , true),
                Arguments.of(new Army()
                                .addUnits(Unit.UnitType.DEFENDER, 5)
                                .addUnits(Unit.UnitType.WARRIOR, 20)
                                .addUnits(Unit.UnitType.DEFENDER, 4),
                        new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 21)
                        , true),
                Arguments.of(new Army()
                                .addUnits(Unit.UnitType.LANCER, 5)
                                .addUnits(Unit.UnitType.VAMPIRE, 3)
                                .addUnits(Unit.UnitType.WARRIOR, 4)
                                .addUnits(Unit.UnitType.DEFENDER, 2),
                        new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 4)
                                .addUnits(Unit.UnitType.DEFENDER, 4)
                                .addUnits(Unit.UnitType.VAMPIRE, 6)
                                .addUnits(Unit.UnitType.LANCER, 5)
                        , false),
                Arguments.of(new Army()
                                .addUnits(Unit.UnitType.LANCER, 7)
                                .addUnits(Unit.UnitType.VAMPIRE, 3)
                                .addUnits(Unit.UnitType.WARRIOR, 4)
                                .addUnits(Unit.UnitType.DEFENDER, 2),
                        new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 4)
                                .addUnits(Unit.UnitType.DEFENDER, 4)
                                .addUnits(Unit.UnitType.VAMPIRE, 6)
                                .addUnits(Unit.UnitType.LANCER, 4)
                        , true),
                Arguments.of(new Army()
                                .addUnits(Unit.UnitType.LANCER, 7)
                                .addUnits(Unit.UnitType.VAMPIRE, 3)
                                .addUnits(Unit.UnitType.WARRIOR, 4)
                                .addUnits(Unit.UnitType.DEFENDER, 2),
                        new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 4)
                                .addUnits(Unit.UnitType.DEFENDER, 4)
                                .addUnits(Unit.UnitType.VAMPIRE, 6)
                                .addUnits(Unit.UnitType.LANCER, 4)
                        , true)
        );
    }

    @Test
    @DisplayName("Given Straight Fight Between 2 Armies Each Having 1 Warrior Then First Army Wins")
    void GivenStraightFightBetween2ArmiesEachHaving1WarriorThenFirstArmyWins() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1);
        //WHEN
        var result = Battle.straightFight(army1, army2);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given straight fight between two armies one having 1 warrior second having 2 warriors then first army looses")
    void GivenStraightFightBetweenTwoArmiesOneHaving1WarriorSecondHaving2WarriorsThenFirstArmyLooses() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 2);
        //WHEN
        var result = Battle.straightFight(army1, army2);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given straight fight between two armies one having warrior second having" +
            "warrior and healer then first army wins")
    void GivenStraightFightBetweenTwoArmiesOneHavingWarriorSecondHavingWarriorAndHealerThenFirstArmyWins() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.HEALER, 1);
        //WHEN
        var result = Battle.straightFight(army1, army2);
        //THEN
        Assertions.assertTrue(result);
    }


    @Test
    @DisplayName("10. Fight: ")
    void test41() {
        //GIVEN
        var unit1 = new Defender();
        var unit2 = new Warlord();
        //WHEN
        var result = Battle.fight(unit1, unit2);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("11. Fight: ")
    void test42() {
        //GIVEN
        var unit1 = new Warlord();
        var unit2 = new Vampire();
        //WHEN
        var result = Battle.fight(unit1, unit2);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("12. Fight: ")
    void test43() {
        //GIVEN
        var unit1 = new Warlord();
        var unit2 = new Knight();
        //WHEN
        var result = Battle.fight(unit1, unit2);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("23. Battle: ")
    void test44() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARLORD, 1)
                .addUnits(Unit.UnitType.WARRIOR, 2)
                .addUnits(Unit.UnitType.LANCER, 2)
                .addUnits(Unit.UnitType.HEALER, 2)
                .lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARLORD, 1)
                .addUnits(Unit.UnitType.VAMPIRE, 1)
                .addUnits(Unit.UnitType.HEALER, 2)
                .addUnits(Unit.UnitType.KNIGHT, 2)
                .lineUp();
        //WHEN
        army1.processStrategy();
        army2.processStrategy();
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("24. Battle: ")
    void test45() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 2)
                .addUnits(Unit.UnitType.LANCER, 2)
                .addUnits(Unit.UnitType.DEFENDER, 1)
                .addUnits(Unit.UnitType.WARLORD, 3)
                .lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARLORD, 2)
                .addUnits(Unit.UnitType.VAMPIRE, 1)
                .addUnits(Unit.UnitType.HEALER, 5)
                .addUnits(Unit.UnitType.KNIGHT, 2)
                .lineUp();
        army1.processStrategy();
        army2.processStrategy();
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("25. Battle: ")
    void test46() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 2)
                .addUnits(Unit.UnitType.LANCER, 3)
                .addUnits(Unit.UnitType.DEFENDER, 1)
                .addUnits(Unit.UnitType.WARLORD, 4)
                .lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARLORD, 1)
                .addUnits(Unit.UnitType.VAMPIRE, 1)
                .addUnits(Unit.UnitType.ROOKIE, 1)
                .addUnits(Unit.UnitType.KNIGHT, 1)
                .lineUp();
        army1.equipWarriorAtPosition(0, Weapon.newSword());
        army2.equipWarriorAtPosition(0, Weapon.newShield());
        army1.processStrategy();
        army2.processStrategy();
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("26. Battle: ")
    void test47() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 2)
                .addUnits(Unit.UnitType.LANCER, 3)
                .addUnits(Unit.UnitType.DEFENDER, 1)
                .addUnits(Unit.UnitType.WARLORD, 1);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARLORD, 5)
                .addUnits(Unit.UnitType.VAMPIRE, 1)
                .addUnits(Unit.UnitType.ROOKIE, 1)
                .addUnits(Unit.UnitType.KNIGHT, 1);
        army1.equipWarriorAtPosition(0, Weapon.newSword());
        army2.equipWarriorAtPosition(0, Weapon.newShield());
        army1.processStrategy();
        army2.processStrategy();
        //WHEN
        var result = Battle.straightFight(army1, army2);
        //THEN
        Assertions.assertFalse(result);
    }


}

