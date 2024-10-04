package bo;

public class OrderItem {
    private Media media;
    private int nrOfCopies;

    protected OrderItem(Media media, int nrOfCopies) {
        this.media = media;
        this.nrOfCopies = nrOfCopies;
    }

    public Media getMedia() {
        return media;
    }

    public int getNrOfCopies() {
        return nrOfCopies;
    }
}
