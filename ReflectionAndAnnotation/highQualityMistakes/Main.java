package highQualityMistakes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static class MethodComparator implements Comparator<Method> {

        @Override
        public int compare(Method first, Method second) {
            return first.getName().compareTo(second.getName());
        }
    }

    public static void main(String[] args) {
        Class<Reflection> reflectionClass = Reflection.class;

        Method[] declaredMethods = reflectionClass.getDeclaredMethods();
        Field[] fields = reflectionClass.getDeclaredFields();
        Set<Method> getters = new TreeSet<>(new MethodComparator());
        Set<Method> setters = new TreeSet<>(new MethodComparator());

        for (Method declaredMethod : declaredMethods) {
            String name = declaredMethod.getName();
            if (name.contains("get")) {
                getters.add(declaredMethod);
            } else if (name.contains("set") ) {
                setters.add(declaredMethod);
            }
        }

        Arrays.stream(fields).filter( f -> !Modifier.isPrivate(f.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .forEach(f -> System.out.println(f.getName() + " must be private!"));

        for (Method getter : getters) {
            if (!Modifier.isPublic(getter.getModifiers())) {
                System.out.println(getter.getName() + " have to be public!");
            }
        }
        for (Method setter : setters) {
            if (!Modifier.isPrivate(setter.getModifiers())) {
                System.out.println(setter.getName() + " have to be private!");
            }
        }

    }
}