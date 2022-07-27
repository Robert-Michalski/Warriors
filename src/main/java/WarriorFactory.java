public class WarriorFactory {

    public Warrior getInstance(String type) {
        switch (type) {
            case "Warrior" -> {
                return new Warrior();
            }
            case "Knight" -> {
                return new Knight();
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
