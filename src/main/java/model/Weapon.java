package model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Weapon {
    private int health;
    private int attack;
    private int defense;
    private int vampirism;
    private int healPower;
}
