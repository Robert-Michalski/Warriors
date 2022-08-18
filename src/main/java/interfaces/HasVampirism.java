package interfaces;

public interface HasVampirism extends HasHealth{
    int getVampirism();
    void healSelfByAmount(int amount);
}
