package bo;

import db.DbOrder;

import java.util.ArrayList;

public class Order {
    private int orderNr;
    private ArrayList<OrderItem> items;
    private String email;
    private Status status;
    private static int nextOrderNr;

    protected Order(int orderNr, ArrayList<OrderItem> items, String email, Status status) {
        this.orderNr = orderNr;
        this.items = items;
        this.email = email;
        this.status = status;
    }

    protected Order(int orderNr, String email, Status status) {
        this.orderNr = orderNr;
        items = new ArrayList<>();
        this.email = email;
        this.status = status;
    }

    protected Order(int orderNr, Status status) {
        this.orderNr = orderNr;
        this.status = status;
    }

    protected Order(ArrayList<OrderItem> items, String email) {
        if (nextOrderNr == 0) {
            nextOrderNr = DbOrder.selectHighestOrderNr() + 1;
        }
        orderNr = nextOrderNr;
        nextOrderNr++;
        this.items = items;
        this.email = email;
        status = Status.CONFIRMED;
    }

    public boolean createOrder() {
        return DbOrder.insertOrder(this);
    }

    public static ArrayList<DbOrder> getOrders() {
        return DbOrder.selectOrders();
    }

    public boolean changeStatus() {
        return DbOrder.updateStatus(this);
    }

    public int getOrderNr() {
        return orderNr;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public String getEmail() {
        return email;
    }

    public Status getStatus() {
        return status;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }
}
