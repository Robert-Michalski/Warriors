public class WarriorFactory {

    public Warrior getInstance(String type) {
        switch (type) {
            case "Warrior" -> {
                return new Warrior();
            }
            case "Knight" -> {
                return new Knight();
            }
            case "Defender" -> {
                return new Defender();
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
