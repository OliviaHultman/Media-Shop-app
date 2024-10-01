package db;

import bo.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUser extends User {
    final static String SELECT_USER_BY_EMAIL = "SELECT User.* FROM User WHERE User.email = ?";
    final static String SELECT_BOOKED = "SELECT Booked.NrOfCopies FROM Booked WHERE WHERE Booked.media = ? AND Booked.user = ?";
    final static String INSERT_BOOKED = "INSERT INTO Booked VALUES (?, ?, 1)";
    final static String UPDATE_BOOKED = "UPDATE Booked SET Booked.NrOfCopies = ? WHERE Booked.media = ? AND Booked.user = ?";
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

    public static void addBooked(String media, String user) {
        ResultSet result = null;
        try {
            PreparedStatement selectBooked = DbManager.getConnection().prepareStatement(SELECT_BOOKED);
            selectBooked.setString(1, media);
            selectBooked.setString(2, user);
            result = selectBooked.executeQuery();
            if (result.next()) {
                PreparedStatement updateBooked = DbManager.getConnection().prepareStatement(UPDATE_BOOKED);
                int nrOfCopies = result.getInt("nrOfCopies") + 1;
                updateBooked.setString(1, String.valueOf(nrOfCopies));
                updateBooked.setString(2, media);
                updateBooked.setString(3, user);
                updateBooked.executeUpdate();
            }
            else {
                PreparedStatement insertBooked = DbManager.getConnection().prepareStatement(INSERT_BOOKED);
                insertBooked.setString(1, media);
                insertBooked.setString(2, user);
                insertBooked.executeUpdate();
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
    }

    public static void deleteBooked(String media, String user) {
        try {
            PreparedStatement deleteBooked = DbManager.getConnection().prepareStatement(DELETE_BOOKED);
            deleteBooked.setString(1, media);
            deleteBooked.setString(2, user);
            deleteBooked.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
