package wildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        List<Animal> animalList= new ArrayList<>();
        String line = "";

        while (!"End".equals(line = scanner.nextLine())){
            String[] animalInfo = line.split("\\s+");
            String type = animalInfo[0];
            String name = animalInfo[1];
            Double weight = Double.parseDouble(animalInfo[2]);
            String livingRegion = animalInfo[3];

            Animal animal = null;

            switch (type){
                case "Mouse":
                    animal = new Mouse(type,name,weight,livingRegion);
                    break;
                case "Zebra":
                    animal = new Zebra(type,name,weight,livingRegion);
                    break;
                case "Cat":
                    String breed = animalInfo[4];
                    animal = new Cat(type,name,weight,livingRegion, breed);
                    break;
                case "Tiger":
                    animal = new Tiger(type,name,weight,livingRegion);
                    break;
            }
            String[] foodTokens = scanner.nextLine().split("\\s+");
            Food food = null;
            int foodQuantity = Integer.parseInt(foodTokens[1]);
            if (foodTokens[0].equals("Vegetable")){
                food = new Vegetable(foodQuantity);
            } else {
                food = new Meat(foodQuantity);
            }
            animal.makeSound();
            try {
                animal.eat(food);
            } catch (IllegalArgumentException exception){
                System.out.println(exception.getMessage());
            }


            animalList.add(animal);
        }
        for (Animal animal : animalList) {
            System.out.println(animal.toString());
        }
    }
}
