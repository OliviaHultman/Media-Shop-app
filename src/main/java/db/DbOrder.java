package db;

import bo.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbOrder extends Order {
    private final static String INSERT_MEDIA_ORDER = "INSERT INTO MediaOrder(media, user, nrOfCopies, status) VALUES (?, ?, ?, 'CONFIRMED')";

    public DbOrder(String orderNr, ArrayList<OrderItem> items, User user, Status status) {
        super(orderNr, items, user, status);
    }

    public static void insertMediaOrder(Order order) {
        try { 
            PreparedStatement insertMediaOrder = DbManager.getConnection().prepareStatement(INSERT_MEDIA_ORDER);
            insertMediaOrder.setString(2, order.getUser().getEmail());
            String ean;
            for (OrderItem item : order.getItems()) {
                ean = item.getMedia().getEan();
                insertMediaOrder.setString(1, ean);
                insertMediaOrder.setString(3, String.valueOf(item.getNrOfCopies()));
                insertMediaOrder.executeUpdate();
                DbMedia.updateNrOfCopies(ean);
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
