package project.other;

import project.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StaffWrapper {

    //This is a Wrapper class to handle the logged in Staff Member
    //Any changes made here will be saved using saveChanges() when the user logs out
    //Will also either save the username/password changes whenever made, or force the user to log back in after

    private String username;
    private String password;
    private String last_name;
    private String first_name;

    public StaffWrapper(String username, String password){
        this.username = username;
        this.password = password;
        this.last_name = null;
        this.first_name = null;
    }

    public StaffWrapper(String username, String password, String last_name, String first_name){
        this.username = username;
        this.password = password;
        this.last_name = last_name;
        this.first_name = first_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void saveChanges(){
        try{
            Connection con = Main.getDatabaseManager().getConnection();
            PreparedStatement stmt = con.prepareStatement(Queries.SAVE_STAFF);
            stmt.setString(1,this.username);
            stmt.setString(2,this.password);
            stmt.setString(3,this.last_name);
            stmt.setString(4,this.first_name);
            stmt.execute();
            stmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        Main.getUsernameHandler().reloadUsers();
    }
}


