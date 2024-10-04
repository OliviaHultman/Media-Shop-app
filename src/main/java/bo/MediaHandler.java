package bo;

import db.DbMedia;
import ui.CartItemInfo;
import ui.MediaInfo;
import ui.OrderItemInfo;

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

    public static ArrayList<OrderItemInfo> getCartMedias(ArrayList<CartItemInfo> cartInfo) {
        ArrayList<String> eans = new ArrayList<>();
        for (CartItemInfo cartItemInfo : cartInfo) {
            eans.add(cartItemInfo.getEan());
        }
        ArrayList<MediaInfo> mediasInfo = convertToMediaInfo(Media.getCartMedias(eans));
        ArrayList<OrderItemInfo> orderInfo = new ArrayList<>();
        for (int i = 0; i < cartInfo.size(); i++) {
            orderInfo.add(new OrderItemInfo(mediasInfo.get(i), cartInfo.get(i).getNrOfCopies()));
        }
        return orderInfo;
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
