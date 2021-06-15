package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GarageTests {
    private Car car;
    private List<Car> cars;
    private Garage garage;

    @Before
    public void setUp() {
        this.car = new Car("Golf", 120, 3000);
        this.cars = new ArrayList<>();
        this.garage = new Garage();
    }

    @Test
    public void testCarShouldAddItCorrectly() {
        garage.addCar(car);
        Assert.assertEquals(1,garage.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCarWithAddingNullCarShouldThrowException() {
    garage.addCar(null);
    }
    @Test
    public void testAddCarWithAddingCarShouldWorkCorrect() {

        garage.addCar(car);
        Assert.assertEquals(1,garage.getCount());
    }

    @Test
    public void testFindAllCarsWithCurrentSpeedOver() {
        Car car2 = new Car("Top", 56, 400);
        garage.addCar(car);
        garage.addCar(car2);
        List<Car> allCarsWithMaxSpeedAbove = garage.findAllCarsWithMaxSpeedAbove(60);
        Assert.assertEquals(1,allCarsWithMaxSpeedAbove.size());
    }

    @Test
    public void testFindTheMostExpensiveCar() {
        Car car2 = new Car("Top", 56, 400);
        garage.addCar(car);
        garage.addCar(car2);
        Car theMostExpensiveCar = garage.getTheMostExpensiveCar();
        Assert.assertEquals(car,theMostExpensiveCar);

    }

    @Test
    public void testGetCarsByBrand() {
        Car car2 = new Car("Top", 56, 400);
        garage.addCar(car);
        garage.addCar(car2);
        List<Car> top = garage.findAllCarsByBrand("Top");
        Assert.assertEquals(car2,top.get(0));
    }
}