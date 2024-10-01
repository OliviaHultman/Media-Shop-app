package bo;

import ui.UserInfo;

public class UserHandler {
    public static UserInfo getUser(String email) {
        User user = User.getUser(email);
        return new UserInfo(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getAuthority());
    }
}
