package Model;

import Service.Battle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class VampireTest {
    @Test
    @DisplayName("Smoke show for Vampire")
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

        var myArmy = new Army()
                .addUnits(Unit.UnitType.DEFENDER, 2)
                .addUnits(Unit.UnitType.VAMPIRE, 2)
                .addUnits(Unit.UnitType.WARRIOR, 1);
        var enemyArmy = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 2)
                .addUnits(Unit.UnitType.DEFENDER, 2)
                .addUnits(Unit.UnitType.VAMPIRE, 3);
        var army3 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.DEFENDER, 4);
        var army4 = new Army()
                .addUnits(Unit.UnitType.VAMPIRE, 3)
                .addUnits(Unit.UnitType.WARRIOR, 2);

        //WHEN + THEN
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
                () -> Assertions.assertFalse(Battle.fight(myArmy, enemyArmy)),
                () -> Assertions.assertTrue(Battle.fight(army3, army4))


        );

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
        var expectedHealth = unit1.getInitial_Health();
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
                                .addUnits(Unit.UnitType.DEFENDER,4),
                        new Army()
                                .addUnits(Unit.UnitType.WARRIOR, 21)
                        , true)
        );
    }

    @Test
    @DisplayName("Given battle of two armies one with 10 Warriors, 5 Defenders, 10 Defenders and second" +
            "with 5 Warriors then first army wins")
    void GivenBattleOfTwoArmiesOneWith10Warriors15DefendersAndSecondWith5WarriorsThenFirstArmyWins() {
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
    @DisplayName("Given battle of two armies one with 5 Defenders, 6 Vampires, 7 Warriors and second army" +
            "with 6 Warriors, 6 Defenders, 6 Vampires then first army loses")
    void GivenBattleOfTwoArmiesOneWith5Defenders6Vampires7WarriorsAndSecondWith6Warriors6Defenders6VampiresThenFirstArmyLoses() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.DEFENDER, 5)
                .addUnits(Unit.UnitType.VAMPIRE, 6)
                .addUnits(Unit.UnitType.WARRIOR, 7);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 6)
                .addUnits(Unit.UnitType.DEFENDER, 6)
                .addUnits(Unit.UnitType.VAMPIRE, 6);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 2 Defenders 3 Vampires 4 Warriors and second army" +
            "with 4 Warriors 4 Defenders and 3 Vampires then first army loses")
    void GivenBattleOfTwoArmiesOneWith2Defenders3Vampires4WarriorsAndSecondWith4Warriors4Defenders3VampiresThenFirstArmyLoses() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.DEFENDER, 2)
                .addUnits(Unit.UnitType.VAMPIRE, 3)
                .addUnits(Unit.UnitType.WARRIOR, 4);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 4)
                .addUnits(Unit.UnitType.DEFENDER, 4)
                .addUnits(Unit.UnitType.VAMPIRE, 3);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 11 Defenders, 3 Vampires 4 Warriors and second army" +
            "with 4 Warriors 4 Defenders and 13 Vampires then first army wins")
    void GivenBattleOfTwoArmiesOneWith11Defenders3Vampires4WarriorsAndSecondWith4Warriors4Defenders13VampiresThenFirstArmyWins() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.DEFENDER, 11)
                .addUnits(Unit.UnitType.VAMPIRE, 3)
                .addUnits(Unit.UnitType.WARRIOR, 4);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 4)
                .addUnits(Unit.UnitType.DEFENDER, 4)
                .addUnits(Unit.UnitType.VAMPIRE, 13);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given battle of two armies one with 9 Defenders 3 Vampires 8 Warriors and second army" +
            "with 4 Warriors 4 Defenders 13 Vampires then first army wins")
    void GivenBAttleOfTwoArmiesOneWith9Defenders3Vampires8WarriorsAndSecondWith4Warriors4Defenders13VampiresThenFirstArmyWins() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.DEFENDER, 9)
                .addUnits(Unit.UnitType.VAMPIRE, 3)
                .addUnits(Unit.UnitType.WARRIOR, 8);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 4)
                .addUnits(Unit.UnitType.DEFENDER, 4)
                .addUnits(Unit.UnitType.VAMPIRE, 13);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given Vampire attacks Defender then Defenders looses 2 health")
    void GivenVampireAttacksDefenderThenDefenderLooses2Health() {
        //GIVEN
        var vampire = new Vampire();
        var defender = new Defender();
        var damageReduction = vampire.getAttack() - defender.getDefense();
        var expectedHealth = defender.getInitial_Health() - damageReduction;
        //WHEN
        vampire.attack(defender);
        //THEN
        Assertions.assertSame(expectedHealth, defender.getHealth());
    }

    @Test
    @DisplayName("Given Rookie attacks Defender then Defender looses 0 health")
    void GivenRookieAttacksDefenderThenDefenderLooses0Health() {
        //GIVEN
        var rookie = new Rookie();
        var defender = new Defender();
        var expectedHealth = defender.getInitial_Health();
        //WHEN
        rookie.attack(defender);
        //THEN
        Assertions.assertSame(expectedHealth, defender.getInitial_Health());
    }

    @Test
    @DisplayName("Given Vampire with full health attacks Warrior then Vampire' health is his initial health")
    void GivenVampireWithFullHealthAttacksWarriorThenVampiresHealthIsHisInitialHealth() {
        //GIVEN
        var vampire = new Vampire();
        var warrior = new Warrior();
        var expectedHealth = vampire.getInitial_Health();
        //WHEN
        vampire.attack(warrior);
        //THEN
        Assertions.assertSame(expectedHealth, vampire.getHealth());
    }

    @Test
    @DisplayName("Given Vampire with no full health attacks Warrior then Vampire heals for half of his damage")
    void GivenVampireWithNoFullHealthAttacksWarriorTheNVampireHealsForHalfOfHisDamage() {
        //GIVEN
        var vampire = new Vampire();
        var warrior = new Warrior();
        var healAmount = (vampire.getAttack() * vampire.getVAMPIRISM()) / 100;
        warrior.attack(vampire);
        //vampire health is 35
        //WHEN
        var healthBeforeAttack = vampire.getHealth();
        vampire.attack(warrior);
        var expectedHealth = healthBeforeAttack + healAmount;
        //THEN
        Assertions.assertSame(expectedHealth, vampire.getHealth());
    }

    @Test
    @DisplayName("Given Vampire with no full health attacks Defender then Vampire heals for half of damage done")
    void GivenVampireWithNoFullHealthAttacksDefenderThenVampireHealsForHalfOfDamageDone() {
        //GIVEN
        var vampire = new Vampire();
        var defender = new Defender();
        var healAmount = ((vampire.getAttack() - defender.getDefense()) * vampire.getVAMPIRISM()) / 100; //1
        defender.attack(vampire);
        //vampire health is 37
        //WHEN
        var healthBeforeAttack = vampire.getHealth();
        vampire.attack(defender);
        var expectedHealth = healthBeforeAttack + healAmount;
        //THEN
        Assertions.assertSame(expectedHealth, vampire.getHealth());
    }

    @Test
    @DisplayName("Given Knight attacks Defender then Defender loses correct amount of health")
    void GivenKnightAttacksDefenderThenDefenderLosesCorrectAmountOfHealth() {
        //GIVEN
        var knight = new Knight();
        var defender = new Defender();
        var actualDamage = knight.getAttack() - defender.getDefense();
        var expectedHealth = defender.getHealth() - actualDamage;
        //WHEN
        knight.attack(defender);
        //THEN
        Assertions.assertSame(expectedHealth, defender.getHealth());
    }


}
