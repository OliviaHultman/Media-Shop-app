package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbManager {
    private static DbManager instance;
    private Connection connection;

    private DbManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String address = "localhost:3306";
            String db = "Webshop";
            String user = "client";
            String password = "client";
            connection = DriverManager.getConnection("jdbc:mysql://" + address + "/" + db + "?UseClientEnc=UTF8", user, password);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static DbManager getInstance() {
        if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }

    public static Connection getConnection() {
        return getInstance().connection;
    }
}
