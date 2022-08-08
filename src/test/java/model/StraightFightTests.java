package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import service.Battle;

import java.util.stream.Stream;

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

public class StraightFightTests {
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
    @DisplayName("Given battle of two armies check if expected output of straight fight matches real output")
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
        var expectedHealth = defender.getInitialHealth() - damageReduction;
        //WHEN
        vampire.hit(defender);
        //THEN
        Assertions.assertSame(expectedHealth, defender.getHealth());
    }

    @Test
    @DisplayName("Given Rookie attacks Defender then Defender looses 0 health")
    void GivenRookieAttacksDefenderThenDefenderLooses0Health() {
        //GIVEN
        var rookie = new Rookie();
        var defender = new Defender();
        var expectedHealth = defender.getInitialHealth();
        //WHEN
        rookie.hit(defender);
        //THEN
        Assertions.assertSame(expectedHealth, defender.getInitialHealth());
    }

    @Test
    @DisplayName("Given Vampire with full health attacks Warrior then Vampire' health is his initial health")
    void GivenVampireWithFullHealthAttacksWarriorThenVampiresHealthIsHisInitialHealth() {
        //GIVEN
        var vampire = new Vampire();
        var warrior = new Warrior();
        var expectedHealth = vampire.getInitialHealth();
        //WHEN
        vampire.hit(warrior);
        //THEN
        Assertions.assertSame(expectedHealth, vampire.getHealth());
    }

    @Test
    @DisplayName("Given Vampire with no full health attacks Warrior then Vampire heals for half of his damage")
    void GivenVampireWithNoFullHealthAttacksWarriorTheNVampireHealsForHalfOfHisDamage() {
        //GIVEN
        var vampire = new Vampire();
        var warrior = new Warrior();
        var healAmount = (vampire.getAttack() * vampire.getVampirism()) / 100;
        warrior.hit(vampire);
        //vampire health is 35
        //WHEN
        var healthBeforeAttack = vampire.getHealth();
        vampire.hit(warrior);
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
        var healAmount = ((vampire.getAttack() - defender.getDefense()) * vampire.getVampirism()) / 100; //1
        defender.hit(vampire);
        //vampire health is 37
        //WHEN
        var healthBeforeAttack = vampire.getHealth();
        vampire.hit(defender);
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
        knight.hit(defender);
        //THEN
        Assertions.assertSame(expectedHealth, defender.getHealth());
    }

    @Test
    @DisplayName("Given Vampire with 39 health when he hits warrior then vampire' health is no higher than his" +
            "initial health")
    void GivenVampireWithAlmostFullHealthWhenHeHitsWarriorThenVampiresHealthIsNoHigherThanHisInitialHealth() {
        //GIVEN
        var vampire = new Vampire();
        vampire.setHealth(39);
        var warrior = new Warrior();
        //WHEN
        vampire.hit(warrior);
        //THEN
        Assertions.assertSame(vampire.getInitialHealth(), vampire.getHealth());
    }

    @Test
    @DisplayName("Given a Vampire with 37 health when he hits a Warrior with 1 health then vampire's health is 39")
    void GivenVampireWith37HeathWhenHeHitsWarriorWith1HealthThenVampiresHealthIs39() {
        //GIVEN
        var vampire = new Vampire();
        vampire.setHealth(37);
        var healthBeforeAttack = vampire.getHealth();
        var warrior = new Warrior();
        warrior.setHealth(1);
        var amountToHeal = (vampire.getAttack() * vampire.getVampirism()) / 100;
        //WHEN
        vampire.hit(warrior);
        var expectedHealth = healthBeforeAttack + amountToHeal;
        //THEN
        Assertions.assertSame(expectedHealth, vampire.getHealth());
    }

    @Test
    @DisplayName("Given battle of two armies one with 2 warriors and second with 1 Lancer and 1 Warrior then first army loses")
    void GivenBattleOfTwoArmiesOneWith2WarriorsAndSecondWith1Lancer1WarriorThenFirstArmyLooses() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 2);
        var army2 = new Army()
                .addUnits(Unit.UnitType.LANCER, 1)
                .addUnits(Unit.UnitType.WARRIOR, 1);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given army with defender and warrior and army with lancer then warrior looses correct amount of health")
    void GivenLancerAttacksDefenderThatHasWarriorBehindHimThenThisWarriorLoosesCorrectAmountOfHealth() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.DEFENDER, 1)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.LANCER, 1);
        //WHEN
        army2.getTroops().get(0).hit(army1.getTroops().get(0));
        //THEN
        Assertions.assertSame(48, army1.getTroops().get(1).getHealth());
    }

    @Test
    @DisplayName("Given army of two defenders and army of 1 lancer when lancer hits first defender then second gets no damage")
    void GivenArmyOf2DefendersAndArmyOf1LancerThenWhenLancerHitsFirstDefenderSecondLoosesNoHealth() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.DEFENDER, 2)
                .lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.LANCER, 1);
        //WHEN
        army2.getTroops().get(0).hit(army1.getTroops().get(0));
        //THEN
        Assertions.assertSame(60, army1.getTroops().get(1).getHealth());
    }

    @Test
    @DisplayName("Given warrior with no full health is in front of healer, after attacking he (warrior) is healed")
    void GivenWarriorWithNoFullHealthInFrontOfHealerAttacksEnemyThisWarriorIsBeingHealedByHealerBehindHim() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.HEALER, 1)
                .lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1);
        army1.getTroops().get(0).setHealth(48);
        //WHEN
        army1.getTroops().get(0).hit(army2.getTroops().get(0));
        //THEN
        Assertions.assertSame(army1.getTroops().get(0).getInitialHealth(), army1.getTroops().get(0).getHealth());

    }

    @Test
    @DisplayName("Given duel between Healer and Warrior then warrior looses no health")
    void GivenDuelBetweenHealerAndWarriorThenWarriorLoosesNoHealth() {
        //GIVEN
        var healer = new Healer();
        var warrior = new Warrior();
        //WHEN
        Battle.fight(healer, warrior);
        //THEN
        Assertions.assertSame(warrior.getInitialHealth(), warrior.getHealth());
    }

    @Test
    @DisplayName("If warrior in front has full hp after attack his hp does not exceed his initial health")
    void GivenWarriorWithFullHealthHasHealerBehindHimThenAfterThisWarriorAttacksHisHealthIsBelowInitialHealth() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.HEALER, 1);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1);
        //WHEN
        army1.getTroops().get(0).hit(army2.getTroops().get(0));
        //THEN
        Assertions.assertSame(army1.getTroops().get(0).getInitialHealth(), army1.getTroops().get(0).getHealth());
    }

    @Test
    @DisplayName("Given Warrior with almost full health that has healer behind then after this Warriors hits then he is being" +
            "healed to initial health")
    void GivenWarriorWithAlmostFullHealthAttacksEnemyThenHeIsBeingHealerButNotOverInitialHealth() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.HEALER, 1)
                .lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1);
        army1.getTroops().get(0).setHealth(49);
        //WHEN
        army1.getTroops().get(0).hit(army2.getTroops().get(0));
        //THEN
        Assertions.assertSame(army1.getTroops().get(0).getInitialHealth(), army1.getTroops().get(0).getHealth());
    }

    @Test
    @DisplayName("Given warrior in front makes his move then everyone in front of any healer gets healed")
    void GivenWarriorInFrontOfArmyMakesHisMoveThenEveryoneInFrontOfAnyHealerGetsHealed() {
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.HEALER, 1)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.HEALER, 1)
                .lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .lineUp();
        army1.getTroops().get(0).setHealth(48);
        army1.getTroops().get(2).setHealth(48);
        //WHEN
        army1.getTroops().get(0).hit(army2.getTroops().get(0));
        //THEN
        Assertions.assertSame(army1.getTroops().get(0).getInitialHealth(), army1.getTroops().get(0).getHealth());
        Assertions.assertSame(army1.getTroops().get(2).getInitialHealth(), army1.getTroops().get(2).getHealth());
    }

    @Test
    @DisplayName("Given straight fight between armies of 2 warriors then first army wins")
    void test01() {
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1);
        var result = Battle.straightFight(army1, army2);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Given straight fight between two armies one having 1 warrior second having 2 warriors then second army wins")
    void test02() {
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 2);
        var result = Battle.straightFight(army1, army2);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given straight fight between two armies one having 1 lancer second having 2 warriors" +
            " then lancer attacks only one enemy")
    void test03() {
        var army1 = new Army()
                .addUnits(Unit.UnitType.LANCER, 1);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 2);
        var result = Battle.straightFight(army1, army2);
        Assertions.assertFalse(result);
        //TODO how to check it ?
    }

    @Test
    @DisplayName("Given straight fight between two armies one having warrior second having" +
            "warrior and heaelr then healer will not heal warrior so they will loose")
    void test04() {
        var army1 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.HEALER, 1);
        var result = Battle.straightFight(army1, army2);
        Assertions.assertTrue(result);
    }


    @Test
    @DisplayName("Given empty army vs army of two warriors then army 2 wins")
    void test06() {
        var army1 = new Army();
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 2);
        var result = Battle.straightFight(army1, army2);
        Assertions.assertFalse(result);
    }

    //    @Test
