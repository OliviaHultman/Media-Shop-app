package db;

import bo.Category;
import bo.Genre;
import bo.Media;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class DbMedia extends Media {
    private Connection connection;
    private PreparedStatement selectMedias, selectMediasByUser;

    private DbMedia(String ean, String name, String artist, Category category, String label, Genre genre, LocalDate released, String description, int price, int nrInStock) {
        super(ean, name, artist, category, label, genre, released, description, price, nrInStock);
        connection = DbManager.getConnection();
    }
}
