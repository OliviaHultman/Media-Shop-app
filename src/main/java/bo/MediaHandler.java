package bo;

import db.DbMedia;
import ui.MediaInfo;
import ui.MediaItemInfo;
import ui.OrderItemInfo;

import java.util.ArrayList;

public class MediaHandler {
    private static ArrayList<MediaInfo> convertToMediaInfo(ArrayList<DbMedia> medias) {
        ArrayList<MediaInfo> mediasInfo = new ArrayList<>();
        for (Media media : medias) {
            mediasInfo.add(new MediaInfo(media.getEan(), media.getName(), media.getArtist(), media.getType(),
                    media.getLabel(), media.getGenre(), media.getPrice(),
                    media.getNrOfCopies()));
        }
        return mediasInfo;
    }

    public static ArrayList<MediaInfo> getMedias() {
        return convertToMediaInfo(Media.getMedias());
    }

    public static ArrayList<MediaItemInfo> getCartMedias(ArrayList<OrderItemInfo> orderItemsInfo) {
        ArrayList<String> eans = new ArrayList<>();
        for (OrderItemInfo orderItemInfo : orderItemsInfo) {
            eans.add(orderItemInfo.getEan());
        }
        ArrayList<MediaInfo> mediasInfo = convertToMediaInfo(Media.getCartMedias(eans));
        ArrayList<MediaItemInfo> mediaItemsInfo = new ArrayList<>();
        for (int i = 0; i < orderItemsInfo.size(); i++) {
            mediaItemsInfo.add(new MediaItemInfo(mediasInfo.get(i), orderItemsInfo.get(i).getNrOfCopies()));
        }
        return mediaItemsInfo;
    }

    public static boolean addMedia(MediaInfo mediaInfo) {
        Media media = new Media(mediaInfo.getEan(), mediaInfo.getName(), mediaInfo.getArtist(), mediaInfo.getType(), mediaInfo.getLabel(),
                mediaInfo.getGenre(), mediaInfo.getPrice(), mediaInfo.getNrOfCopies());
        return media.addMedia();
    }

    public static boolean updateMedia(MediaInfo mediaInfo) {
        Media media = new Media(mediaInfo.getEan(), mediaInfo.getName(), mediaInfo.getArtist(), mediaInfo.getType(), mediaInfo.getLabel(),
                mediaInfo.getGenre(), mediaInfo.getPrice(), mediaInfo.getNrOfCopies());
        return media.updateMedia();
    }

    public static boolean deleteMedia(String ean) {
        return Media.deleteMedia(ean);
    }

    public static ArrayList<String> getGenres() {
        return Media.getGenres();
    }

    public static boolean addGenre(String genre) {
        return Media.addGenre(genre);
    }

    public static boolean deleteGenre(String genre) {
        return Media.deleteGenre(genre);
    }
}
