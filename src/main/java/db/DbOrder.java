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

    public static void insertMediaOrder(ArrayList<EanItem> eanItems, String email) {
        try { 
            PreparedStatement insertMediaOrder = DbManager.getConnection().prepareStatement(INSERT_MEDIA_ORDER);
            insertMediaOrder.setString(2, email);
            String ean;
            for (EanItem eanItem : eanItems) {
                insertMediaOrder.setString(1, eanItem.getEan());
                insertMediaOrder.setString(3, String.valueOf(eanItem.getNrOfCopies()));
                insertMediaOrder.executeUpdate();
                DbMedia.updateNrOfCopies(eanItem.getEan(), eanItem.getNrOfCopies());
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
