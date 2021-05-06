package animals;

public class Tomcat extends Cat{
    final static String TOMCAT_GENDER = "Male";
    @Override
    public String produceSound() {
        return "MEOW";
    }

    public Tomcat(String name, int age) {
        super(name, age, TOMCAT_GENDER);
    }
}
