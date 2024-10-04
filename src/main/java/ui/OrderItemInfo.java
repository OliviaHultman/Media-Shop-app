package ui;

import bo.Media;

public class OrderItemInfo {
    private MediaInfo media;
    private int nrOfCopies;

    public OrderItemInfo(MediaInfo media, int nrOfCopies) {
        this.media = media;
        this.nrOfCopies = nrOfCopies;
    }

    public MediaInfo getMedia() {
        return media;
    }

    public int getNrOfCopies() {
        return nrOfCopies;
    }
}
