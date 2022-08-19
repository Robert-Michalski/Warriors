package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DefenderTests {
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

}
