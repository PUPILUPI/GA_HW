package ru.belov.hw5.jdbc.dao;

import ru.belov.hw5.jdbc.entity.PersonRequest;
import ru.belov.hw5.jdbc.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDAOImpl implements UserDAO {
    Properties properties = new Properties();
    String url;
    String username;
    String password;

    public UserDAOImpl() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.yml")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.url = properties.getProperty("url");
        this.username = properties.getProperty("username");
        this.password = properties.getProperty("password");
    }

    @Override
    public void create(User user) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        String query = "INSERT INTO homework.user (second_name, first_name, last_name, birth_date, salary) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getSecondName());
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setDate(4, Date.valueOf(user.getBirthDate()));
        preparedStatement.setDouble(5, (double) Math.round(user.getSalary() * 100) / 100);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void update(Long id, User user) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        String query = "UPDATE homework.user SET second_name = ?, first_name = ?, last_name = ?, birth_date = ?, salary = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getSecondName());
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setDate(4, Date.valueOf(user.getBirthDate()));
        preparedStatement.setDouble(5, (double) Math.round(user.getSalary() * 100) / 100);
        preparedStatement.setLong(6, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        String query = "DELETE FROM homework.user where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public User findById(Long id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        String query = "SELECT * FROM homework.user WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        ResultSet res = preparedStatement.executeQuery();
        return res.next() ? new User(res.getLong("id"),
                res.getString("second_name"),
                res.getString("first_name"),
                res.getString("last_name"),
                res.getDate("birth_date").toLocalDate(),
                res.getDouble("salary")) : null;
    }

    //    @SneakyThrows
    @Override
    public List<User> getAll(PersonRequest personRequest) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        List<User> filteredPersons = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        int count = personRequest.countNulls();
        query.append("SELECT * FROM homework.user ");
        if (count != 0) {
            query.append(" WHERE ");
        }
        if (personRequest.secondName() != null) {
            query.append(" second_name = ? ");
            count-=1;
            if(count!=0) query.append( " AND " );
        }
        if (personRequest.firstName() != null) {
            query.append(" first_name = ? ");
            count-=1;
            if(count!=0) query.append( " AND " );
        }
        if (personRequest.lastName() != null) {
            query.append(" last_name = ? ");
            count-=1;
            if(count!=0) query.append( " AND " );
        }
        if (personRequest.birthDate() != null) {
            query.append(" birth_date = ? ");
            count-=1;
            if(count!=0) query.append( " AND " );
        }
        if (personRequest.salary() != null) {
            query.append(" salary = ? ");
            count-=1;
            if(count!=0) query.append( " AND " );
        }
        if (personRequest.limit() != 0) {
            query.append(" LIMIT ? ");
        }
        if (personRequest.page() != 0) {
            query.append(" OFFSET ?");
        }
        query.append(";");
        System.out.println(query);
        PreparedStatement statement = connection.prepareStatement(query.toString());
        count = personRequest.countNulls();
        int start = 1;
        if(start <= count) {
            if (personRequest.secondName() != null) {
                statement.setString(start++, personRequest.secondName());
            }
            if (personRequest.firstName() != null) {
                statement.setString(start++, personRequest.firstName());
            }
            if (personRequest.lastName() != null) {
                statement.setString(start++, personRequest.lastName());
            }
            if (personRequest.birthDate() != null) {
                statement.setDate(start++, Date.valueOf(personRequest.birthDate()));
            }
            if (personRequest.secondName() != null) {
                statement.setString(start++, personRequest.secondName());
            }
            if (personRequest.limit() != 0) {
                statement.setInt(start++, personRequest.limit());
            }
            if (personRequest.page() != 0) {
                statement.setInt(start, (personRequest.page() - 1) * personRequest.limit());
            }
        }
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User person = new User(resultSet.getLong("id"),
                            resultSet.getString("second_name"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getDate("birth_date").toLocalDate(),
                            resultSet.getDouble("salary"));
                    filteredPersons.add(person);
                }
            }
        return filteredPersons;
    }
}

