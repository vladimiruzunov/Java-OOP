package bakery.entities.bakedFoods;

public class Bread extends BaseFood{
    private static final double INITIAL_BREAD = 200;
    public Bread(String name,double price) {
        super(name, INITIAL_BREAD, price);
    }
}
