package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.Battle;

public class WeaponTests {
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
}
