package ui;

public class MediaItemInfo {
    private MediaInfo media;
    private int nrOfCopies;

    public MediaItemInfo(MediaInfo media, int nrOfCopies) {
        this.media = media;
        this.nrOfCopies = nrOfCopies;
    }

    public MediaInfo getMedia() {
        return media;
    }

    public int getNrOfCopies() {
        return nrOfCopies;
    }

    public void setNrOfCopies(int nrOfCopies) {
        this.nrOfCopies = nrOfCopies;
    }
}
