package ru.belov.hw5.hibernate;

import entity.PersonRequest;
import entity.User;

import java.sql.SQLException;
import java.util.List;

public class CriteriaUserDAO implements dao.UserDAO {
    @Override
    public void create(User person) throws SQLException {

    }

    @Override
    public void update(Long id, User person) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    @Override
    public User findById(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<User> getAll(PersonRequest personRequest) throws SQLException {
        return null;
    }
}
