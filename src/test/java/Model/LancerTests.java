package Model;

import Service.Battle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LancerTests {
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
    void GivenFightOfArmyWith1LancerAndArmyWith2WarriorsThenBothWarriorsLooseHealth(){
        //GIVEN
        var army1 = new Army()
                .addUnits(Unit.UnitType.LANCER, 1);
        var army2 = new Army()
                .addUnits(Unit.UnitType.WARRIOR, 2);
        //WHEN
        Battle.fight(army1.getTroops().get(0),army2.getTroops().get(0));
        //THEN
        Assertions.assertNotSame(50,army2.getTroops().get(1).getHealth());
    }

}
