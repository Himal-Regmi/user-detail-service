package com.regmi.userdetail.dao;

import com.regmi.userdetail.model.UserDetailEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
@Repository
public class UserDetailDaoImpl implements UserDetailDao {
    @Autowired
    EntityManager entityManager;

    @Override
    public UserDetailEntity getUserDetails(int id) {
        Session session= entityManager.unwrap(Session.class);
        return session.get(UserDetailEntity.class,id);
    }

    @Override
    public void save(UserDetailEntity userDetail) {
        Session session= entityManager.unwrap(Session.class);
        session.save(userDetail);
    }

    @Override
    public void update(UserDetailEntity userDetail) {
        Session session= entityManager.unwrap(Session.class);
        session.update(userDetail);
    }

    @Override
    public void deleteUserDetailsById(int id) {
        Session session= entityManager.unwrap(Session.class);
        UserDetailEntity userDetail= session.get(UserDetailEntity.class,id);
        session.delete(userDetail);
    }

    @Override
    public UserDetailEntity getUserDetailsByUserId(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query<UserDetailEntity> query =
                session.createQuery("from UserDetailEntity where userId =:uid", UserDetailEntity.class);
        query.setParameter("uid", id);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
