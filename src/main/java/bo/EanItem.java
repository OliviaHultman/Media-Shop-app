package bo;

public class EanItem {
    private String ean;
    private int nrOfCopies;

    protected EanItem(String ean) {
        this.ean = ean;
        this.nrOfCopies = 1;
    }

    public EanItem(String ean, int nrOfCopies) {
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
