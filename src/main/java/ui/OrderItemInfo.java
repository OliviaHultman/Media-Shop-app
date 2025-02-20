package ui;

import bo.Media;

public class OrderItemInfo {
    private String ean;
    private int nrOfCopies;

    public OrderItemInfo(String ean, int nrOfCopies) {
        this.ean = ean;
        this.nrOfCopies = nrOfCopies;
    }

    public OrderItemInfo(String ean) {
        this.ean = ean;
        nrOfCopies = 1;
    }

    public String getEan() {
        return ean;
    }

    public int getNrOfCopies() {
        return nrOfCopies;
    }

    public void incrementNrOfCopies() {
        nrOfCopies++;
    }

    public void setNrOfCopies(int nrOfCopies) {
        this.nrOfCopies = nrOfCopies;
    }
}
