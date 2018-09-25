package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.ArrayList;

/**
 * @program: DrawingBoard
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-09-20 21:22
 **/
public class Panel {

    private ArrayList<Stroke> strokes = new ArrayList<Stroke>();

    private String description;

    public Panel(ArrayList<Stroke> strokes) {
        this.strokes = strokes;
    }

    public Panel() {
        strokes = new ArrayList<Stroke>();
    }

    /**
     * 撤销上一笔
     */
    public void revocationStroke(GraphicsContext graphicsContext) {
        if (strokes.size() == 0) return;

        Stroke stroke = strokes.get(strokes.size() - 1);
        stroke.clearStroke(graphicsContext, graphicsContext.getLineWidth());
        strokes.remove(strokes.size() - 1);

        //重绘
        storeStroke(graphicsContext);

    }


    /**
     * 清空
     * @param graphicsContext
     */
    public void clearAll(GraphicsContext graphicsContext) {
        if (strokes.size() == 0) return;

        for (Stroke stroke : strokes) {
            stroke.clearStroke(graphicsContext, graphicsContext.getLineWidth());
        }

        strokes.clear();
    }

    public void storeStroke(GraphicsContext graphicsContext) {
        for (Stroke s : strokes) {
            s.drawStroke(graphicsContext);
        }
    }



    public void addStroke(Stroke stroke) {
        if (strokes != null) {
            this.strokes.add(stroke);
        }
    }

    public void addImage(Image image) {
        ImageView imageView = new ImageView();

    }

    public boolean isIllegal() {
        return description == null || strokes == null;
    }

    public ArrayList<Stroke> getStrokes() {
        return strokes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
