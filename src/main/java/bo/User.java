package bo;

import db.DbUser;

import java.util.ArrayList;

public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Authority authority;
    private ArrayList<Media> cart;
    private ArrayList<Media> ordered;

    protected User(String email, String firstName, String lastName, String password, Authority authority) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.authority = authority;
        cart = new ArrayList<>();
        ordered = new ArrayList<>();
    }

    public static User getUser(String email) {
        return DbUser.selectUserByEmail(email);
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

    public ArrayList<Media> getCart() {
        return cart;
    }

    public ArrayList<Media> getOrdered() {
        return ordered;
    }
}
