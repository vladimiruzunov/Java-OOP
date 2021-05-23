package Shapes;

import static java.lang.Math.PI;

public class Circle extends Shape{
    private Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    public final Double getRadius() {
        return radius;
    }

    @Override
    public double calculateArea() {
        if (getArea() == null){
            double area =  PI * radius * radius;
            setArea(area);
        }
        return getArea();
    }

    @Override
    public double calculatePerimeter() {
        if (getPerimeter() == null){
            double perimeter = 2 * PI * radius;
            setPerimeter(perimeter);
        }
        return getPerimeter();
    }
}
