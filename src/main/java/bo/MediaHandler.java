package bo;

import db.DbMedia;
import ui.EanItemInfo;
import ui.MediaInfo;
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

    public static ArrayList<OrderItemInfo> getCartMedias(ArrayList<EanItemInfo> eanItemsInfo) {
        ArrayList<String> eans = new ArrayList<>();
        for (EanItemInfo eanItemInfo : eanItemsInfo) {
            eans.add(eanItemInfo.getEan());
        }
        ArrayList<MediaInfo> mediasInfo = convertToMediaInfo(Media.getCartMedias(eans));
        ArrayList<OrderItemInfo> orderInfo = new ArrayList<>();
        for (int i = 0; i < eanItemsInfo.size(); i++) {
            orderInfo.add(new OrderItemInfo(mediasInfo.get(i), eanItemsInfo.get(i).getNrOfCopies()));
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
