package Model;

public interface Chain {
    public abstract void setNext(Chain nextInChain);
    public abstract void process(Warrior warrior);
}
