package cn.tzy;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * @author 在下萝莉控
 */
public class Controller {

    @FXML
    public TextField path;

    @FXML
    public TextArea text;

    @FXML
    public TextField begin;

    @FXML
    public TextField end;

    //打开检查的窗口
    public void jiancha() throws Exception {
        StageManager.CONTROLLER.put("indexControl", this);
        String s = path.getText();//存网址的变量
        String s1 = begin.getText();//存开始位置的变量
        String s2 = end.getText();//存结束位置的变量

        StageManager.count.put("path", s);
        StageManager.count.put("begin", s1);
        StageManager.count.put("end", s2);

        Amain amain =new Amain();
        amain.start(new Stage());
    }

    public void test(ActionEvent event) throws IOException {
        String s = path.getText();//存网址的变量
        Boolean 开关 = true;

        String s1 = begin.getText();//存开始位置的变量

        String s2 = end.getText();//存结束位置的变量


        if (开关) {
            test04 test04 = new test04();
            try {
                test04.test(s, text, s1, s2);
            } catch (IOException e) {
                e.printStackTrace();
                alert("你输入的网站有问题，给老子滚");
            } catch (Exception in) {
                in.printStackTrace();
                alert("不知道为什么，反正就是爆了个错");
            }
        }
    }

    public static void alert( String str)  {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, str, new ButtonType("确定", ButtonBar.ButtonData.YES));
        alert.setHeaderText(null);
        alert.setTitle("提示");
        alert.show();
    }
}
