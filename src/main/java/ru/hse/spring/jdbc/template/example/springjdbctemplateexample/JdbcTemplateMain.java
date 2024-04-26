package ru.hse.spring.jdbc.template.example.springjdbctemplateexample;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateMain {

    public static void render(Person person) {
        System.out.println(person);
    }

    public static void main(String[] args) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUser("postgres");
        dataSource.setPassword("123456");

        JdbcOperations jdbcOperations = new JdbcTemplate(dataSource);

        Person person = null;
        try {
            person = jdbcOperations.queryForObject(
                    "select first_name, last_name from person where id = ?",
                    (rs, rowNum) -> new Person(rs.getString("first_name"), rs.getString("last_name")),
                    1);
        } catch (org.springframework.dao.DataAccessException e) {
            System.out.println(e.getMessage() + " Cannot access data correctly!");
        }
        if (person != null) {
            render(person);
        }
    }
}
