package dao;
import models.City;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CityDAO {
    private static final String URL = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private static Connection connection;

    static {
        try {
            Class.forName("");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static City city(String cityName) throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = "SELECT * from City where name = '" + cityName + "'";
        ResultSet resultSet = statement.executeQuery(SQL);
        resultSet.next();
        City city = new City(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("latitude"),
                resultSet.getString("longitude")
        );
        return city;
    }
    public static City cityByID(int city_id) throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = "SELECT * from City where id=" + city_id;
        ResultSet resultSet = statement.executeQuery(SQL);
        resultSet.next();
        City city = new City(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("latitude"),
                resultSet.getString("longitude")
        );
        return city;
    }
    public static List<String> listOfCity() throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = "SELECT name from City";
        ResultSet resultSet = statement.executeQuery(SQL);
        List<String> list = new ArrayList<>();
        while (resultSet.next())
            list.add(resultSet.getString("name"));
        return list;
    }
}
