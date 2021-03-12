package project.other;

import project.applications.Main;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsernameHandler {

    private List<String> staff_usernames = new ArrayList<>();

    //This handler makes a database connection to get a list of all staff usernames to avoid needless DB Queries later. Will expand later.
    public UsernameHandler() {
        try {
            PreparedStatement stmt = Main.getDatabaseManager().getConnection().prepareStatement(Queries.GET_STAFF_USERNAMES);
            ResultSet rS = stmt.executeQuery();
            while(rS.next()){
                this.staff_usernames.add(rS.getString(1));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<String> getStaff_usernames(){
        return this.staff_usernames;
    }

    public boolean containsUser(String username){
        return staff_usernames.contains(username);
    }
}
