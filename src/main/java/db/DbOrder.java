package db;

import bo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbOrder extends Order {
    private final static String INSERT_ORDER = "INSERT INTO MediaOrder VALUES (?, ?, ?, ?, 'CONFIRMED')";
    private final static String SELECT_DISTINCT_ORDER_NRS = "SELECT DISTINCT MediaOrder.orderNr FROM MediaOrder";
    private final static String SELECT_ORDER = "SELECT MediaOrder.* FROM MediaOrder WHERE MediaOrder.orderNr = ?";
    private final static String UPDATE_STATUS = "UPDATE MediaOrder SET MediaOrder.Status = ? WHERE MediaOrder.orderNr = ?";

    private DbOrder(int orderNr, String email, Status status) {
        super(orderNr, email, status);
    }

    public static boolean insertOrder(Order order) {
        Connection connection = DbManager.getConnection();
        try {
            PreparedStatement insertOrder = connection.prepareStatement(INSERT_ORDER);
            insertOrder.setInt(1, order.getOrderNr());
            insertOrder.setString(3, order.getEmail());
            connection.setAutoCommit(false);
            for (OrderItem orderItem : order.getItems()) {
                if (!DbMedia.updateNrOfCopies(orderItem.getEan(), orderItem.getNrOfCopies())) {
                    connection.rollback();
                    return false;
                }
                insertOrder.setString(2, orderItem.getEan());
                insertOrder.setString(4, String.valueOf(orderItem.getNrOfCopies()));
                insertOrder.executeUpdate();

            }
            connection.commit();
            return true;
        }
        catch (SQLException exception) {
            try {
                connection.rollback();
            }
            catch (SQLException exception2) {
            }
            finally {
                exception.printStackTrace();
                return false;
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

    public static ArrayList<Integer> selectDistinctOrderNr() {
        ResultSet result = null;
        ArrayList<Integer> orderNrs = new ArrayList<>();
        try {
            PreparedStatement selectDistinctOrderNrs = DbManager.getConnection().prepareStatement(SELECT_DISTINCT_ORDER_NRS);
            result = selectDistinctOrderNrs.executeQuery();
            while (result.next()) {
                orderNrs.add(result.getInt("orderNr"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return orderNrs;
    }

    public static ArrayList<DbOrder> selectOrders() {
        ResultSet result = null;
        ArrayList<DbOrder> orders = new ArrayList<>();
        try {
            ArrayList<Integer> orderNrs = selectDistinctOrderNr();
            PreparedStatement selectOrder = DbManager.getConnection().prepareStatement(SELECT_ORDER);
            for (int orderNr : orderNrs) {
                selectOrder.setInt(1, orderNr);
                result = selectOrder.executeQuery();
                if (result.next()) {
                    DbOrder order = new DbOrder(orderNr, result.getString("user"), Status.valueOf(result.getString("status")));
                    do {
                        order.addItem(new DbOrderItem(result.getString("media"), result.getInt("nrOfCopies")));
                    } while (result.next());
                    orders.add(order);
                }
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return orders;
    }

    public static boolean updateStatus(Order order) {
        Connection connection = DbManager.getConnection();
        try {
            PreparedStatement updateStatus = connection.prepareStatement(UPDATE_STATUS);
            updateStatus.setString(1, String.valueOf(order.getStatus()));
            updateStatus.setInt(2, order.getOrderNr());
            connection.setAutoCommit(false);
            updateStatus.executeUpdate();
            connection.commit();
            return true;
        }
        catch (SQLException exception) {
            try {
                connection.rollback();
            }
            catch (SQLException exception2) {
            }
            finally {
                exception.printStackTrace();
                return false;
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
