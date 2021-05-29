package rpg_lab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DummyTest {
    public static final int DUMMY_HEALTH = 1000;
    public static final int DUMMY_EXPERIENCE = 10;

    private Dummy dummy;

    @Before
    public void setUp() {
        dummy = new Dummy(DUMMY_HEALTH,DUMMY_EXPERIENCE);
    }

    @Test
    public void testDummyLosesHealthIfAttacked () {
        dummy.takeAttack(100);

        assertEquals(DUMMY_HEALTH - 100, dummy.getHealth());
    }

    @Test
    public void testIdDeadDummyCanBeAttacked() {
        dummy.takeAttack(DUMMY_HEALTH);
        dummy.takeAttack(10);
    }

    @Test
    public void testDummyIfDeadCanGiveXp() {
        dummy.takeAttack(DUMMY_HEALTH);

        assertEquals(DUMMY_EXPERIENCE,dummy.giveExperience());
    }

    @Test
    public void testAliveDummyCanGiveXp() {
        dummy.giveExperience();
    }

}