package project.applications.MainUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    public void signupBtnAction(MouseEvent event) throws IOException {
        ((Button) event.getSource()).getParent().getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../SignupUtils/signupscreen.fxml"));
        primaryStage.setTitle("Signup Screen");
        primaryStage.setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
        primaryStage.show();
    }

    public void loginBtnAction(MouseEvent event) throws IOException {
        ((Button) event.getSource()).getParent().getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../LoginUtils/loginscreen.fxml"));
        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
        primaryStage.show();
    }
}
