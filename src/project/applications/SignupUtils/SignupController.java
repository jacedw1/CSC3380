package project.applications.SignupUtils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.Main;
import project.other.StaffWrapper;

import java.io.IOException;

public class SignupController {

    @FXML
    private TextField userTxt;

    @FXML
    private PasswordField passTxt;

    @FXML
    private Label resultLabel;

    @FXML
    private Label usernameLabel;

    public void signupAction(MouseEvent event) throws IOException {
        String username = userTxt.getText();
        if(username.length() > 16){
            resultLabel.setText("Signup Failed!");
            usernameLabel.setText("Username Too Long");
            return;
        }
        String password = passTxt.getText();
        if(password.length() > 16){
            resultLabel.setText("Signup Failed!");
            usernameLabel.setText("Password Too Long");
            return;
        }
        if(Main.getUsernameHandler().containsUser(username)){
            resultLabel.setText("Signup Failed!");
            usernameLabel.setText("Username Already Exists");
            return;
        }
        StaffWrapper wrapper = new StaffWrapper(username, password, null, null);
        wrapper.saveChanges();

        ((Button) event.getSource()).getParent().getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../LoginUtils/loginscreen.fxml"));
        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
        primaryStage.show();
    }
}
