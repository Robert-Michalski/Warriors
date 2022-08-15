package interfaces;

import interfaces.command.ICommand;
import model.Warrior;

public interface IWarrior extends CanAttack, HasHealth, CanWieldWeapon{
    IWarrior getWarriorBehind();
    IWarrior getWarriorInFront();
    void process(ICommand command, IWarrior warrior);
}
