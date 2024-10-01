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
    final static String SELECT_USER_BY_EMAIL = "SELECT User.* FROM User WHERE User.email = ?";

    private DbUser(String email, String firstName, String lastName, String password, Authority authority) {
        super(email, firstName, lastName, password, authority);
    }

    public static DbUser selectUserByEmail(String email) {
        ResultSet result = null;
        try {
            PreparedStatement selectUserByEmail = DbManager.getConnection().prepareStatement(SELECT_USER_BY_EMAIL);
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
                if (result != null) {
                    result.close();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
