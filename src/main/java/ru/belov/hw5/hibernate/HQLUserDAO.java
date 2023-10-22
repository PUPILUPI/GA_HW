package ru.belov.hw5.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.belov.hw5.jdbc.dao.UserDAO;
import ru.belov.hw5.jdbc.entity.PersonRequest;
import ru.belov.hw5.jdbc.entity.User;

import java.sql.SQLException;
import java.util.List;

public class HQLUserDAO implements UserDAO {
    SessionFactory sessionFactory;

    public HQLUserDAO() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    @Override
    public void create(User user) throws SQLException {
//  код без hql
//  транзакцию надо открывать при создании, обновлении или удалении записи
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        session.persist(user);
//        transaction.commit();
    }

    @Override
    public void update(Long id, User newUser) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(newUser);
        transaction.commit();
    }

    @Override
    public void delete(Long id) throws SQLException {
//        код без hql
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        User user = session.get(User.class, id);
//        session.remove(user);
//        transaction.commit();
    }

    @Override
    public User findById(Long id) throws SQLException {
//        это вариант без hql
//        Session session = sessionFactory.openSession();
//        User user = session.get(User.class, id);
//        return user;
        return null;
    }

    @Override
    public List<User> getAll(PersonRequest personRequest) throws SQLException {
//      тут без hql никак;
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        session.get
        return null;
    }
}
