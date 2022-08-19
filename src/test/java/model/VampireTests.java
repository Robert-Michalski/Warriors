package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VampireTests {
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

}
