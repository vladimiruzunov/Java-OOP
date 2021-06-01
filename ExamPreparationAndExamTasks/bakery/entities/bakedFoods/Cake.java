package bakery.entities.bakedFoods;

public class Cake extends BaseFood{
    private static final double INITIAL_CAKE = 245;
    public Cake(String name,double price) {
        super(name, INITIAL_CAKE, price);
    }
}
