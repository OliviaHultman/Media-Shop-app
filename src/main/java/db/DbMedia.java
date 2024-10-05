package db;

import bo.Authority;
import bo.Category;
import bo.Genre;
import bo.Media;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DbMedia extends Media {
    final static String SELECT_MEDIA_BY_USER = "SELECT Media.* FROM Booked LEFT JOIN Media ON Booked.media = Media.ean WHERE Booked.user = ?",
            SELECT_MEDIA = "SELECT Media.* FROM Media",
            SELECT_MEDIA_BY_EAN = "SELECT Media.* FROM Media WHERE Media.ean = ?",
            SELECT_NR_OF_COPIES = "SELECT Media.nrOfCopies FROM Media WHERE Media.ean = ?",
            UPDATE_NR_OF_COPIES = "UPDATE Media SET Media.nrOfCopies = Media.nrOfCopies - ? WHERE Media.ean = ?";

    private DbMedia(String ean, String name, String artist, Category category, String label, Genre genre, Date released, String description, int price, int nrOfCopies) {
        super(ean, name, artist, category, label, genre, released, description, price, nrOfCopies);
    }

    private static ArrayList<DbMedia> createMedias(ResultSet result) throws SQLException {
        ArrayList<DbMedia> medias = new ArrayList<>();
        while (result.next()) {
            medias.add(new DbMedia(result.getString("ean"), result.getString("name"),
                    result.getString("artist"), Category.valueOf(result.getString("category")),
                    result.getString("label"), Genre.valueOf(result.getString("genre")),
                    result.getDate("released"), result.getString("description"),
                    result.getInt("price"), result.getInt("nrOfCopies")));
        }
        return medias;
    }

    public static ArrayList<DbMedia> selectMedia() {
        ResultSet result = null;
        try {
            PreparedStatement selectMedia = DbManager.getConnection().prepareStatement(SELECT_MEDIA);
            return createMedias(selectMedia.executeQuery());
        } catch (SQLException exception) {
            return null;
        }
        finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static ArrayList<DbMedia> selectMediaByEan(ArrayList<String> eans) {
        ResultSet result = null;
        try {
            PreparedStatement selectMedia = DbManager.getConnection().prepareStatement(SELECT_MEDIA_BY_EAN);
            ArrayList<DbMedia> medias = new ArrayList<>();
            for (String ean : eans) {
                selectMedia.setString(1, ean);
                result = selectMedia.executeQuery();
                if (result.next()) {
                    medias.add(new DbMedia(result.getString("ean"), result.getString("name"),
                            result.getString("artist"), Category.valueOf(result.getString("category")),
                            result.getString("label"), Genre.valueOf(result.getString("genre")),
                            result.getDate("released"), result.getString("description"),
                            result.getInt("price"), result.getInt("nrOfCopies")));
                }
            }
            return medias;
        } catch (SQLException exception) {
            return null;
        }
        finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static ArrayList<DbMedia> selectMediaByUser(String user) {
        ResultSet result = null;
        try {
            PreparedStatement selectMediaByUser = DbManager.getConnection().prepareStatement(SELECT_MEDIA_BY_USER);
            selectMediaByUser.setString(1, user);
            return createMedias(selectMediaByUser.executeQuery());
        } catch (SQLException exception) {
            return null;
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static boolean updateNrOfCopies(String media, int nrOfCopies) {
        ResultSet result = null;
        try {
            PreparedStatement selectNrOfCopies = DbManager.getConnection().prepareStatement(SELECT_NR_OF_COPIES);
            selectNrOfCopies.setString(1, media);
            result = selectNrOfCopies.executeQuery();
            result.next();
            if (result.getInt("nrOfCopies") < nrOfCopies) {
                return false;
            }
            PreparedStatement updateNrOfCopies = DbManager.getConnection().prepareStatement(UPDATE_NR_OF_COPIES);
            updateNrOfCopies.setInt(1, nrOfCopies);
            updateNrOfCopies.setString(2, media);
            updateNrOfCopies.executeUpdate();
            return true;
        }
        catch (SQLException exception) {
            return false;
        }
        finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}
