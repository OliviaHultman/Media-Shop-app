package db;

import bo.*;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class DbMediaOrder extends MediaOrder {
    private final static String INSERT_MEDIA_ORDER = "INSERT INTO MediaOrder(media, user, nrOfCopies, status) VALUES (?, ?, ?, 'CONFIRMED')";

    public DbMediaOrder(String orderNr, ArrayList<MediaOrderItem> items, User user, Status status) {
        super(orderNr, items, user, status);
    }

    public static void insertMediaOrder(MediaOrder mediaOrder) {
        try { 
            PreparedStatement insertMediaOrder = DbManager.getConnection().prepareStatement(INSERT_MEDIA_ORDER);
            insertMediaOrder.setString(2, mediaOrder.getUser().getEmail());
            for (MediaOrderItem item : mediaOrder.getItems()) {
                insertMediaOrder.setString(1, item.getMedia().getEan());
                insertMediaOrder.setString(3, String.valueOf(item.getNrOfCopies()));
                insertMediaOrder.executeUpdate();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
