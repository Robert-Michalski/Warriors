package interfaces;

public interface HasVampirism extends HasHealth{
    int getVampirism();
    void healSelfBy(int amount);
}
