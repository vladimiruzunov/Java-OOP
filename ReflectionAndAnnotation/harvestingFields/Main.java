package harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Field[] declaredFields = RichSoilLand.class.getDeclaredFields();

        String line;
        while (!"HARVEST".equals(line = scanner.nextLine())) {
            String modifier = line;
            if (!modifier.equals("all")) {
                Field[] fields = Arrays.stream(declaredFields)
                        .filter(f -> Modifier.toString(f.getModifiers())
                                .equals(modifier)).toArray(Field[]::new);
                printFields(fields);
            } else {
                printFields(declaredFields);
            }
        }
    }

    private static void printFields(Field[] fields) {
        Arrays.stream(fields).forEach(f -> System.out.printf("%s %s %s%n"
                , Modifier.toString(f.getModifiers())
                , f.getType().getSimpleName()
                , f.getName()));
    }
}

