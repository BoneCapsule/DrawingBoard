package utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Panel;
import sun.java2d.pipe.DrawImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.ParseException;

/**
 * @program: DrawingBoard
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-09-23 18:45
 **/
public class FileUtil {


    /**
     * 保存成图片文件
     * @param stage
     * @param canvas
     * @throws FileAlreadyExistsException
     * @throws IOException
     */
    public void saveFile(Stage stage, Canvas canvas) throws FileAlreadyExistsException, IOException {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("保存图片");

        fileChooser.setInitialFileName("未命名文件" + ".png");
        File file = fileChooser.showSaveDialog(stage);

        WritableImage image = canvas.snapshot(new SnapshotParameters(), null);

        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);


    }

    /**
     *  打开图片文件
     * @param stage
     * @throws FileNotFoundException
     * @throws ParseException
     */
    public Panel openFile(Stage stage, Canvas canvas) throws FileNotFoundException, ParseException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image", "*.png", "*.jpg", "*.jpeg"));
        fileChooser.setTitle("打开图片");// 打开图片

        Panel panel = new Panel();

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image("image/111.png");
            canvas.getGraphicsContext2D().drawImage(image, image.getWidth(), image.getHeight());
        }

        return panel;
    }

}
