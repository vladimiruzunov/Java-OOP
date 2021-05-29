package rpg_lab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AxeTest {
    public static final int AXE_ATTACK = 10;
    public static final int AXE_DURABILITY = 10;
    public static final int DUMMY_HEALTH = 1000;
    public static final int DUMMY_EXPERIENCE = 10;

    private Axe axe;
    private Dummy dummy;

    @Before
    public void setUp() {
        axe = new Axe(AXE_ATTACK, AXE_DURABILITY);
        dummy = new Dummy(DUMMY_HEALTH, DUMMY_EXPERIENCE);
    }

    @Test
    public void testAxeLosesDurabilityAfterAttack() {
        axe.attack(dummy);

        assertEquals(AXE_ATTACK - 1,axe.getDurabilityPoints());
    }

    @Test
    public void testIfWeaponIsBroken() {

        for (int i = 0; i < 11; i++) {
            axe.attack(dummy);
        }
    }
}