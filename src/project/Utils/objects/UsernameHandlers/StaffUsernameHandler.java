package project.Utils.objects.UsernameHandlers;

import project.Main;
import project.Utils.storage.Queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffUsernameHandler extends UsernameHandler {

    private List<String> usernames = new ArrayList<>();

    public StaffUsernameHandler(){
        super();
        try {
            PreparedStatement stmt = Main.getDatabaseManager().getConnection().prepareStatement(Queries.GET_STAFF_USERNAMES);
            ResultSet rS = stmt.executeQuery();
            while(rS.next()){
                this.usernames.add(rS.getString(1));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        super.setUsernames(this.usernames);
    }

    public void reloadUsers(){
        try {
            PreparedStatement stmt = Main.getDatabaseManager().getConnection().prepareStatement(Queries.GET_STAFF_USERNAMES);
            ResultSet rS = stmt.executeQuery();
            this.usernames.clear();
            while(rS.next()){
                this.usernames.add(rS.getString(1));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        super.setUsernames(this.usernames);
    }
}
