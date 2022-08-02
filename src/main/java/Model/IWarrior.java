package Model;

public interface IWarrior extends HasHealth, CanAttack {

    default void hit(IWarrior opponent) {
        opponent.receiveHit(this);
    }

    default void receiveHit(CanAttack damageDealer) {
        reduceHealthBasedOnDamage(damageDealer.getAttack());
    }

}

interface HasHealth {
    int getHealth();

    default boolean isAlive() {
        if (getHealth() > 0) {
            return true;
        } else {
            return false;
        }
    }

    void reduceHealthBasedOnDamage(int damage);
}

interface CanAttack {
    int getAttack();
}