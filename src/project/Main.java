package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.other.DatabaseManager;
import project.other.Queries;
import project.other.StaffWrapper;
import project.other.UsernameHandler;

import java.sql.PreparedStatement;


public class Main extends Application {

    private static DatabaseManager databaseManager;
    private static UsernameHandler usernameHandler;
    private static StaffWrapper staffWrapper = null;

    @Override
    public void init() throws Exception {
        super.init();

        databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/", "testing_java", "root", "kj071720");
        //initiate connection with database using your own data
//        databaseManager = new DatabaseManager(url, databaseName, username, password);
//        should look similar to this:
//        databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/", "testing_java", "root", "password");
//        gonna try to set this up remotely, but for now it's local

        //Creating table of staff
        PreparedStatement stmt = databaseManager.getConnection().prepareStatement(Queries.CREATE_STAFF_TABLE);
        stmt.execute();
        stmt.close();

        //Adding default staff member TurtleMD with password SmallTurtleHouse
        stmt = databaseManager.getConnection().prepareStatement(Queries.ADD_STAFF_LOGIN);
        //Replacing '?' in Query
        stmt.setString(1,"TurtleMD");
        stmt.setString(2,"SmallTurtleHouse");
        stmt.execute();
        stmt.close();

        //Setting up the UsernameHandler to avoid needless DB Queries after program starts
        usernameHandler = new UsernameHandler();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Read .fxml file and show it
        Parent root = FXMLLoader.load(getClass().getResource("applications/MainUtils/main.fxml"));
        primaryStage.setTitle("Main Screen");
        primaryStage.setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        databaseManager.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public static UsernameHandler getUsernameHandler() {
        return usernameHandler;
    }

    public static StaffWrapper getStaffWrapper() {
        return staffWrapper;
    }

    public static void setStaffWrapper(StaffWrapper staffWrapper) {
        Main.staffWrapper = staffWrapper;
    }
}
