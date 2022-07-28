package Service;

import Model.Defender;
import Model.Knight;
import Model.Warrior;

public class WarriorFactory {

    public Warrior getInstance(String type) {
        switch (type) {
            case "Model.Warrior" -> {
                return new Warrior();
            }
            case "Model.Knight" -> {
                return new Knight();
            }
            case "Model.Defender" -> {
                return new Defender();
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
