package ui;

public class EanItemInfo {
    private String ean;
    private int nrOfCopies;

    protected EanItemInfo(String ean) {
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

    public void setNrOfCopies(int nrOfCopies) {
        this.nrOfCopies = nrOfCopies;
    }
}
