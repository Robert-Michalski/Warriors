package model;

public class WarriorFactory {
    public Warrior getInstance(Unit.UnitType type){
        switch(type){
            case WARRIOR -> {
                return new Warrior();
            }
            case KNIGHT -> {
                return new Knight();
            }
            case DEFENDER -> {
                return new Defender();
            }
            case LANCER -> {
                return new Lancer();
            }
            case HEALER -> {
                return new Healer();
            }
            case VAMPIRE -> {
                return new Vampire();
            }
            case WARLORD -> {
                return new Warlord();
            }
            case ROOKIE -> {
                return new Rookie();
            }
            default -> {
                throw new IllegalArgumentException("NO SUCH TYPE");
            }
        }
    }
}
