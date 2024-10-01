package bo;

import ui.MediaInfo;
import ui.UserInfo;

public class UserHandler {
    public static UserInfo getUser(String email) {
        User user = User.getUser(email);
        return new UserInfo(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword(),
                user.getAuthority());
    }

    public static void addToCart (MediaInfo chosenMediaInfo, UserInfo userInfo) {
        User user = new User(userInfo.getEmail(), userInfo.getFirstName(), userInfo.getLastName(),
                userInfo.getPassword(), userInfo.getAuthority());
        for (MediaInfo mediaInfo : userInfo.getCart()) {
            user.addToCart(new Media(mediaInfo.getEan(), mediaInfo.getName(), mediaInfo.getArtist(),
                    mediaInfo.getCategory(), mediaInfo.getLabel(), mediaInfo.getGenre(), mediaInfo.getReleased(),
                    mediaInfo.getDescription(), mediaInfo.getPrice(), mediaInfo.getNrOfCopies()));
        }
        user.addNewToCart(new Media(chosenMediaInfo.getEan(), chosenMediaInfo.getName(), chosenMediaInfo.getArtist(),
                chosenMediaInfo.getCategory(), chosenMediaInfo.getLabel(), chosenMediaInfo.getGenre(),
                chosenMediaInfo.getReleased(), chosenMediaInfo.getDescription(), chosenMediaInfo.getPrice(),
                chosenMediaInfo.getNrOfCopies()));
    }

    public static void removeFromCart (MediaInfo chosenMediaInfo, UserInfo userInfo) {
        User user = new User(userInfo.getEmail(), userInfo.getFirstName(), userInfo.getLastName(),
                userInfo.getPassword(), userInfo.getAuthority());
        for (MediaInfo mediaInfo : userInfo.getCart()) {
            user.addToCart(new Media(mediaInfo.getEan(), mediaInfo.getName(), mediaInfo.getArtist(),
                    mediaInfo.getCategory(), mediaInfo.getLabel(), mediaInfo.getGenre(), mediaInfo.getReleased(),
                    mediaInfo.getDescription(), mediaInfo.getPrice(), mediaInfo.getNrOfCopies()));
        }
        user.removeFromCart(new Media(chosenMediaInfo.getEan(), chosenMediaInfo.getName(), chosenMediaInfo.getArtist(),
                chosenMediaInfo.getCategory(), chosenMediaInfo.getLabel(), chosenMediaInfo.getGenre(),
                chosenMediaInfo.getReleased(), chosenMediaInfo.getDescription(), chosenMediaInfo.getPrice(),
                chosenMediaInfo.getNrOfCopies()));
    }
}
