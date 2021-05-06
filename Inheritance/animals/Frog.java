package animals;

public class Frog extends Animal{
    @Override
    public String produceSound() {
        return "Ribbit";
    }

    public Frog(String name, int age, String gender) {
        super(name, age, gender);
    }
}
