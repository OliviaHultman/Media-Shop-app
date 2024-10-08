package bo;

import db.DbUser;

import java.util.ArrayList;

public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;

    protected User(String email, String firstName, String lastName, String password, Role role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
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

    public boolean updateUser() {
        return DbUser.updateUser(this);
    }

    public static boolean deleteUser(String email) {
        return DbUser.deleteUser(email);
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

    public Role getRole() {
        return role;
    }
}
