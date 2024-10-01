package db;

import bo.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUser extends User {
    final static String SELECT_USER_BY_EMAIL = "SELECT User.* FROM User WHERE User.email = ?";
    final static String INSERT_NEW_BOOKED = "INSERT INTO BOOKED VALUES (?, ?, 1)";
    final static String DELETE_BOOKED = "DELETE FROM Booked WHERE Booked.media = ? AND Booked.user = ?";

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

    public static void insertNewBooked(String media, String user) {
        ResultSet result = null;
        try {
            PreparedStatement insertBooked = DbManager.getConnection().prepareStatement(INSERT_NEW_BOOKED);
            insertBooked.setString(1, media);
            insertBooked.setString(2, user);
            insertBooked.executeUpdate();
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
    }

    public static void deleteBooked(String media, String user) {
        ResultSet result = null;
        try {
            PreparedStatement deleteBooked = DbManager.getConnection().prepareStatement(DELETE_BOOKED);
            deleteBooked.setString(1, media);
            deleteBooked.setString(2, user);
            deleteBooked.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}