//    @DisplayName("Test from site")
//    void test08() {
//        var army1 = new Army()
//                .addUnits(Unit.UnitType.LANCER, 7)
//                .addUnits(Unit.UnitType.VAMPIRE, 3)
//                .addUnits(Unit.UnitType.HEALER, 1)
//                .addUnits(Unit.UnitType.WARRIOR, 4)
//                .addUnits(Unit.UnitType.HEALER, 1)
//                .addUnits(Unit.UnitType.DEFENDER, 2);
//        var army2 = new Army()
//                .addUnits(Unit.UnitType.WARRIOR, 4)
//                .addUnits(Unit.UnitType.DEFENDER, 4)
//                .addUnits(Unit.UnitType.HEALER, 1)
//                .addUnits(Unit.UnitType.VAMPIRE, 6)
//                .addUnits(Unit.UnitType.LANCER, 4);
//        var result = Battle.straightFight(army1, army2);
//        System.out.println("army1 "+army1.getTroops());
//        System.out.println("army2 "+army2.getTroops());
//        Assertions.assertFalse(result);
//    }
    @Test
    @DisplayName("Warrior equips a sword and has proper statistics")
    void test10() {
        var warrior = new Warrior();
        var sword = Weapon.newSword();
        var expectedHealth = warrior.getHealth() + sword.getHealth();
        var expectedAttack = warrior.getAttack() + sword.getAttack();
        warrior.equipWeapon(sword);
        Assertions.assertSame(expectedHealth, warrior.getHealth());
        Assertions.assertSame(expectedAttack, warrior.getAttack());
    }

    @Test
    @DisplayName("Defender equips a Shield and has proper statistics")
    void test11() {
        var defender = new Defender();
        var shield = Weapon.newShield();
        var expectedHealth = defender.getHealth() + shield.getHealth();
        var expectedAttack = defender.getAttack() + shield.getAttack();
        var expectedDefense = defender.getDefense() + shield.getDefense();
        defender.equipWeapon(shield);
        Assertions.assertAll(
                () -> Assertions.assertSame(expectedHealth, defender.getHealth()),
                () -> Assertions.assertSame(expectedAttack, defender.getAttack()),
                () -> Assertions.assertSame(expectedDefense, defender.getDefense())
        );
        //TODO fix log why he knows about defense = 4 before it should happen ?
    }

    @Test
    @DisplayName("Vampire equips GreatAxe and has proper statistics")
    void test12() {
        var vampire = new Vampire();
        var greatAxe = Weapon.newGreatAxe();
        var expectedHealth = vampire.getHealth() + greatAxe.getHealth();
        var expectedAttack = vampire.getAttack() + greatAxe.getAttack();
        var expectedVampirism = vampire.getVampirism() + greatAxe.getVampirism();
        vampire.equipWeapon(greatAxe);
        Assertions.assertAll(
                () -> Assertions.assertSame(expectedHealth, vampire.getHealth()),
                () -> Assertions.assertSame(expectedAttack, vampire.getAttack()),
                () -> Assertions.assertSame(expectedVampirism, vampire.getVampirism())
        );
    }

    @Test
    @DisplayName("Vampire equips Katana and has proper statistics")
    void test13() {
        var vampire = new Vampire();
        var katana = Weapon.newKatana();
        var expectedHealth = vampire.getHealth() + katana.getHealth();
        var expectedAttack = vampire.getAttack() + katana.getAttack();
        var expectedVampirism = vampire.getVampirism() + katana.getVampirism();
        vampire.equipWeapon(katana);
        Assertions.assertAll(
                () -> Assertions.assertSame(expectedHealth, vampire.getHealth()),
                () -> Assertions.assertSame(expectedAttack, vampire.getAttack()),
                () -> Assertions.assertSame(expectedVampirism, vampire.getVampirism())
        );
    }

    @Test
    @DisplayName("Defender equips Katana and has proper statistics")
    void test14() {
        var defender = new Defender();
        var katana = Weapon.newKatana();
        var expectedHealth = defender.getHealth() + katana.getHealth();
        var expectedAttack = defender.getAttack() + katana.getAttack();
        var expectedDefense = 0;
        defender.equipWeapon(katana);
        Assertions.assertAll(
                () -> Assertions.assertSame(expectedHealth, defender.getHealth()),
                () -> Assertions.assertSame(expectedAttack, defender.getAttack()),
                () -> Assertions.assertSame(expectedDefense, defender.getDefense())
        );
    }

    @Test
    @DisplayName("Healer equips MagicWand and has proper statistics")
    void test15() {
        var healer = new Healer();
        var wand = Weapon.newMagicWand();
        var expectedHealth = healer.getHealth() + wand.getHealth();
        var expectedAttack = healer.getAttack() + wand.getAttack();
        var expectedHealPower = healer.getHealPower() + wand.getHealPower();
        healer.equipWeapon(wand);
    }
    @Test
    @DisplayName("Lancer equips Sword and has proper statistics")
    void test16(){
        var lancer = new Lancer();
        var sword = Weapon.newSword();
        lancer.equipWeapon(sword);
    }
    
}
