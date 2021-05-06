package animals;

public class Kitten extends Cat{
    final static String KITTEN_GENDER = "Female";
    @Override
    public String produceSound() {
        return "Meow";
    }

    public Kitten(String name, int age) {
        super(name, age, KITTEN_GENDER);
    }
}
