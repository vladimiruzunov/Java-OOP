package SalaryIncrease;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void increaseSalary(double bonus){
    if (this.getAge() < 30){
        this.salary = this.salary * (1.00 + bonus / 200);
    }else {
        this.salary = this.salary * (1.00 + bonus / 100);
    }
    }

    @Override
    public String toString() {
        return String.format("%s %s gets %f leva.",getFirstName(), getLastName(),
                getSalary());
    }
}
