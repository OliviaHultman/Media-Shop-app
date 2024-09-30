package bo;

import java.time.LocalDate;

public class Media {
    private String ean;
    private String name;
    private String artist;
    private Category category;
    private String label;
    private Genre genre;
    private LocalDate released;
    private String description;
    private int price;
    private int nrInStock;

    public Media(String ean, String name, String artist, Category category, String label, Genre genre, LocalDate released, String description, int price, int nrInStock) {
        this.ean = ean;
        this.name = name;
        this.artist = artist;
        this.category = category;
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

    public Category getMedia() {
        return category;
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
