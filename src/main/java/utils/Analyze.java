package utils;

/**
 * @program: DrawingBoard
 * @author: Lijie
 * @description: ${mark}
 * @create: 2018-09-23 17:23
 **/




import java.io.Serializable;

/**
 * 形状的枚举类型
 */

public enum Analyze implements Serializable {
    POLYGON("多边形", -1), NONE("无形状", 0), ROUND("圆形", 1),
    TRIANGLE("三角形", 2), RECTANGLE("矩形", 3), SQUARE("正方形", 4);

    public String mark;

    public int strokes;


    Analyze(String mark, int i) {
        this.strokes = i;
        this.mark = mark;
    }

}
