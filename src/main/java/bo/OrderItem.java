package bo;

public class OrderItem {
    private String ean;
    private int nrOfCopies;

    public OrderItem(String ean) {
        this.ean = ean;
        nrOfCopies = 1;
    }

    protected OrderItem(String ean, int nrOfCopies) {
        this.ean = ean;
        this.nrOfCopies = nrOfCopies;
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
