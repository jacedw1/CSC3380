package project.applications.PatientApps.SignupUtils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import project.Main;
import project.Utils.objects.Wrappers.PatientWrapper;
import project.Utils.objects.Wrappers.UserWrapper;

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
        if(Main.getUsernameHandler().containsUser(username,false)){
            resultLabel.setText("Signup Failed!");
            usernameLabel.setText("Username Already Exists");
            return;
        }
        UserWrapper wrapper = new PatientWrapper(username, password);
        ((PatientWrapper) wrapper).saveChanges();

        Main.setUserWrapper(wrapper);
        Parent root = FXMLLoader.load(getClass().getResource("../../GeneralApps/LoginUtils/loginscreen.fxml"));
        Main.getPrimaryStage().setTitle("Login Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }
}
