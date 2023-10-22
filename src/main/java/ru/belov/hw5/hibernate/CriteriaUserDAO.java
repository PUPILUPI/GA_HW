package ru.belov.hw5.hibernate;

import entity.PersonRequest;
import entity.User;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class CriteriaUserDAO {
    SessionFactory sessionFactory;
    public CriteriaUserDAO() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(ru.belov.hw5.jdbc.entity.User.class)
                .buildSessionFactory();
    }
    public List<User> selectAll(){
        List<User> staffs;
        try {
            Session session = sessionFactory.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            query.select(query.from(User.class));
            staffs = session.createQuery(query).getResultList();
            session.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return staffs;
    }

    public List<User> selectByColumn(String column, Object value) {
        List<User> staffs;
        try {
            Session session = sessionFactory.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> root = query.from(User.class);
            query.select(root).where(cb.equal(root.get(column), value));
            staffs = session.createQuery(query).getResultList();
            session.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return staffs;
    }

    public User selectById(Integer id) {
        List<User> staffs = selectByColumn("id", id);
        return staffs.isEmpty() ? null : staffs.get(0);
    }

    public void updateStaff(Integer id, String column, Object value){
        try {
            Session session = sessionFactory.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<User> criteriaUpdate = cb.createCriteriaUpdate(User.class);
            Root<User> root = criteriaUpdate.from(User.class);
            criteriaUpdate.set(root.get(column), value).where(cb.equal(root.get("id"), id));
            Query query = session.createQuery(criteriaUpdate);
            try {
                session.beginTransaction();
                query.executeUpdate();
                session.getTransaction().commit();
            }
            catch (Exception e){
                session.getTransaction().rollback();
            }
            finally {
                session.close();
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStaff(Integer id) {
        try {
            Session session = sessionFactory.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<User> criteriaDelete = cb.createCriteriaDelete(User.class);
            Root<User> root = criteriaDelete.from(User.class);
            criteriaDelete.where(cb.equal(root.get("id"), id));
            Query query = session.createQuery(criteriaDelete);
            try {
                session.beginTransaction();
                query.executeUpdate();
                session.getTransaction().commit();
            }
            catch (Exception e) {
                session.getTransaction().rollback();
            }
            finally {
                session.close();
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
