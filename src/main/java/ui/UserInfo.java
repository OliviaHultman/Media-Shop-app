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

    public UserInfo( String email, String firstName, String lastName, String password, Authority authority) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.authority = authority;
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
