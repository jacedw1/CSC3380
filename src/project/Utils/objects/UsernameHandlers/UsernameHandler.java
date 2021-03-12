package project.Utils.objects.UsernameHandlers;

import project.Main;
import project.Utils.storage.Queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsernameHandler {

    private List<String> usernames = new ArrayList<>();

    //This handler makes a database connection to get a list of all staff usernames to avoid needless DB Queries later. Will expand later.
    public UsernameHandler() {

    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

    public List<String> getStaff_usernames(){
        return this.usernames;
    }

    public boolean containsUser(String username){
        return usernames.contains(username);
    }

}
