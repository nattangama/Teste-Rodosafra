package com.javaeeeee;

import com.codahale.metrics.annotation.Timed;
import org.hibernate.Session;
import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.SessionFactory;

import javax.ws.rs.GET;
import java.util.List;

public class UserDAO extends AbstractDAO<Student> {

    private static SessionFactory factory2;

    public UserDAO(SessionFactory factory) {
        super(factory);
        factory2 = factory;
        }

public Student findById(Long id) {
        return get(id);
        }

public long create(Student student) {
        return persist(student).getStudentID();
        }

@UnitOfWork
public List<Student> findAll() {
        Session session = factory2.openSession();
        org.hibernate.query.Query query = session.createQuery("from Student");
        return query.list();
    }
}
