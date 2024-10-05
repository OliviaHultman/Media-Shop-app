package bo;

import db.DbMedia;

import java.util.ArrayList;

public class Media {
    private String ean;
    private String name;
    private String artist;
    private Type type;
    private String label;
    private String genre;
    private int price;
    private int nrOfCopies;

    protected Media(String ean, String name, String artist, Type type, String label, String genre, int price, int nrOfCopies) {
        this.ean = ean;
        this.name = name;
        this.artist = artist;
        this.type = type;
        this.label = label;
        this.genre = genre;
        this.price = price;
        this.nrOfCopies = nrOfCopies;
    }

    public static ArrayList<DbMedia> getMedias() {
        return DbMedia.selectMedias();
    }

    public static ArrayList<DbMedia> getUserMedias(String user) {
        return DbMedia.selectMediaByUser(user);
    }

    public static ArrayList<DbMedia> getCartMedias(ArrayList<String> eans) {
        return DbMedia.selectMediasByEan(eans);
    }

    public String getEan() {
        return ean;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public Type getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public String getGenre() {
        return genre;
    }

    public int getPrice() {
        return price;
    }

    public int getNrOfCopies() {
        return nrOfCopies;
    }
}
