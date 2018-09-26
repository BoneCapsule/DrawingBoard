package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Panel;
import model.Stroke;
import service.AnalyzeService;
import service.FileService;
import service.impl.AnalyzeServiceImpl;
import service.impl.FileServiceImpl;
import utils.DialogUtil;
import utils.FileUtil;
import model.Point;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.*;
/**
 * @program: DrawingBoard
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-09-19 19:07
 **/
public class Controller implements Initializable {

    private Panel panel;

    private Stage stage;

    @FXML
    private Canvas canvas;

    @FXML
    private BorderPane borderPane;

    private  GraphicsContext graphicsContext;

    private Stroke stroke;

    private AnalyzeService analyzeService;

    private FileService fileService;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Slider slider;

    @FXML
    private Label markLabel;

    public void initialize(URL location, ResourceBundle resources) {
        analyzeService = new AnalyzeServiceImpl();
        fileService = new FileServiceImpl();

        panel = new Panel();
        stroke = new Stroke(); //当前笔画
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(2);

        colorPicker.setOnAction((ActionEvent e) -> {
            graphicsContext.setStroke(colorPicker.getValue());
        });

        markLabel.setText(panel.getDescription());
    }

    /**
     * 生成新文件（未命名）对应的界面
     */
    @FXML
    private void createNewFile() {
        panel = new Panel();
        graphicsContext.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
    }


    @FXML
    public void openFile() {
        try {
            panel = fileService.openFile(stage);

            if (panel != null) {
                graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                panel.storeStroke(graphicsContext);
            }

//            FileUtil fileUtil = new FileUtil();
//            fileUtil.openFile(stage, canvas);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void saveFile() {
        try {
            fileService.saveFile(panel, stage);

            DialogUtil.showInformationAlert("提示", "保存文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            DialogUtil.showErrorAlert(e.getLocalizedMessage());
        }
    }

    @FXML
    public void undo() {
        panel.revocationStroke(graphicsContext);
    }

    @FXML
    public  void clear() {
       panel.clearAll(graphicsContext);
       panel = new Panel();
    }


    @FXML
    public void analyze() {
        String mark = analyzeService.getMark(panel.getStrokes().size());
        DialogUtil.showInformationAlert("分析", mark);
        panel.setDescription(mark);
        markLabel.setText(panel.getDescription());
    }

    @FXML
    public void getAbout() {
        DialogUtil.showInformationAlert("关于", "画板软件\n版本 1.0\n识别机制: 使用简单的基于笔画数的识别\n" +
                "圆形: 一笔\n" + "三角形: 两笔\n" + "矩形: 三笔\n" + "正方形：四笔\n" + "其他多边形或无形状\n" +
                "Copyright © 2018 cst.\n保留一切权利。");
    }



    /**
     * 按下鼠标
     *
     * @param e 鼠标事件
     */
    @FXML
    private void onMousePressedListener(MouseEvent e) {
        Image image = new Image("image/pen.png");
        ImageCursor imageCursor = new ImageCursor(image, image.getWidth() - 33.5, image.getHeight());
        canvas.setCursor(imageCursor);
    }

    /**
     * 拖拽鼠标
     * @param e 鼠标事件
     */
    @FXML
    private void onMouseDraggedListener(MouseEvent e) {
        Image image = new Image("image/pen.png");
        ImageCursor imageCursor = new ImageCursor(image, image.getWidth() - 33.5, image.getHeight());
        canvas.setCursor(imageCursor);

        graphicsContext.lineTo(e.getX(),e.getY());
        graphicsContext.stroke();

        Point point = new Point(e.getX(), e.getY());
        stroke.addPoints(point);
    }

    /**
     * 释放鼠标
     *
     * @param e
     */
    @FXML
    private void onMouseReleaseListener(MouseEvent e) {
        graphicsContext.beginPath();
        Stroke tmpStroke = new Stroke(stroke);//对当前笔画进行深拷贝
        stroke.clearAll();
        panel.addStroke(tmpStroke);
    }

    @FXML
    public void revocation() {
        undo();
    }

    @FXML
    public void recover() {

    }

    @FXML
    public void clearAll() {
        clear();
    }

    @FXML
    public void saveImage() {
        FileUtil fileUtil = new FileUtil();
        try {
            fileUtil.saveFile(stage, canvas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void redo() {
        
    }

    @FXML
    public void exit() {
//        stage.close();
        Platform.exit();
    }
}
