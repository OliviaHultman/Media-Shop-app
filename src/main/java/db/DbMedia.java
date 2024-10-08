package db;

import bo.Type;
import bo.Media;

import java.sql.*;
import java.util.ArrayList;

public class DbMedia extends Media {
    final static String SELECT_MEDIAS = "SELECT Media.* FROM Media";
    final static String SELECT_MEDIAS_BY_EAN = "SELECT Media.* FROM Media WHERE Media.ean = ?";
    final static String SELECT_NR_OF_COPIES = "SELECT Media.nrOfCopies FROM Media WHERE Media.ean = ?";
    final static String UPDATE_NR_OF_COPIES = "UPDATE Media SET Media.nrOfCopies = Media.nrOfCopies - ? WHERE Media.ean = ?";
    final static String INSERT_MEDIA = "INSERT INTO Media VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    final static String UPDATE_MEDIA = "UPDATE Media SET Media.name = ?, Media.artist = ?, Media.type = ?, Media.label = ?, Media.category = ?, Media.price = ?, Media.nrOfCopies = ? WHERE Media.ean = ?";
    final static String DELETE_MEDIA = "DELETE FROM Media WHERE Media.ean = ?";
    final static String SELECT_CATEGORIES = "SELECT Category.* FROM Category";
    final static String ADD_CATEGORY = "INSERT INTO Category VALUES (?)";
    final static String DELETE_CATEGORY = "DELETE FROM Category WHERE Category.genre = ?";


    private DbMedia(String ean, String name, String artist, Type type, String label, String genre, int price, int nrOfCopies) {
        super(ean, name, artist, type, label, genre, price, nrOfCopies);
    }

    public static ArrayList<DbMedia> selectMedias() {
        ResultSet result = null;
        ArrayList<DbMedia> medias = new ArrayList<>();
        try {
            PreparedStatement selectMedia = DbManager.getConnection().prepareStatement(SELECT_MEDIAS);
            result = selectMedia.executeQuery();
            while (result.next()) {
                medias.add(new DbMedia(result.getString("ean"), result.getString("name"),
                        result.getString("artist"), Type.valueOf(result.getString("type")),
                        result.getString("label"), result.getString("category"),
                        result.getInt("price"), result.getInt("nrOfCopies")));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
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
        return medias;
    }

    public static ArrayList<DbMedia> selectMediasByEan(ArrayList<String> eans) {
        ResultSet result = null;
        ArrayList<DbMedia> medias = new ArrayList<>();
        try {
            PreparedStatement selectMedia = DbManager.getConnection().prepareStatement(SELECT_MEDIAS_BY_EAN);
            for (String ean : eans) {
                selectMedia.setString(1, ean);
                result = selectMedia.executeQuery();
                if (result.next()) {
                    medias.add(new DbMedia(result.getString("ean"), result.getString("name"),
                            result.getString("artist"), Type.valueOf(result.getString("type")),
                            result.getString("label"), result.getString("category"),
                            result.getInt("price"), result.getInt("nrOfCopies")));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
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
        return medias;
    }

    protected static boolean updateNrOfCopies(String media, int nrOfCopies) {
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
            exception.printStackTrace();
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

    public static boolean insertMedia(Media media) {
        try {
            PreparedStatement insertMedia = DbManager.getConnection().prepareStatement(INSERT_MEDIA);
            insertMedia.setString(1, media.getEan());
            insertMedia.setString(2, media.getName());
            insertMedia.setString(3, media.getArtist());
            insertMedia.setString(4, String.valueOf(media.getType()));
            insertMedia.setString(5, media.getLabel());
            insertMedia.setString(6, media.getGenre());
            insertMedia.setInt(7, media.getPrice());
            insertMedia.setInt(8, media.getNrOfCopies());
            insertMedia.executeUpdate();
            return true;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static boolean updateMedia(Media media) {
            try {
                PreparedStatement updateMedia = DbManager.getConnection().prepareStatement(UPDATE_MEDIA);
                updateMedia.setString(1, media.getName());
                updateMedia.setString(2, media.getArtist());
                updateMedia.setString(3, String.valueOf(media.getType()));
                updateMedia.setString(4, media.getLabel());
                updateMedia.setString(5, media.getGenre());
                updateMedia.setInt(6, media.getPrice());
                updateMedia.setInt(7, media.getNrOfCopies());
                updateMedia.setString(8, media.getEan());
                updateMedia.executeUpdate();
                return true;
            }
            catch (SQLException exception) {
                exception.printStackTrace();
                return false;
            }
    }

    public static boolean deleteMedia(String ean) {
        try {
            PreparedStatement deleteMedia = DbManager.getConnection().prepareStatement(DELETE_MEDIA);
            deleteMedia.setString(1, ean);
            deleteMedia.executeUpdate();
            return true;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static ArrayList<String> selectCategories() {
        ResultSet result = null;
        ArrayList<String> categories = new ArrayList<>();
        try {
            PreparedStatement selectCategories = DbManager.getConnection().prepareStatement(SELECT_CATEGORIES);
            result = selectCategories.executeQuery();
            while (result.next()) {
                categories.add(result.getString("genre"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
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
        return categories;
    }

    public static boolean addCategory(String category) {
        try {
            PreparedStatement addCategory = DbManager.getConnection().prepareStatement(ADD_CATEGORY);
            addCategory.setString(1, category);
            addCategory.executeUpdate();
            return true;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static boolean deleteCategory(String category) {
        try {
            PreparedStatement deleteCategory = DbManager.getConnection().prepareStatement(DELETE_CATEGORY);
            deleteCategory.setString(1, category);
            deleteCategory.executeUpdate();
            return true;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }
}