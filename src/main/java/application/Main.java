package application; /**
 * @program: DrawingBoard
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-09-19 19:05
 **/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MyScene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("css/background.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.setTitle("画板");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
