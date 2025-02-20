package bo;

import db.DbUser;
import ui.UserInfo;

import java.util.ArrayList;

public class UserHandler {
    public static UserInfo getUser(String email, String password) {
        User user = User.getUser(email, password);
        if (user != null) {
            return new UserInfo(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword(),
                    user.getRole());
        }
        else {
            return null;
        }
    }

    public static boolean createUser(UserInfo userInfo) {
        User user = new User(userInfo.getEmail(), userInfo.getFirstName(), userInfo.getLastName(), userInfo.getPassword(), userInfo.getRole());
        return user.createUser();
    }

    public static boolean updateUser(UserInfo userInfo) {
        User user = new User(userInfo.getEmail(), userInfo.getFirstName(), userInfo.getLastName(), userInfo.getPassword(), userInfo.getRole());
        return user.updateUser();
    }

    public static boolean deleteUser(String email) {
        return User.deleteUser(email);
    }

    public static ArrayList<UserInfo> getUsers() {
        ArrayList<DbUser> users = User.getUsers();
        ArrayList<UserInfo> usersInfo = new ArrayList<>();
        for (User user : users) {
            usersInfo.add(new UserInfo(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getRole()));
        }
        return usersInfo;
    }

}
