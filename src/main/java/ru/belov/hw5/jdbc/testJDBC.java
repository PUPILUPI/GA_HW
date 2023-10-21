package ru.belov.hw5.jdbc;

import ru.belov.hw5.jdbc.dao.UserDAOImpl;
import ru.belov.hw5.jdbc.entity.PersonRequest;

import java.sql.SQLException;

public class testJDBC {
    public static void main(String[] args) throws SQLException {
        UserDAOImpl userDAO = new UserDAOImpl();
//        userDAO.create(new User("Белов", "Егор", "Владимирович", LocalDate.of(2002,8,21), 20000.456));
//        userDAO.update(1L, new User("Белов", "Егорыч", "Владимирович", LocalDate.of(2002,8,21), 20000.456));
        userDAO.delete(2L);
        userDAO.findById(1l);
        PersonRequest request = new PersonRequest(1, 0, null, "Егорыч", "Владимирович", null, null);
        userDAO.getAll(request).forEach(user -> System.out.println(user));
    }
}
