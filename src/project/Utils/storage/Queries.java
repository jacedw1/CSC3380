package project.Utils.storage;

public class Queries {

    public static String CREATE_QUESTIONNAIRE_TABLE = "CREATE TABLE IF NOT EXISTS pre_app_quest (" +
            "patient_id INTEGER PRIMARY KEY AUTO_INCREMENT," +
            "rounded_temp INTEGER," +
            "headaches VARCHAR(3)," +
            "coughing VARCHAR(3)," +
            "mucus VARCHAR(3)," +
            "swollen_lymphnodes VARCHAR(3)," +
            "soreness VARCHAR(3)," +
            "nausea VARCHAR(3)," +
            "common_cold VARCHAR(3)," +
            "flu VARCHAR(3)," +
            "bronchitis VARCHAR(3) );";

    public static String CREATE_PATIENT_TABLE = "CREATE TABLE IF NOT EXISTS user_data (" +
            "username VARCHAR(16) PRIMARY KEY," +
            "password VARCHAR(16)," +
            "last_name VARCHAR(32)," +
            "first_name VARCHAR(32)," +
            "next_appointment TIMESTAMP," +
            "patient_id INTEGER," +
            "FOREIGN KEY(patient_id) REFERENCES pre_app_quest(patient_id) ON DELETE SET NULL );";

    public static String CREATE_STAFF_TABLE = "CREATE TABLE IF NOT EXISTS staff_data (" +
            "username VARCHAR(16) PRIMARY KEY," +
            "password VARCHAR(16)," +
            "last_name VARCHAR(32)," +
            "first_name VARCHAR(32) );";

    public static String CREATE_SCHEDULE_TABLE = "CREATE TABLE IF NOT EXISTS master_schedule (" +
            "day DATE PRIMARY KEY," +
            "9AM_available BOOLEAN DEFAULT TRUE," +
            "10AM_available BOOLEAN DEFAULT TRUE," +
            "11AM_available BOOLEAN DEFAULT TRUE," +
            "12PM_available BOOLEAN DEFAULT TRUE," +
            "1PM_available BOOLEAN DEFAULT TRUE," +
            "2PM_available BOOLEAN DEFAULT TRUE," +
            "3PM_available BOOLEAN DEFAULT TRUE," +
            "4PM_available BOOLEAN DEFAULT TRUE," +
            "5PM_available BOOLEAN DEFAULT TRUE );";

    public static String ADD_NEW_STAFF_LOGIN = "REPLACE INTO staff_data (username, password) VALUES(?,?);";

    public static String ADD_STAFF_NAME = "UPDATE staff_data SET last_name = ?, first_name = ? WHERE username = ?;";

    public static String SAVE_STAFF = "REPLACE INTO staff_data (username, password, last_name, first_name) VALUES(?,?,?,?);";

    public static String GET_STAFF_USERNAMES = "SELECT username FROM staff_data;";

    public static String GET_STAFF = "SELECT * FROM staff_data WHERE username = ?;";

    public static String ADD_NEW_PATIENT_LOGIN = "REPLACE INTO user_data (username, password) VALUES(?,?);";

    public static String ADD_PATIENT_NAME = "UPDATE user_data SET last_name = ?, first_name = ? WHERE username = ?;";

    public static String ADD_PATIENT_ID = "UPDATE user_data SET patient_id = ? WHERE username = ?;";

    public static String SAVE_PATIENTS = "REPLACE INTO user_data (username, password, last_name, first_name, next_appointment) VALUES(?,?,?,?,?);";

    public static String GET_PATIENT_USERNAMES = "SELECT username FROM user_data;";

    public static String GET_PATIENT = "SELECT * FROM user_data WHERE username = ?;";
}

