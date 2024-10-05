package ui;

import bo.Status;

import java.util.ArrayList;

public class OrderInfo {
    private int orderNr;
    private ArrayList<OrderItemInfo> items;
    private String email;
    private Status status;

    protected OrderInfo(int orderNr, ArrayList<OrderItemInfo> items, String email, Status status) {
        this.orderNr = orderNr;
        this.items = items;
        this.email = email;
        this.status = status;
    }

    public OrderInfo(int orderNr, String email, Status status) {
        this.orderNr = orderNr;
        items = new ArrayList<>();
        this.email = email;
        this.status = status;
    }

    public void addItem(OrderItemInfo orderItem) {
        items.add(orderItem);
    }

    public int getOrderNr() {
        return orderNr;
    }

    public ArrayList<OrderItemInfo> getItems() {
        return items;
    }

    public String getEmail() {
        return email;
    }

    public Status getStatus() {
        return status;
    }
}
