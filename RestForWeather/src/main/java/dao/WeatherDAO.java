package dao;
import models.Weather;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeatherDAO {
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
    public static void save(Weather weather) throws SQLException {
        boolean o = getWeatherByID(weather);
        try {
            Statement statement = connection.createStatement();
            String SQL = "";
            if(o)
                SQL = "update Weather set temperature =" + weather.getTemperature() + ", feels_like=" + weather.getFeels_like()+ ", main='" + weather.getMain() + "', description='" + weather.getDescription()  + "' where city_id=" + weather.getCity_id();
            else
                 SQL = "INSERT INTO Weather (city_id, temperature, feels_like, main, description) " + "VALUES (" + weather.city_id + "," + weather.getTemperature() + "," + weather.getFeels_like() + ",'" + weather.getMain() + "','" + weather.getDescription() + "')";
            ResultSet resultSet = statement.executeQuery(SQL);
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public static Boolean getWeatherByID(Weather weather) throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = "SELECT * from Weather where city_id = '" + weather.city_id + "'";
        ResultSet resultSet = statement.executeQuery(SQL);
        if(resultSet.next()) {
            return resultSet.getInt("city_id") == weather.city_id;
        }
        return false;
    }
    public static List<Weather> getWeather() throws SQLException {
        List<Weather> weathers = new ArrayList<>();
        Weather weather;
        Statement statement = connection.createStatement();
        String SQL = "SELECT * from Weather";
        ResultSet resultSet = statement.executeQuery(SQL);
        int i = 0;
        while (resultSet.next()) {
            weather = new Weather(resultSet.getInt("city_id"),
                    resultSet.getInt("temperature"), resultSet.getInt("feels_like"),
                    resultSet.getString("main"), resultSet.getString("description"));
            weathers.add(weather);
        }
        return weathers;
    }

    public static Weather getWeatherWithMaxTemperature() throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = "SELECT * from Weather where temperature=(SELECT max(temperature) from Weather);";
        ResultSet resultSet = statement.executeQuery(SQL);
        resultSet.next();
        return new Weather(resultSet.getInt("city_id"),
                resultSet.getInt("temperature"), resultSet.getInt("feels_like"),
                resultSet.getString("main"), resultSet.getString("description"));
    }
    public static Weather getWeatherWithMinTemperature() throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = "SELECT * from Weather where temperature=(SELECT min(temperature) from Weather);";
        ResultSet resultSet = statement.executeQuery(SQL);
        resultSet.next();
        return new Weather(resultSet.getInt("city_id"),
                resultSet.getInt("temperature"), resultSet.getInt("feels_like"),
                resultSet.getString("main"), resultSet.getString("description"));
    }
}
