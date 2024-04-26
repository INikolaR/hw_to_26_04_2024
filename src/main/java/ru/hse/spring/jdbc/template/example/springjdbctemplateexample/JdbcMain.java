package ru.hse.spring.jdbc.template.example.springjdbctemplateexample;

import java.sql.*;
import java.util.Properties;

public class JdbcMain {

    public static void render(Person person) {
        System.out.println(person);
    }

    public static void main(String[] args) {
        Properties connectionProperties = new Properties();

        connectionProperties.setProperty("user", "postgres");
        connectionProperties.setProperty("password", "123456");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", connectionProperties);
        } catch (SQLException e){
            System.out.println(e.getMessage() + " Cannot connect to database!");
            return;
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select first_name, last_name from person where id = ?");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " Cannot prepare statement!");
            return;
        }
        Person person = null;
        try {
            preparedStatement.setInt(1, 2);
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " Cannot set param to PrepareStatement!");
        }
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " Cannot execute query!");
            return;
        }
        boolean hasNext;
        try {
            hasNext = resultSet.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " Cannot get call next() method on result!");
            return;
        }
        if (hasNext) {
            try {
                person = new Person(resultSet.getString("first_name"), resultSet.getString("last_name"));
            } catch (SQLException e) {
                System.out.println(e.getMessage() + " Cannot get information from result!");
            }
            render(person);
        }
    }
}
