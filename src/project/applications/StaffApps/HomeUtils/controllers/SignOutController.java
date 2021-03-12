package project.applications.StaffApps.HomeUtils.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.Main;

import java.io.IOException;

public class SignOutController {

    public void confirmBtnAction(MouseEvent event) throws IOException  {
        Main.getStaffWrapper().saveChanges();
        Main.getPrimaryStage().hide();
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

        Parent root = FXMLLoader.load(getClass().getResource("../../../GeneralApps/CloseUtils/closescreen.fxml"));
        stage.setTitle("Termination Screen");
        stage.setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));

    }

    public void backBtnAction(MouseEvent event) {
    }
}
