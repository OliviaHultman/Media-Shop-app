package bo;

import db.DbOrder;
import ui.EanItemInfo;
import ui.OrderInfo;
import ui.OrderItemInfo;
import ui.UserInfo;

import java.util.ArrayList;

public class OrderHandler {
    public static boolean createOrder(ArrayList<OrderItemInfo> orderItemsInfo, String email) {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemInfo orderItemInfo : orderItemsInfo) {
            orderItems.add(new OrderItem(orderItemInfo.getEan(), orderItemInfo.getNrOfCopies()));
        }
        Order order = new Order(orderItems, email);
        return order.createOrder();
    }

    public static ArrayList<OrderInfo> getOrders() {
        ArrayList<OrderInfo> ordersInfo = new ArrayList<>();
        ArrayList<OrderItemInfo> orderItemsInfo;
        OrderInfo orderInfo;
        for (Order order : Order.getOrders()) {
            orderInfo = new OrderInfo(order.getOrderNr(), order.getEmail(), order.getStatus());
            for (OrderItem orderItem : order.getItems()) {
                orderInfo.addItem(new OrderItemInfo(orderItem.getEan(), orderItem.getNrOfCopies()));
            }
            ordersInfo.add(orderInfo);
        }
        return ordersInfo;
    }
}
