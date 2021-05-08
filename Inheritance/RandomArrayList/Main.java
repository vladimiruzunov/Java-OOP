package RandomArrayList;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RandomArrayList<Integer> randomArrayList = new RandomArrayList<Integer>();
        ThreadLocalRandom.current().ints(50)
                .forEach(randomArrayList::add);

        System.out.println(randomArrayList.getRandomElement());
    }
}
