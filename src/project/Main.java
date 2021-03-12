package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.Utils.objects.UsernameHandlers.PatientUsernameHandler;
import project.Utils.objects.UsernameHandlers.StaffUsernameHandler;
import project.Utils.storage.DatabaseManager;
import project.Utils.storage.Queries;
import project.Utils.objects.Wrappers.StaffWrapper;
import project.Utils.objects.UsernameHandlers.UsernameHandler;

import java.sql.PreparedStatement;


public class Main extends Application {

    private static DatabaseManager databaseManager;
    private static UsernameHandler usernameHandler;
    private static StaffWrapper staffWrapper = null;
    private static Stage stage;
    private static boolean staff = true;

    @Override
    public void init() throws Exception {
        super.init();

        databaseManager = new DatabaseManager("jdbc:mysql://db4free.net:3306/", "turtlemd", "turtlemd", "SmallTurtleHouse");
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
        stmt = databaseManager.getConnection().prepareStatement(Queries.CREATE_STAFF_LOGIN);
        //Replacing '?' in Query
        stmt.setString(1,"TurtleMD");
        stmt.setString(2,"SmallTurtleHouse");
        stmt.execute();
        stmt.close();

        //Setting up the UsernameHandler to avoid needless DB Queries after program starts
        if(staff){
            usernameHandler = new StaffUsernameHandler();
        }
        else{
            usernameHandler = new PatientUsernameHandler();
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Main.stage = primaryStage;
        //Read .fxml file and show it
        Parent root = FXMLLoader.load(getClass().getResource("applications/GeneralApps/MainUtils/main.fxml"));
        stage.setTitle("Main Screen");
        stage.setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
        stage.setResizable(false);
        stage.show();
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

    public static Stage getPrimaryStage(){
        return stage;
    }
}
