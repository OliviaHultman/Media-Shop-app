package db;

import bo.Category;
import bo.Genre;
import bo.Media;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class DbMedia extends Media {
    final static String SELECT_MEDIA_BY_USER = "", SELECT_MEDIA = "";

    private DbMedia(String ean, String name, String artist, Category category, String label, Genre genre, LocalDate released, String description, int price, int nrOfCopies) {
        super(ean, name, artist, category, label, genre, released, description, price, nrOfCopies);
    }


}
