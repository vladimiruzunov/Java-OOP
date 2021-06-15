package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceshipTests {
    private Spaceship spaceship;
    @Before
    public void setUp(){
        this.spaceship = new Spaceship("test", 5);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldFailWhenNull() {
        new Spaceship(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldFailWhenEmpty() {
        new Spaceship("   ", 10);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityShouldFailWhenBellowZero(){
        new Spaceship("Spaceship", -1);
    }
    @Test
    public void testGetAstronautsCountShouldReturnTwoWhenTwoAdded(){
    spaceship.add(new Astronaut("test1", 100));
    spaceship.add(new Astronaut("test2", 10));
    assertEquals(2, spaceship.getCount());
    }
    @Test
    public void testGetAstronautsCountShouldReturnZeroWhenEmpty(){
        assertEquals(0, spaceship.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddSameAstronautTwiceShouldFail(){
        spaceship.add(new Astronaut("test1", 100));
        spaceship.add(new Astronaut("test1", 100));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddSameAstronautWhenCapacityIsFullShouldFail(){
        Spaceship spaceship = new Spaceship("Spaceship", 1);
        spaceship.add(new Astronaut("test1", 100));
        spaceship.add(new Astronaut("test2", 100));
    }
    @Test
    public void testRemoveAstronautShouldReturnFalseWhenNoSuchAdded(){
        this.spaceship.add(new Astronaut("test1", 100));
        assertFalse(this.spaceship.remove("other"));
    }
    @Test
    public void testRemoveAstronautShouldReturnTrueAdded(){
        this.spaceship.add(new Astronaut("test1", 100));
        assertTrue(this.spaceship.remove("test1"));
    }

}
