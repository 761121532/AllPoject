package sample;

import action.test04;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * @author 在下萝莉控
 */
public class Controller {

    @FXML
    private TextField path;

    @FXML
    private TextArea text;


    public void test(ActionEvent actionEvent) throws IOException {
        String s=path.getText();
        test04 test04 =new test04();
        test04.test(s);
    }


    public void alert(ActionEvent event) throws IOException {
        String info="已经ok了";
        Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("确定", ButtonBar.ButtonData.YES));
        alert.setHeaderText(null);
        alert.setTitle("提示");
        alert.show();
    }



}
