public class Battle {
    private Battle() {
    }

    public static boolean fight(Warrior warrior1, Warrior warrior2) {
        while (warrior1.isAlive() && warrior2.isAlive()) {
            warrior1.attack(warrior2);
            if (warrior2.isAlive()) {
                warrior2.attack(warrior1);
            }
        }
        return warrior1.isAlive();

    }
}
