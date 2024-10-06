package bo;

public class OrderItem {
    private String ean;
    private int nrOfCopies;

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
}
