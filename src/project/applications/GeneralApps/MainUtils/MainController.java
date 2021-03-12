package project.applications.GeneralApps.MainUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import project.Main;

import java.io.IOException;

public class MainController {

    public void signupBtnAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../StaffApps/SignupUtils/signupscreen.fxml"));
        Main.getPrimaryStage().setTitle("Signup Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }

    public void loginBtnAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../StaffApps/LoginUtils/loginscreen.fxml"));
        Main.getPrimaryStage().setTitle("Login Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }
}
