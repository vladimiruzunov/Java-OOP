package gettersAndSetters;

import java.lang.reflect.Method;
import java.util.*;

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
        Set<Method> getters = new TreeSet<>(new MethodComparator());
        Set<Method> setters = new TreeSet<>(new MethodComparator());

        for (Method declaredMethod : declaredMethods) {
            String name = declaredMethod.getName();
            if (name.contains("get")) {
                getters.add(declaredMethod);
            } else if (declaredMethod.getName().contains("set")) {
                setters.add(declaredMethod);
            }
        }

        for (Method getter : getters) {
            System.out.println(getter.getName() + " will return class " + getter.getReturnType().getName());
        }
        for (Method setter : setters) {
            System.out.println(setter.getName() + " and will set field of class "
                    + setter.getParameterTypes()[0].getName());
        }

    }
}