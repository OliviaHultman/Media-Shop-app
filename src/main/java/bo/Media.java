package bo;

import db.DbMedia;

import java.sql.Date;
import java.util.ArrayList;

public class Media {
    private String ean;
    private String name;
    private String artist;
    private Category category;
    private String label;
    private Genre genre;
    private Date released;
    private String description;
    private int price;
    private int nrOfCopies;

    protected Media(String ean, String name, String artist, Category category, String label, Genre genre, Date released, String description, int price, int nrOfCopies) {
        this.ean = ean;
        this.name = name;
        this.artist = artist;
        this.category = category;
        this.label = label;
        this.genre = genre;
        this.released = released;
        this.description = description;
        this.price = price;
        this.nrOfCopies = nrOfCopies;
    }

    public static ArrayList<DbMedia> getMedias() {
        return DbMedia.selectMedia();
    }

    public static ArrayList<DbMedia> getUserMedias(String user) {
        return DbMedia.selectMediaByUser(user);
    }

    public static ArrayList<DbMedia> getCartMedias(ArrayList<String> eans) {
        return DbMedia.selectMediaByEan(eans);
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

    public Category getCategory() {
        return category;
    }

    public String getLabel() {
        return label;
    }

    public Genre getGenre() {
        return genre;
    }

    public Date getReleased() {
        return released;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getNrOfCopies() {
        return nrOfCopies;
    }
}
