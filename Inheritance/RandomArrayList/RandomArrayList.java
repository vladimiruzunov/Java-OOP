package RandomArrayList;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RandomArrayList<T> extends ArrayList<T> {
    public T getRandomElement(){
        int i = ThreadLocalRandom.current().nextInt(this.size());
        return remove(i);
    }
}
