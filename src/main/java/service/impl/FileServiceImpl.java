package service.impl;

import com.google.gson.Gson;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Panel;
import service.FileService;

import java.io.*;

/**
 * @program: DrawingBoard
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-09-23 17:16
 **/
public class FileServiceImpl implements FileService  {

    @Override
    public void saveFile(Panel panel, Stage stage) throws IOException {


        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("保存图片");
        fileChooser.setInitialFileName("未命名文件" + ".draw");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("DRAW files (*.draw)", "*.draw");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);

        if (file == null) throw new IOException();

        if (file.exists()) {
            file.delete();
            file.createNewFile();
        }
        else {
            file.createNewFile();
        }

        FileWriter writer = new FileWriter(file);

        writer.write(new Gson().toJson(panel));

        writer.flush();
        writer.close();

    }

    @Override
    public Panel openFile(Stage stage) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("打开文件");

        File file = fileChooser.showOpenDialog(stage);

        FileReader fileReader = new FileReader(file);
        Panel panel = new Gson().fromJson(fileReader, Panel.class);

        return panel;
    }
}
