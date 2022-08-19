package interfaces.command;

import model.Archer;

public class ArrowRainCommand implements ICommand {
    Archer archer;

    public ArrowRainCommand(Archer archer) {
        this.archer = archer;
    }

    public Archer getArcher() {
        return archer;
    }
}
