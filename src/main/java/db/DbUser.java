package db;

import bo.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUser extends User {
    final static String INSERT_USER = "INSERT INTO User VALUES (?, ?, ?, ?, ?)";
    final static String UPDATE_USER = "UPDATE User SET User.email = ?, User.firstName = ?, User.lastName = ?, User.password = ?, User.authority = ? WHERE User.email = ?";
    final static String SELECT_USER = "SELECT User.* FROM User WHERE User.email = ? AND User.password = ?";
    final static String SELECT_BOOKED = "SELECT Booked.nrOfCopies FROM Booked WHERE WHERE Booked.media = ? AND Booked.user = ?";
    final static String INSERT_BOOKED = "INSERT INTO Booked VALUES (?, ?, 1)";
    final static String UPDATE_BOOKED = "UPDATE Booked SET Booked.nrOfCopies = ? WHERE Booked.media = ? AND Booked.user = ?";
    final static String DELETE_BOOKED = "DELETE FROM Booked WHERE Booked.media = ? AND Booked.user = ?";

    private DbUser(String email, String firstName, String lastName, String password, Authority authority) {
        super(email, firstName, lastName, password, authority);
    }

    public static DbUser selectUser(String email, String password) {
        ResultSet result = null;
        try {
            PreparedStatement selectUser = DbManager.getConnection().prepareStatement(SELECT_USER);
            selectUser.setString(1, email);
            selectUser.setString(2, password);
            result = selectUser.executeQuery();
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

    public static boolean insertUser(User user) {
        try {
            PreparedStatement insertUser = DbManager.getConnection().prepareStatement(INSERT_USER);
            insertUser.setString(1, user.getEmail());
            insertUser.setString(2, user.getFirstName());
            insertUser.setString(3, user.getLastName());
            insertUser.setString(4, user.getPassword());
            insertUser.setString(5, String.valueOf(user.getAuthority()));
            insertUser.executeUpdate();
            return true;
        }
        catch (SQLException exception) {
            return false;
        }
    }

    public static void updateUser(User user, String oldEmail) {
        try {
            PreparedStatement updateUser = DbManager.getConnection().prepareStatement(UPDATE_USER);
            updateUser.setString(1, user.getEmail());
            updateUser.setString(2, user.getFirstName());
            updateUser.setString(3, user.getLastName());
            updateUser.setString(4, user.getPassword());
            updateUser.setString(5, String.valueOf(user.getAuthority()));
            updateUser.setString(6, oldEmail);
            updateUser.executeUpdate();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
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
