package ui;

import bo.Status;

import java.util.ArrayList;

public class OrderInfo {
    private String orderNr;
    private ArrayList<OrderItemInfo> items;
    private UserInfo user;
    private Status status;

    protected OrderInfo(String orderNr, ArrayList<OrderItemInfo> items, UserInfo user, Status status) {
        this.orderNr = orderNr;
        this.items = items;
        this.user = user;
        this.status = status;
    }

    public String getOrderNr() {
        return orderNr;
    }

    public ArrayList<OrderItemInfo> getItems() {
        return items;
    }

    public UserInfo getUser() {
        return user;
    }

    public Status getStatus() {
        return status;
    }
}
