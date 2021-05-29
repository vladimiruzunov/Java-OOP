package rpg_lab;

import org.junit.Test;
import org.mockito.Mockito;
import rpg_lab.Hero;

import static org.junit.Assert.*;

public class HeroTest {

    public static final int TARGET_XP = 10;

    @Test
    public void attackGainsExperienceIfTargetIsDead() {
        Target fakeTarget = Mockito.mock(Target.class);
        Weapon fakeWeapon = Mockito.mock(Weapon.class);

        Mockito.when(fakeTarget.isDead()).thenReturn(true);
        Mockito.when(fakeTarget.giveExperience()).thenReturn(TARGET_XP);

        Hero hero = new Hero("testHero", fakeWeapon);

        hero.attack(fakeTarget);
        assertEquals(TARGET_XP,fakeTarget.giveExperience());
    }
}