package model;

import model.*;

public class WarriorFactory {
    public Warrior getInstance(Unit.UnitType type) {
        switch (type) {
            case WARRIOR -> {
                return new Warrior();
            }
            case KNIGHT -> {
                return new Knight();
            }
            case DEFENDER -> {
                return new Defender();
            }
            case VAMPIRE -> {
                return new Vampire();
            }
            case LANCER -> {
                return new Lancer();
            }
            case HEALER -> {
                return new Healer();
            }
            case ROOKIE -> {
                return new Rookie();
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
