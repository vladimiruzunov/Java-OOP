
package greedyTimes;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long bagCapacity = Long.parseLong(scanner.nextLine());
        String[] input = scanner.nextLine().split("\\s+");

        var torba = new LinkedHashMap<String, LinkedHashMap<String, Long>>();
        long zlato = 0;
        long kamuni = 0;
        long mangizi = 0;

        for (int i = 0; i < input.length; i += 2) {
            String item = input[i];
            long quantity = Long.parseLong(input[i + 1]);



            if (item.length() == 3) {
                if (!torba.containsKey(item)) {
                    if (torba.containsKey("Gem")) {
                        if (quantity > torba.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                            continue;
                        }
                    } else {
                        continue;
                    }
                } else if (torba.get(item).values().stream().mapToLong(e -> e).sum() + quantity > torba.get("Gem").values().stream().mapToLong(e -> e).sum()) {
                    continue;
                }
            } else if (item.toLowerCase().endsWith("gem")) {
                if (!torba.containsKey(item)) {
                    if (torba.containsKey("Gold")) {
                        if (quantity > torba.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                            continue;
                        }
                    } else {
                        continue;
                    }
                } else if (torba.get(item).values().stream().mapToLong(e -> e).sum() + quantity > torba.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                    continue;
                }
            } else if (item.toLowerCase().equals("gold")) {

            }

//            if (kvoE.equals("")) {
//                continue;
//            } else if (bagCapacity < torba.values().stream().map(Map::values).flatMap(Collection::stream).mapToLong(e -> e).sum() + quantity) {
//                continue;
//            }


            if (!torba.containsKey(item)) {
                torba.put((item), new LinkedHashMap<String, Long>());
            }

            if (!torba.get(item).containsKey(item)) {
                torba.get(item).put(item, 0L);
            }


            torba.get(item).put(item, torba.get(item).get(item) + quantity);
            if (item.equals("Gold")) {
                zlato += quantity;
            } else if (item.equals("Gem")) {
                kamuni += quantity;
            } else if (item.equals("Cash")) {
                mangizi += quantity;
            }
        }

        for (var x : torba.entrySet()) {
            Long sumValues = x.getValue().values().stream().mapToLong(l -> l).sum();

            System.out.println(String.format("<%s> $%s", x.getKey(), sumValues));

            x.getValue().entrySet().stream().sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())).forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));

        }
    }
}