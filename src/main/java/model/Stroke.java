package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * @program: DrawingBoard
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-09-19 22:41
 **/
public class Stroke {

    private ArrayList<Point> points = new ArrayList<Point>();

    public Stroke() {

    }

    public Stroke(Stroke stroke) {
        ArrayList<Point> newPoints = stroke.getPoints();
        for (Point p : newPoints) {
            this.points.add(p);
        }
    }


    public void addPoints(Point point) {
        points.add(point);
    }

    public ArrayList<Point> getPoints() {
        return points;
    }


    public void drawStroke(GraphicsContext graphicsContext) {
        Color color = (Color) graphicsContext.getStroke();
        double strokeWidth = graphicsContext.getLineWidth();
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(2);

        for (Point p: points) {
            graphicsContext.lineTo(p.getX(), p.getY());
        }
        graphicsContext.stroke();
        graphicsContext.beginPath();

        graphicsContext.setLineWidth(strokeWidth);
        graphicsContext.setStroke(color);
    }

    public void clearStroke(GraphicsContext graphicsContext, double strokeWidth) {
        graphicsContext.setStroke(Color.color(0.95, 0.95, 0.95));
        graphicsContext.setLineWidth(strokeWidth + 3);
        for (Point point: points) {
            graphicsContext.lineTo(point.getX(), point.getY());
        }
        graphicsContext.stroke();
        graphicsContext.beginPath();
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(strokeWidth);

    }

    public void clearAll() {
        points.clear();
    }
}
