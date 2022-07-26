import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestSuit {
    @Test
    @DisplayName("Warrior fights knight, warrior should lose")
    void test01() {
        var carl = new Knight();
        var dave = new Warrior();
        assertFalse(Battle.fight(dave, carl));

    }

    @Test
    @DisplayName("Knight hits Warrior, Warrior health is reduced by Knight' attack")
    void test02() {
        var carl = new Knight();
        var dave = new Warrior();
        var expectedHealth = 43;
        var actualHealth = (dave.getHealth() - carl.getAttack());
        assertEquals(expectedHealth, actualHealth);
    }

    @Test
    @DisplayName("Two Warriors fight, first to attack wins")
    void test03() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        assertTrue(Battle.fight(chuck, bruce));
    }

    @Test
    @DisplayName("Two Warriors fight, first to attack is alive")
    void test04() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        Battle.fight(chuck, bruce);
        assertTrue(chuck.isAlive());
    }

    @Test
    @DisplayName("Two Warriors fight, second to attack is dead")
    void test05() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        Battle.fight(chuck, bruce);
        assertFalse(bruce.isAlive());
    }
}
