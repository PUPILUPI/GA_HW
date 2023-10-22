package ru.belov.hw5.hibernate;

import ru.belov.hw5.jdbc.entity.PersonRequest;
import ru.belov.hw5.jdbc.entity.User;

import java.sql.SQLException;
import java.time.LocalDate;

public class testHibernate {
    public static void main(String[] args) throws SQLException {
        HQLUserDAO userDAO = new HQLUserDAO();
//        userDAO.create(new User("Рахбари2", "Владимир", "Владимирович", LocalDate.of(2002,8,21), 20000.456));
        userDAO.update(5l , new User("Рахбари", "Егор", "Владимирович", LocalDate.of(2002,8,21), 20000.456));
        userDAO.delete(6l);
        userDAO.getAll(new PersonRequest(2, 2, null, null, null, null, null));
//        userDAO.delete(5l);
//        userDAO.update(5l, new User(5, "Заадорожко", "Владимир", "Владимирович", LocalDate.of(2002,8,21), 20000.456));
    }
}
