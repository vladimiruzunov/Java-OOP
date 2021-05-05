package ShoppingSpree;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        this.name = name;
        this.money = money;
        this.products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }

        this.name = name;
    }

    private void setMoney(double money) {
        if (money < 0){
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }
    public void buyProduct(Product product){
        if (!hasEnough(product)){
        throw new IllegalArgumentException(String.format("%s can't afford %s"
        ,this.name, product.getName()));
        }
        this.money -= product.getCost();
        this.products.add(product);
    }
    private boolean hasEnough(Product product){
        return this.money >= product.getCost();
    }
}
