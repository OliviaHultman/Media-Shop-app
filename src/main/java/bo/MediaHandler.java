package bo;

import db.DbMedia;
import ui.MediaInfo;

import java.util.ArrayList;

public class MediaHandler {
    private static ArrayList<MediaInfo> convertToMediaInfo(ArrayList<DbMedia> medias) {
        ArrayList<MediaInfo> mediasInfo = new ArrayList<>();
        for (Media media : medias) {
            mediasInfo.add(new MediaInfo(media.getEan(), media.getName(), media.getArtist(), media.getCategory(),
                    media.getLabel(), media.getGenre(), media.getReleased(), media.getDescription(), media.getPrice(),
                    media.getNrOfCopies()));
        }
        return mediasInfo;
    }

    public static ArrayList<MediaInfo> getMedias() {
        return convertToMediaInfo(Media.getMedias());
    }

    public static ArrayList<MediaInfo> getCartMedias(ArrayList<String> eans) {
        return convertToMediaInfo(Media.getCartMedias(eans));
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
