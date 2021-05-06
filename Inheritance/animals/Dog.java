package animals;

public class Dog extends Animal{
    @Override
    public String produceSound() {
        return "Woof!";

    }

    public Dog(String name, int age, String gender) {
        super(name, age, gender);

    }
}
