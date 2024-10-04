package bo;

import db.DbOrder;

import java.util.ArrayList;

public class Order {
    private String orderNr;
    private ArrayList<OrderItem> items;
    private User user;
    private Status status;

    protected Order(String orderNr, ArrayList<OrderItem> items, User user, Status status) {
        this.orderNr = orderNr;
        this.items = items;
        this.user = user;
        this.status = status;
    }

    public Order(ArrayList<OrderItem> items, User user) {
        this.items = items;
        this.user = user;
    }

    public static void createOrder(ArrayList<EanItem> eanItems, String email) {
        DbOrder.insertMediaOrder(eanItems, email);
    }

    public String getOrderNr() {
        return orderNr;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public User getUser() {
        return user;
    }

    public Status getStatus() {
        return status;
    }
}
