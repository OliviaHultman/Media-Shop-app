package db;

import bo.Authority;
import bo.Media;
import bo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbUser extends User {
    private Connection connection;
    private PreparedStatement selectUserByEmail;

    private DbUser(String firstName, String lastName, String email, String password, Authority authority) {
        super(firstName, lastName, email, password, authority);
        try {
            connection = DbManager.getConnection();
            selectUserByEmail = connection.prepareStatement("SELECT User.* FROM User WHERE User.email = ?");
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public DbUser selectUserByEmail(String email) {
        ResultSet result = null;
        try {
            selectUserByEmail.setString(1, email);
            result = selectUserByEmail.executeQuery();
             if (result.next()) {
                 return new DbUser(email, result.getString("firstName"),
                         result.getString("lastName"), result.getString("password"),
                         Authority.valueOf(result.getString("authority")));
             }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        finally {
            try {
                result.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
