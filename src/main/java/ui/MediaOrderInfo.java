package ui;

import bo.Media;
import bo.Status;
import bo.User;

import java.util.ArrayList;

public class MediaOrderInfo {
    private String orderNr;
    private ArrayList<MediaInfo> medias;
    private UserInfo user;
    private Status status;

    protected MediaOrderInfo(String orderNr, ArrayList<MediaInfo> medias, UserInfo user, Status status) {
        this.orderNr = orderNr;
        this.medias = medias;
        this.user = user;
        this.status = status;
    }

    public String getOrderNr() {
        return orderNr;
    }

    public ArrayList<MediaInfo> getMedias() {
        return medias;
    }

    public UserInfo getUser() {
        return user;
    }

    public Status getStatus() {
        return status;
    }
}
