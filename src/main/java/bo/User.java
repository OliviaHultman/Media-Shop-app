package bo;

import db.DbUser;

import java.util.ArrayList;

public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Authority authority;

    protected User(String email, String firstName, String lastName, String password, Authority authority) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.authority = authority;
    }

    protected User(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public static User getUser(String email, String password) {
        return DbUser.selectUser(email, password);
    }

    public boolean createUser() {
        return DbUser.insertUser(this);
    }

    public boolean changeUser() {
        return DbUser.updateUser(this);
    }

    public static ArrayList<DbUser> getUsers() {
        return DbUser.selectUsers();
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public Authority getAuthority() {
        return authority;
    }
}
