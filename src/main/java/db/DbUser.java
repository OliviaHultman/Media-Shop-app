package db;

import bo.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbUser extends User {
    final static String INSERT_USER = "INSERT INTO User VALUES (?, ?, ?, ?, ?)";
    final static String UPDATE_USER = "UPDATE User SET User.firstName = ?, User.lastName = ?, User.password = ?, User.authority = ? WHERE User.email = ?";
    final static String SELECT_USER = "SELECT User.* FROM User WHERE User.email = ? AND User.password = ?";
    final static String SELECT_USERS = "SELECT User.* FROM User";

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

    public static boolean updateUser(User user) {
        try {
            PreparedStatement updateUser = DbManager.getConnection().prepareStatement(UPDATE_USER);
            System.out.print(user.getAuthority());
            System.out.print(user.getEmail());
            updateUser.setString(1, user.getFirstName());
            updateUser.setString(2, user.getLastName());
            updateUser.setString(3, user.getPassword());
            updateUser.setString(4, String.valueOf(user.getAuthority()));
            updateUser.setString(5, user.getEmail());
            System.out.println(updateUser.executeUpdate());
            return true;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static ArrayList<DbUser> selectUsers() {
        ResultSet result = null;
        ArrayList<DbUser> users = new ArrayList<>();
        try {
            PreparedStatement selectUsers = DbManager.getConnection().prepareStatement(SELECT_USERS);
            result = selectUsers.executeQuery();
            while (result.next()) {
                users.add(new DbUser(result.getString("email"), result.getString("firstName"),
                        result.getString("lastName"), result.getString("password"),
                        Authority.valueOf(result.getString("authority"))));
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
        return users;
    }
}
