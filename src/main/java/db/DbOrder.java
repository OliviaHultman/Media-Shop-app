package db;

import bo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbOrder extends Order {
    private final static String INSERT_MEDIA_ORDER = "INSERT INTO MediaOrder(media, user, nrOfCopies, status) VALUES (?, ?, ?, 'CONFIRMED')";

    public DbOrder(String orderNr, ArrayList<OrderItem> items, User user, Status status) {
        super(orderNr, items, user, status);
    }

    public static void insertMediaOrder(ArrayList<EanItem> eanItems, String email) {
        Connection connection = DbManager.getConnection();
        try {
            PreparedStatement insertMediaOrder = connection.prepareStatement(INSERT_MEDIA_ORDER);
            insertMediaOrder.setString(2, email);
            connection.setAutoCommit(false);
            for (EanItem eanItem : eanItems) {
                insertMediaOrder.setString(1, eanItem.getEan());
                insertMediaOrder.setString(3, String.valueOf(eanItem.getNrOfCopies()));
                insertMediaOrder.executeUpdate();
                DbMedia.updateNrOfCopies(eanItem.getEan(), eanItem.getNrOfCopies());
            }
            connection.commit();
        }
        catch (SQLException exception) {
            try {
                connection.rollback();
            }
            catch (SQLException exception2) {

            }
        }
        finally {
            try {
                connection.setAutoCommit(true);
            }
            catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}
