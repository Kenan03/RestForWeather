package dao;


import models.Person;

import java.sql.*;

public class PersonDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/RestForWeather";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "kenansql";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
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

    public static Boolean findByName(String name) throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = "SELECT * from Person where name = '" + name + "'";
        ResultSet resultSet = statement.executeQuery(SQL);
        return resultSet.next();
    }

    public static Boolean findByNameAndPassword(String name, String password) throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = "SELECT * from Person where name = '" + name + "' and password = '" + password + "'";
        ResultSet resultSet = statement.executeQuery(SQL);
        return resultSet.next();
    }

    public static void save(Person person) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String SQL = "";
            SQL = "INSERT INTO Person (name, email, password) " + "VALUES ('" + person.name + "','" + person.email + "','" + person.password + "')";
            statement.executeQuery(SQL);
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public static int getUserId(Person person) throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = "SELECT * from Person where name = '" + person.name + "'";
        ResultSet resultSet = statement.executeQuery(SQL);
        if(resultSet.next()) {
            return resultSet.getInt("id");
        }
        return 0;
    }

    public static Person getUserByID(int ID) throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = "SELECT * from Person where id = '" + ID + "'";
        ResultSet resultSet = statement.executeQuery(SQL);
        if(resultSet.next()) {
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            Person person = new Person(name, email, password);
            return person;
        }
        return null;
    }

    public static int getUserByName(String NAME) throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = "SELECT * from Person where name = '" + NAME + "'";
        ResultSet resultSet = statement.executeQuery(SQL);
        resultSet.next();
        return resultSet.getInt("id");
    }
}
