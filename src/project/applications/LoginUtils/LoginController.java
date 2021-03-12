package project.applications.LoginUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import project.Main;
import project.other.Queries;
import project.other.StaffWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginController {

    //Controller classes will connect to the .fxml file and provide methods for the events to implement


    //@FXML allows you to register fields directly from .fxml file
    @FXML
    private TextField userTxt;

    @FXML
    private PasswordField passTxt;

    @FXML
    private Label resultLabel;

    @FXML
    private Label usernameLabel;

    public void loginAction(MouseEvent event) {

        //Set username to the username input
        String username = this.userTxt.getText();

        //Case: Username Does Not Exist
        if(!Main.getUsernameHandler().containsUser(username)){
            resultLabel.setText("Login Failed!");
            usernameLabel.setText("Incorrect Username");
            return;
        }

        StaffWrapper wrapper = null;
        try{
            //Connect to database
            Connection con = Main.getDatabaseManager().getConnection();

            //Prepare Query and define ResultSet
            PreparedStatement stmt = con.prepareStatement(Queries.GET_STAFF);
            stmt.setString(1,username);
            ResultSet rS = stmt.executeQuery();

            //Get necessary information from ResultSet
            rS.next();
            String password = rS.getString(2);
            String last_name = rS.getString(3);
            String first_name = rS.getString(4);

            //Create StaffWrapper from information given to avoid continually calling on the database
            wrapper = (new StaffWrapper(username, password, last_name, first_name));

            //Always close your statements
            stmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        //Case: Password Does Not Match
        if(!passTxt.getText().equals(wrapper.getPassword())){
            resultLabel.setText("Login Failed!");
            usernameLabel.setText("Incorrect Password");
            return;
        }

        //Set StaffWrapper from Main class (might change to a general UserWrapper later, this is just for testing
        Main.setStaffWrapper(wrapper);
        //Set color to lime because success! Default color red
        resultLabel.setStyle("-fx-text-fill: LIME");
        usernameLabel.setStyle("-fx-text-fill: LIME");
        resultLabel.setText("Login Succeeded to: ");
        usernameLabel.setText(username);
    }
}
