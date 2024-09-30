package bo;

import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Authority authority;
    private ArrayList<Media> cart;

    public User(String firstName, String lastName, String email, String password, Authority authority, ArrayList<Media> cart) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.authority = authority;
        this.cart = cart;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
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
}
