package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.Battle;

public class HealerTests {
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
}
