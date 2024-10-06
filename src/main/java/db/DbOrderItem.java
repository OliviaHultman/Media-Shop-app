package db;

import bo.OrderItem;

public class DbOrderItem extends OrderItem {

    protected DbOrderItem(String ean, int nrOfCopies) {
        super(ean, nrOfCopies);
    }
}
