package bo;

import ui.EanItemInfo;
import ui.UserInfo;

import java.util.ArrayList;

public class OrderHandler {
    public static void createOrder(ArrayList<EanItemInfo> eanItemsInfo, UserInfo userInfo) {
        ArrayList<EanItem> eanItems = new ArrayList<>();
        for (EanItemInfo eanItemInfo : eanItemsInfo) {
            eanItems.add(new EanItem(eanItemInfo.getEan(), eanItemInfo.getNrOfCopies()));
        }
        Order.createOrder(eanItems, userInfo.getEmail());
    }
}
