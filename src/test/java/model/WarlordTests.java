package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import service.Battle;

import java.util.stream.Stream;


class WarlordTests {
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
    void SmokeShowWeapons() {
        var ogre = new Warrior();
        var lancelot = new Knight();
        var richard = new Defender();
        var eric = new Vampire();
        var freelancer = new Lancer();
        var priest = new Healer();
        var sword = Weapon.newSword();
        var shield = Weapon.newShield();
        var axe = Weapon.newGreatAxe();
        var katana = Weapon.newKatana();
        var wand = Weapon.newMagicWand();
        var wunderWaffe = Weapon.builder()
                .health(50)
                .attack(10)
                .defense(5)
                .vampirism(150)
                .healPower(8)
                .build();
        ogre.equipWeapon(sword);
        ogre.equipWeapon(shield);
        ogre.equipWeapon(wunderWaffe);
        lancelot.equipWeapon(wunderWaffe);
        richard.equipWeapon(shield);
        eric.equipWeapon(wunderWaffe);
        freelancer.equipWeapon(axe);
        freelancer.equipWeapon(katana);
        priest.equipWeapon(wand);
        priest.equipWeapon(shield);

        var myArmy = new Army()
                .addUnits(Unit.UnitType.KNIGHT, 1)
                .addUnits(Unit.UnitType.LANCER, 1)
                .lineUp();
        var enemyArmy = new Army()
                .addUnits(Unit.UnitType.VAMPIRE, 1)
                .addUnits(Unit.UnitType.HEALER, 1)
                .lineUp();
        myArmy.equipWarriorAtPosition(0, axe);
        myArmy.equipWarriorAtPosition(1, wunderWaffe);
        enemyArmy.equipWarriorAtPosition(0, katana);
        enemyArmy.equipWarriorAtPosition(1, wand);

        Assertions.assertAll(
                () -> Assertions.assertSame(125, ogre.getHealth()),
                () -> Assertions.assertSame(17, lancelot.getAttack()),
                () -> Assertions.assertSame(4, richard.getDefense()),
                () -> Assertions.assertEquals(200, eric.getVampirism()),
                () -> Assertions.assertSame(15, freelancer.getHealth()),
                () -> Assertions.assertSame(5, priest.getHealPower()),
                () -> Assertions.assertFalse(Battle.fight(ogre, eric)),
                () -> Assertions.assertFalse(Battle.fight(priest, richard)),
                () -> Assertions.assertTrue(Battle.fight(lancelot, freelancer)),
                () -> Assertions.assertTrue(Battle.fight(myArmy, enemyArmy))
        );
        System.out.println(myArmy.getTroops());
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
                .addUnits(Unit.UnitType.WARRIOR, 2)
                .lineUp();
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
    @DisplayName("Given Warrior equips a sword then he has proper statistics")
    void GivenWarriorEquipsASwordThenHeHasProperStatistics() {
        //GIVEN
        var warrior = new Warrior();
        var sword = Weapon.newSword();
        var expectedHealth = warrior.getHealth() + sword.getHealth();
        var expectedAttack = warrior.getAttack() + sword.getAttack();
        //WHEN
        warrior.equipWeapon(sword);
        //THEN
        Assertions.assertSame(expectedHealth, warrior.getHealth());
        Assertions.assertSame(expectedAttack, warrior.getAttack());
    }

    @Test
    @DisplayName("Given Defender equips a shield then he has proper statistics")
    void GivenDefenderEquipsAShieldThenHeHasProperStatistics() {
        //GIVEN
        var defender = new Defender();
        var shield = Weapon.newShield();
        var expectedHealth = defender.getHealth() + shield.getHealth();
        var expectedAttack = defender.getAttack() + shield.getAttack();
        var expectedDefense = defender.getDefense() + shield.getDefense();
        //WHEN
        defender.equipWeapon(shield);
        //THEN
        Assertions.assertAll(
                () -> Assertions.assertSame(expectedHealth, defender.getHealth()),
                () -> Assertions.assertSame(expectedAttack, defender.getAttack()),
                () -> Assertions.assertSame(expectedDefense, defender.getDefense())
        );
    }

    @Test
    @DisplayName("Given Vampire equips GreatAxe then he has proper statistics")
    void GivenVampireEquipsAGreatAxeThenHeHasProperStatistics() {
        //GIVEN
        var vampire = new Vampire();
        var greatAxe = Weapon.newGreatAxe();
        var expectedHealth = vampire.getHealth() + greatAxe.getHealth();
        var expectedAttack = vampire.getAttack() + greatAxe.getAttack();
        var expectedVampirism = vampire.getVampirism() + greatAxe.getVampirism();
        //WHEN
        vampire.equipWeapon(greatAxe);
        //THEN
        Assertions.assertAll(
                () -> Assertions.assertSame(expectedHealth, vampire.getHealth()),
                () -> Assertions.assertSame(expectedAttack, vampire.getAttack()),
                () -> Assertions.assertSame(expectedVampirism, vampire.getVampirism())
        );
    }

    @Test
    @DisplayName("Given Vampire equips a Katana then he has proper statistics")
    void GivenVampireEquipsAKatanaThenHeHasProperStatistics() {
        //GIVEN
        var vampire = new Vampire();
        var katana = Weapon.newKatana();
        var expectedHealth = vampire.getHealth() + katana.getHealth();
        var expectedAttack = vampire.getAttack() + katana.getAttack();
        var expectedVampirism = vampire.getVampirism() + katana.getVampirism();
        //WHEN
        vampire.equipWeapon(katana);
        //THEN
        Assertions.assertAll(
                () -> Assertions.assertSame(expectedHealth, vampire.getHealth()),
                () -> Assertions.assertSame(expectedAttack, vampire.getAttack()),
                () -> Assertions.assertSame(expectedVampirism, vampire.getVampirism())
        );
    }

    @Test
    @DisplayName("Given Defender equips a Katana then he has proper statistics")
    void GivenDefenderEquipsAKatanaThenHeHasProperStatistics() {
        //GIVEN
        var defender = new Defender();
        var katana = Weapon.newKatana();
        var expectedHealth = defender.getHealth() + katana.getHealth();
        var expectedAttack = defender.getAttack() + katana.getAttack();
        var expectedDefense = 0;
        //WHEN
        defender.equipWeapon(katana);
        //THEN
        Assertions.assertAll(
                () -> Assertions.assertSame(expectedHealth, defender.getHealth()),
                () -> Assertions.assertSame(expectedAttack, defender.getAttack()),
                () -> Assertions.assertSame(expectedDefense, defender.getDefense())
        );
    }

    @Test
    @DisplayName("Given Healer equips MagicWand then he has proper statistics")
    void GivenHealerEquipsAMagicWandThenHeHasProperStatistics() {
        //GIVEN
        var healer = new Healer();
        var wand = Weapon.newMagicWand();
        var expectedHealth = healer.getHealth() + wand.getHealth();
        var expectedAttack = healer.getAttack() + wand.getAttack();
        var expectedHealPower = healer.getHealPower() + wand.getHealPower();
        //WHEN
        healer.equipWeapon(wand);
        //THEN
        Assertions.assertAll(
                () -> Assertions.assertSame(expectedHealth, healer.getHealth()),
                () -> Assertions.assertSame(expectedAttack, healer.getAttack()),
                () -> Assertions.assertSame(expectedHealPower, healer.getHealPower())
        );

    }

    @Test
    @DisplayName("Given Lancer equips a sword then he has proper statistics")
    void GivenLancerEquipsASwordThenHeHasProperStatistics() {
        //GIVEN
        var lancer = new Lancer();
        var sword = Weapon.newSword();
        var expectedHealth = lancer.getHealth() + sword.getHealth();
        var expectedAttack = lancer.getAttack() + sword.getAttack();
        //WHEN
        lancer.equipWeapon(sword);
        //THEN
        Assertions.assertAll(
                () -> Assertions.assertSame(expectedHealth, lancer.getHealth()),
                () -> Assertions.assertSame(expectedAttack, lancer.getAttack())
        );

    }

    @Test
    @DisplayName("Given Knight equips two custom weapons then he has proper statistics")
    void GivenKnightEquipsTwoCustomWeaponsThenHeHasProperStatistics() {
        //GIVEN
        var knight = new Knight();
        var superWeapon = Weapon.builder()
                .health(50)
                .attack(10)
                .defense(5)
                .vampirism(150)
                .healPower(8)
                .build();
        var expectedHealth = knight.getHealth() + superWeapon.getHealth() + superWeapon.getHealth();
        var expectedAttack = knight.getAttack() + superWeapon.getAttack() + superWeapon.getAttack();
        //WHEN
        knight.equipWeapon(superWeapon);
        knight.equipWeapon(superWeapon);
        //THEN
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedHealth, knight.getHealth()),
                () -> Assertions.assertEquals(expectedAttack, knight.getAttack())
        );
    }

    @Test
    @DisplayName("1. Weapon: ")
    void test18() {
        //GIVEN
        var unit1 = new Warrior();
        var unit2 = new Vampire();
        var weapon1 = Weapon.builder()
                .health(-10)
                .attack(5)
                .defense(0)
                .vampirism(40)
                .healPower(0)
                .build();
        var weapon2 = Weapon.newSword();
        unit1.equipWeapon(weapon1);
        unit2.equipWeapon(weapon2);
        //WHEN
        var result = Battle.fight(unit1, unit2);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("2. Weapon: ")
    void test19() {
        //GIVEN
        var unit1 = new Defender();
        var unit2 = new Lancer();
        var weapon1 = Weapon.newShield();
        var weapon2 = Weapon.newGreatAxe();
        unit1.equipWeapon(weapon1);
        unit2.equipWeapon(weapon2);
        //WHEN
        var result = Battle.fight(unit1, unit2);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("3. Weapon: ")
    void test20() {
        //GIVEN
        var unit1 = new Healer();
        var unit2 = new Knight();
        var weapon1 = Weapon.newMagicWand();
        var weapon2 = Weapon.newKatana();
        unit1.equipWeapon(weapon1);
        unit2.equipWeapon(weapon2);
        //WHEN
        var result = Battle.fight(unit1, unit2);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("4. Weapon: ")
    void test21() {
        //GIVEN
        var unit1 = new Defender();
        var unit2 = new Vampire();
        var weapon1 = Weapon.newShield();
        var weapon2 = Weapon.newMagicWand();
        var weapon3 = Weapon.newShield();
        var weapon4 = Weapon.newKatana();
        unit1.equipWeapon(weapon1);
        unit1.equipWeapon(weapon2);
        unit2.equipWeapon(weapon3);
        unit2.equipWeapon(weapon4);
        //WHEN
        var result = Battle.fight(unit1, unit2);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("5. Weapon: ")
    void test22() {
        //GIVEN
        var weapon1 = Weapon.newMagicWand();
        var weapon2 = Weapon.newGreatAxe();
        var myArmy = new Army()
                .addUnits(Unit.UnitType.KNIGHT, 1)
                .addUnits(Unit.UnitType.LANCER, 1)
                .lineUp();
        var enemyArmy = new Army()
                .addUnits(Unit.UnitType.VAMPIRE, 1)
                .addUnits(Unit.UnitType.HEALER, 1)
                .lineUp();
        myArmy.equipWarriorAtPosition(0, weapon1);
        myArmy.equipWarriorAtPosition(1, weapon2);
        enemyArmy.equipWarriorAtPosition(0, weapon1);
        enemyArmy.equipWarriorAtPosition(1, weapon2);
        //WHEN
        var result = Battle.fight(myArmy, enemyArmy);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("6. Weapon: ")
    void test23() {
        //GIVEN
        var weapon1 = Weapon.newSword();
        var weapon2 = Weapon.newGreatAxe();
        var army1 = new Army()
                .addUnits(Unit.UnitType.DEFENDER, 1)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.KNIGHT, 1)
                .addUnits(Unit.UnitType.HEALER, 1)
                .lineUp();
        army1.equipWarriorAtPosition(0, weapon2);
        army1.equipWarriorAtPosition(1, weapon2);
        army2.equipWarriorAtPosition(0, weapon1);
        army2.equipWarriorAtPosition(1, weapon1);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("7. Weapon: ")
    void test24() {
        //GIVEN
        var weapon1 = Weapon.newKatana();
        var army1 = new Army()
                .addUnits(Unit.UnitType.DEFENDER, 2)
                .lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.KNIGHT, 1)
                .addUnits(Unit.UnitType.VAMPIRE, 1)
                .lineUp();
        army1.equipWarriorAtPosition(0, weapon1);
        army1.equipWarriorAtPosition(1, weapon1);
        army2.equipWarriorAtPosition(0, weapon1);
        army2.equipWarriorAtPosition(1, weapon1);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("8. Weapon: ")
    void test25() {
        //GIVEN
        var weapon1 = Weapon.builder()
                .health(-20)
                .attack(6)
                .defense(1)
                .vampirism(40)
                .healPower(-2)
                .build();
        var weapon2 = Weapon.builder()
                .health(20)
                .attack(-2)
                .defense(2)
                .vampirism(-55)
                .healPower(3)
                .build();
        var army1 = new Army()
                .addUnits(Unit.UnitType.KNIGHT, 3)
                .lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.DEFENDER, 2)
                .lineUp();
        army1.equipWarriorAtPosition(0, weapon1);
        army1.equipWarriorAtPosition(1, weapon1);
        army1.equipWarriorAtPosition(2, weapon2);
        army2.equipWarriorAtPosition(0, weapon1);
        army2.equipWarriorAtPosition(0, weapon1);
        army2.equipWarriorAtPosition(1, weapon2);
        army2.equipWarriorAtPosition(2, weapon2);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("9. Weapon: ")
    void test26() {
        //GIVEN
        var weapon1 = Weapon.builder()
                .health(-20)
                .attack(1)
                .defense(1)
                .vampirism(40)
                .healPower(-2)
                .build();
        var weapon2 = Weapon.builder()
                .health(20)
                .attack(2)
                .defense(2)
                .vampirism(-55)
                .healPower(3)
                .build();
        var army1 = new Army()
                .addUnits(Unit.UnitType.VAMPIRE, 3)
                .lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.DEFENDER, 2)
                .lineUp();
        army1.equipWarriorAtPosition(0, weapon1);
        army1.equipWarriorAtPosition(1, weapon1);
        army1.equipWarriorAtPosition(2, weapon2);
        army2.equipWarriorAtPosition(0, weapon1);
        army2.equipWarriorAtPosition(1, weapon2);
        army2.equipWarriorAtPosition(2, weapon2);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("10. Weapon: ")
    void test27() {
        var weapon1 = Weapon.newKatana();
        var weapon2 = Weapon.newShield();
        var army1 = new Army()
                .addUnits(Unit.UnitType.VAMPIRE, 2)
                .addUnits(Unit.UnitType.ROOKIE, 2)
                .lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.DEFENDER, 2)
                .lineUp();
        army1.equipWarriorAtPosition(0, weapon1);
        army1.equipWarriorAtPosition(1, weapon1);
        army1.equipWarriorAtPosition(2, weapon2);
        army2.equipWarriorAtPosition(0, weapon1);
        army2.equipWarriorAtPosition(1, weapon2);
        army2.equipWarriorAtPosition(2, weapon2);
        //WHEN
        var result = Battle.fight(army1, army2);
        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("11. Weapon: ")
    void test28() {
        var weapon1 = Weapon.newSword();
        var weapon2 = Weapon.newGreatAxe();
        var army1 = new Army()
                .addUnits(Unit.UnitType.VAMPIRE, 3);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.DEFENDER, 1);
        army1.equipWarriorAtPosition(0, weapon2);
        army1.equipWarriorAtPosition(1, weapon2);
        army1.equipWarriorAtPosition(2, weapon2);
        army2.equipWarriorAtPosition(0, weapon1);
        army2.equipWarriorAtPosition(1, weapon1);
        var result = Battle.straightFight(army1, army2);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("12. Weapon: ")
    void test29() {
        var weapon1 = Weapon.newKatana();
        var weapon2 = Weapon.newMagicWand();
        var army1 = new Army()
                .addUnits(Unit.UnitType.ROOKIE, 3);
        var army2 = new Army()
                .addUnits(Unit.UnitType.DEFENDER, 1)
                .addUnits(Unit.UnitType.HEALER, 1);
        army1.equipWarriorAtPosition(0, weapon1);
        army1.equipWarriorAtPosition(1, weapon1);
        army1.equipWarriorAtPosition(2, weapon1);
        army2.equipWarriorAtPosition(0, weapon2);
        army2.equipWarriorAtPosition(1, weapon2);
        var result = Battle.straightFight(army1, army2);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Given an army then Warlord should be in last position")
    void GivenAnArmyThenWarlordShouldBeInLastPosition() {
        //GIVEN
        var army = new Army()
                .addUnits(Unit.UnitType.WARLORD, 1)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .lineUp();
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.WARLORD, 1)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .lineUp();
        //WHEN
        army.processStrategy();
        army2.processStrategy();
        System.out.println("after shift " + army2.getTroops());
        //THEN
        Assertions.assertTrue(army.getWarrior(army.getSize() - 1) instanceof Warlord);
        Assertions.assertTrue(army2.getWarrior(army2.getSize() - 1) instanceof Warlord);
    }

    @Test
    @DisplayName("Given army with at least 1 lancer then lancer is in front of the army")
    void GivenArmyWithAtLeast1LancerThenLancerIsInFrontOfTheArmy() {
        //GIVEN
        var army = new Army()
                .addUnits(Unit.UnitType.WARLORD, 1)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.LANCER, 1)
                .addUnits(Unit.UnitType.LANCER, 1)
                .lineUp();
        //WHEN
        army.processStrategy();
        //THEN
        Assertions.assertAll(
                () -> Assertions.assertTrue(army.getWarrior(0) instanceof Lancer),
                () -> Assertions.assertTrue(army.getWarrior(1) instanceof Lancer),
                () -> Assertions.assertTrue(army.getWarrior(army.getSize() - 1) instanceof Warlord)
        );
    }

    @Test
    @DisplayName("Given Lancer and Healer in an Army then Lancer is first and Healer is behind him")
    void GivenLancerAndHealerInAnArmyThenLancerIsFirstAndHealerIsBehindHim() {
        var army = new Army()
                .addUnits(Unit.UnitType.WARLORD, 1)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .addUnits(Unit.UnitType.LANCER, 1)
                .addUnits(Unit.UnitType.LANCER, 1)
                .addUnits(Unit.UnitType.HEALER, 1)
                .lineUp();
        army.processStrategy();
        Assertions.assertAll(
                () -> Assertions.assertTrue(army.getWarrior(0) instanceof Lancer),
                () -> Assertions.assertTrue(army.getWarrior(1) instanceof Healer),
                () -> Assertions.assertTrue(army.getWarrior(2) instanceof Lancer),
                () -> Assertions.assertTrue(army.getWarrior(army.getSize() - 1) instanceof Warlord)
        );
    }


    @Test
    @DisplayName("Given no lancers are present in army then first warrior that can fight will be in front")
    void GivenNoLancersArePresentInArmyThenFirstWarriorThatCanFightWillBeInFront() {
        //GIVEN
        var army = new Army()
                .addUnits(Unit.UnitType.HEALER, 1)
                .addUnits(Unit.UnitType.WARLORD, 1)
                .addUnits(Unit.UnitType.WARRIOR, 1)
                .lineUp();
        //WHEN
        army.processStrategy();
        //THEN
        Assertions.assertTrue(army.getWarrior(0) instanceof Warrior);

    }


    @Test
    @DisplayName("Warlord smoke test")
    void test35() {
        var ronald = new Warlord();
        var heimdall = new Knight();
        var myArmy = new Army()
                .addUnits(Unit.UnitType.WARLORD, 1)
                .addUnits(Unit.UnitType.WARRIOR, 2)
                .addUnits(Unit.UnitType.LANCER, 2)
                .addUnits(Unit.UnitType.HEALER, 2)
                .lineUp();
        var enemyArmy = new Army()
                .addUnits(Unit.UnitType.WARLORD, 3)
                .addUnits(Unit.UnitType.VAMPIRE, 1)
                .addUnits(Unit.UnitType.HEALER, 2)
                .addUnits(Unit.UnitType.KNIGHT, 2)
                .lineUp();
        myArmy.processStrategy();
        enemyArmy.processStrategy();

        Assertions.assertAll(
                () -> Assertions.assertFalse(Battle.fight(heimdall, ronald)),
                () -> Assertions.assertTrue(myArmy.getWarrior(0) instanceof Lancer),
                () -> Assertions.assertTrue(myArmy.getWarrior(1) instanceof Healer),
                () -> Assertions.assertTrue(myArmy.getWarrior(myArmy.getSize() - 1) instanceof Warlord),
                () -> Assertions.assertTrue(enemyArmy.getWarrior(0) instanceof Vampire),
                () -> Assertions.assertTrue(enemyArmy.getWarrior(enemyArmy.getSize() - 1) instanceof Warlord),
                () -> Assertions.assertTrue(enemyArmy.getWarrior(enemyArmy.getSize() - 2) instanceof Knight),
                () -> Assertions.assertSame(enemyArmy.getSize(), 6),
                () -> Assertions.assertTrue(Battle.fight(myArmy, enemyArmy))
        );
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

