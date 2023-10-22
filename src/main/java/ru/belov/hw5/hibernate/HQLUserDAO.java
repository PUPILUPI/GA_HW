package ru.belov.hw5.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.belov.hw5.jdbc.dao.UserDAO;
import ru.belov.hw5.jdbc.entity.PersonRequest;
import ru.belov.hw5.jdbc.entity.User;

import java.sql.Date;
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
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "INSERT INTO User (secondName, firstName, lastName, birthDate, salary)" +
                "VALUES (:secondName, :firstName, :lastName, :birthDate, :salary)";
        session.createQuery(hql).setParameter("secondName", user.getSecondName())
                .setParameter("firstName", user.getFirstName())
                .setParameter("lastName", user.getLastName())
                .setParameter("birthDate", Date.valueOf(user.getBirthDate()))
                .setParameter("salary", user.getSalary())
                .executeUpdate();
        transaction.commit();
    }

    @Override
    public void update(Long id, User newUser) throws SQLException {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        session.merge(newUser);
//        transaction.commit();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "UPDATE User SET secondName = :secondName, firstName = :firstName, lastName = :lastName, birthDate = :birthDate, salary = :salary" +
               " WHERE id = :id";
        session.createQuery(hql).setParameter("secondName", newUser.getSecondName())
                .setParameter("firstName", newUser.getFirstName())
                .setParameter("lastName", newUser.getLastName())
                .setParameter("birthDate", newUser.getBirthDate())
                .setParameter("salary", newUser.getSalary())
                .setParameter("id", id)
                .executeUpdate();
        transaction.commit();
    }

    @Override
    public void delete(Long id) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql ="DELETE FROM User WHERE id = :id";
        session.createQuery(hql).setParameter("id", id).executeUpdate();
        transaction.commit();
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
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("from User", User.class);
        query.setFirstResult((personRequest.page()-1) * personRequest.limit()).setMaxResults(personRequest.limit());
        return query.list();
    }
}
