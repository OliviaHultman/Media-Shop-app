package bo;

import java.util.ArrayList;

public class MediaOrderItem {
    private Media media;
    private int nrOfCopies;

    protected MediaOrderItem(Media media, int nrOfCopies) {
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
