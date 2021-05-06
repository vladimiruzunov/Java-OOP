package animals;

public class Cat extends Animal{
    @Override
    public String produceSound() {
        return "Meow meow";
    }

    public Cat(String name, int age, String gender) {
        super(name, age, gender);
    }
}
