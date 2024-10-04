package ui;

public class CartItemInfo {
    private String ean;
    private int nrOfCopies;

    protected CartItemInfo(String ean) {
        this.ean = ean;
        this.nrOfCopies = 1;
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
}
