package Shapes;

public class Rectangle extends Shape{
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double calculateArea() {
        if (getArea() == null){
            double area =  width * height;
            setArea(area);
        }
        return getArea();
    }

    @Override
    public double calculatePerimeter() {
        if (getPerimeter() == null){
            double perimeter = width * 2 + height * 2;
            setPerimeter(perimeter);
        }
        return getPerimeter();
    }
}
