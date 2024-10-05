package bo;

import db.DbMedia;
import ui.EanItemInfo;
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
        ArrayList<MediaItemInfo> orderInfo = new ArrayList<>();
        for (int i = 0; i < orderItemsInfo.size(); i++) {
            orderInfo.add(new MediaItemInfo(mediasInfo.get(i), orderItemsInfo.get(i).getNrOfCopies()));
        }
        return orderInfo;
    }

    public static ArrayList<MediaInfo> getUserMedias(String user) {
        ArrayList<MediaInfo> mediasInfo = new ArrayList<>();
        for (Media media : Media.getUserMedias(user)) {
            mediasInfo.add(new MediaInfo(media.getEan(), media.getName(), media.getArtist(), media.getType(),
                    media.getLabel(), media.getGenre(), media.getPrice(),
                    media.getNrOfCopies()));
        }
        return mediasInfo;
    }
}
