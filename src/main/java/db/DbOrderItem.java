package db;

import bo.OrderItem;

public class DbOrderItem extends OrderItem {
    public DbOrderItem(String ean) {
        super(ean);
    }

    protected DbOrderItem(String ean, int nrOfCopies) {
        super(ean, nrOfCopies);
    }
}
