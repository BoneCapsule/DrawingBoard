package service;

import javafx.stage.Stage;
import model.Panel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * @program: DrawingBoard
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-09-23 17:16
 **/
public interface FileService {

    public void saveFile(Panel panel, Stage stage) throws IOException;

    public Panel openFile(Stage stage) throws FileNotFoundException;
}
