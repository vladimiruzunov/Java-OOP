package PointInRectangle;

public class Rectangle {
    private Point bottomLeft;
    private Point topRight;

    public Rectangle(Point bottomLeft, Point topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    public boolean contains(Point point) {
        return point.isGreaterOrEqualByX(bottomLeft)
                && point.isLessOrEqualByX(topRight)
                && point.isGreaterOrEqualByY(topRight)
                && point.isLessOrEqualByY(bottomLeft);
    }
}
