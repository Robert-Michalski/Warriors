package model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Weapon implements IWeapon{

    private int health;
    private int attack;
    private int defense;
    private int vampirism;
    private int healPower;
    private static final Weapon.WeaponBuilder Sword =
            Weapon.builder()
                    .health(5)
                    .attack(2);
    private static final Weapon.WeaponBuilder Shield =
            Weapon.builder()
                    .health(20)
                    .attack(-1)
                    .defense(2);
    private static final Weapon.WeaponBuilder GreatAxe =
            Weapon.builder()
                    .health(-15)
                    .attack(5)
                    .defense(-2)
                    .vampirism(10);
    private static final Weapon.WeaponBuilder Katana =
            Weapon.builder()
                    .health(-20)
                    .attack(6)
                    .defense(-5)
                    .vampirism(50);
    private static final Weapon.WeaponBuilder MagicWand =
            Weapon.builder()
                    .health(30)
                    .attack(3)
                    .healPower(3);
    public static IWeapon newSword(){
        return Weapon.Sword.build();
    }
    public static IWeapon newShield(){
        return Weapon.Shield.build();
    }
    public static IWeapon newGreatAxe(){
        return Weapon.GreatAxe.build();
    }
    public static IWeapon newKatana(){
        return Weapon.Katana.build();
    }
    public static IWeapon newMagicWand(){
        return Weapon.MagicWand.build();
    }


}

