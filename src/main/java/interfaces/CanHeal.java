package interfaces;

import model.Warrior;

public interface CanHeal {
    int getHealPower();
    void heal(HasHealth warrior);
}
