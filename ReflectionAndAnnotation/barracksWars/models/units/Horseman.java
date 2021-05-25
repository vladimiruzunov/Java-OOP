package barracksWars.models.units;

public class Horseman extends AbstractUnit{

    public static int HORSEMAN_HEALTH = 20;
    public static int HORSEMAN_DAMAGE = 20;

    public Horseman() {
        super(HORSEMAN_HEALTH, HORSEMAN_DAMAGE);
    }
}
