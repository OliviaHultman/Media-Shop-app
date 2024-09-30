package bo;

import java.time.LocalDate;

public class Product {
    private String ean;
    private String name;
    private String artist;
    private Media media;
    private String label;
    private Genre genre;
    private LocalDate released;
    private String description;
    private int price;
    private int nrInStock;

    public Product(String ean, String name, String artist, Media media, String label, Genre genre, LocalDate released, String description, int price, int nrInStock) {
        this.ean = ean;
        this.name = name;
        this.artist = artist;
        this.media = media;
        this.label = label;
        this.genre = genre;
        this.released = released;
        this.description = description;
        this.price = price;
        this.nrInStock = nrInStock;
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

    public Media getMedia() {
        return media;
    }

    public String getLabel() {
        return label;
    }

    public Genre getGenre() {
        return genre;
    }

    public LocalDate getReleased() {
        return released;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getNrInStock() {
        return nrInStock;
    }
}
