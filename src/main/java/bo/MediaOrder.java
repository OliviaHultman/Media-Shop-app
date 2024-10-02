package bo;

import java.util.ArrayList;

public class MediaOrder {
    private String orderNr;
    private ArrayList<MediaOrderItem> items;
    private User user;
    private Status status;

    public MediaOrder(String orderNr, ArrayList<MediaOrderItem> items, User user, Status status) {
        this.orderNr = orderNr;
        this.items = items;
        this.user = user;
        this.status = status;
    }

    public String getOrderNr() {
        return orderNr;
    }

    public ArrayList<MediaOrderItem> getItems() {
        return items;
    }

    public User getUser() {
        return user;
    }

    public Status getStatus() {
        return status;
    }
}
