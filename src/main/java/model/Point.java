package model;

/**
 * @program: DrawingBoard
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-09-19 22:41
 **/
public class Point {

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void print() {
        System.out.println("(" + x + "," + y + ")");
    }
}
