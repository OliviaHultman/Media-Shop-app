package bo;

import ui.MediaInfo;

import java.util.ArrayList;

public class MediaHandler {
    public static ArrayList<MediaInfo> getMedias() {
        ArrayList<MediaInfo> mediasInfo = new ArrayList<>();
        for (Media media : Media.getMedias()) {
            mediasInfo.add(new MediaInfo(media.getEan(), media.getName(), media.getArtist(), media.getCategory(),
                    media.getLabel(), media.getGenre(), media.getReleased(), media.getDescription(), media.getPrice(),
                    media.getNrOfCopies()));
        }
        return mediasInfo;
    }

    public static ArrayList<MediaInfo> getUserMedias(String user) {
        ArrayList<MediaInfo> mediasInfo = new ArrayList<>();
        for (Media media : Media.getUserMedias(user)) {
            mediasInfo.add(new MediaInfo(media.getEan(), media.getName(), media.getArtist(), media.getCategory(),
                    media.getLabel(), media.getGenre(), media.getReleased(), media.getDescription(), media.getPrice(),
                    media.getNrOfCopies()));
        }
        return mediasInfo;
    }
}
