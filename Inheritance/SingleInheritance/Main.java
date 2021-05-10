package SingleInheritance;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Animal animal = new Animal();
        Dog dog = new Dog();
        dog.eat();
        dog.bark();
    }
}
