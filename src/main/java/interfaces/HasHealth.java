package interfaces;

public interface HasHealth {
    void setHealth(int health);
    int getHealth();
    int getInitialHealth();
    void reduceHealthBy(int attack);


    default boolean isAlive() {
        return this.getHealth() > 0 ? true : false;
    }
}
