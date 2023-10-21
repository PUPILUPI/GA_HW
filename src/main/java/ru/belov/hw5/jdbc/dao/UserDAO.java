package ru.belov.hw5.jdbc.dao;

import ru.belov.hw5.jdbc.entity.PersonRequest;
import ru.belov.hw5.jdbc.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void create(User person) throws SQLException;

    void update(Long id, User person) throws SQLException;

    void delete(Long id) throws SQLException;

    User findById(Long id) throws SQLException;

    List<User> getAll(PersonRequest personRequest) throws SQLException;
}
