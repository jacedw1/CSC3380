package project.Utils.objects.Wrappers;

import project.Main;
import project.Utils.objects.UsernameHandlers.PatientUsernameHandler;
import project.Utils.storage.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientWrapper extends UserWrapper {

    public PatientWrapper(String username, String password){
        super(username,password,null,null);
    }

    public PatientWrapper(String username, String password, String last_name, String first_name){
        super(username,password,last_name,first_name);
    }

    public void saveChanges(){
        try{
            Connection con = Main.getDatabaseManager().getConnection();
            PreparedStatement stmt = con.prepareStatement(Queries.SAVE_STAFF);
            stmt.setString(1,this.getUsername());
            stmt.setString(2,this.getPassword());
            stmt.setString(3,this.getLast_name());
            stmt.setString(4,this.getFirst_name());
            stmt.execute();
            stmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        ((PatientUsernameHandler) Main.getUsernameHandler()).reloadUsers();
    }
}
