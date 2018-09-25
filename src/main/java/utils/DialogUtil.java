package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @program: DrawingBoard
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-09-23 18:02
 **/
public abstract class DialogUtil {


    /**
     * 输入对话框
     * @param title
     * @param context
     * @param defaultValue
     * @return
     */
    public static String getStringFromTextInputDialog(String title, String context, String defaultValue) {
        TextInputDialog textInputDialog = new TextInputDialog(defaultValue);

        textInputDialog.setTitle(title);
        textInputDialog.setHeaderText(null);
        textInputDialog.setContentText(context);

        Optional<String> optionalDescription = textInputDialog.showAndWait();

        return optionalDescription.orElse(null);

    }


    /**
     * 消息对话框
     * @param title
     * @param context
     */
    public static void showInformationAlert(String title, String context) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(context);
        alert.show();
    }

    /**
     * 错误对话框
     * @param context 内容
     */
    public static void showErrorAlert(String context) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("发生错误");
        alert.setHeaderText(null);
        alert.setContentText(context);
        alert.show();
    }


    /**
     * 确认对话框
     * @param title
     * @param context
     */
    public static String showConfirmationAlert(String title, String context, List<String> options) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(context);

        alert.getButtonTypes().setAll(options.stream().map(ButtonType::new).collect(Collectors.toList()));

        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        return optionalButtonType.map(ButtonType::getText).orElse(null);
    }


}
