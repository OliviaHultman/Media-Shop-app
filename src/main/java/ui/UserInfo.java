package ui;

import bo.Authority;
import bo.Media;

import java.util.ArrayList;

public class UserInfo {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Authority authority;
    private ArrayList<MediaInfo> cart;
    private ArrayList<MediaInfo> ordered;

    public UserInfo( String email, String firstName, String lastName, String password, Authority authority) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.authority = authority;
        cart = new ArrayList<>();
        ordered = new ArrayList<>();
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

    public ArrayList<MediaInfo> getCart() {
        return cart;
    }

    public ArrayList<MediaInfo> getOrdered() {
        return ordered;
    }
}
