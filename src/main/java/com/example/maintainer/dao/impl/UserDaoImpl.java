package com.example.maintainer.dao.impl;

import com.example.maintainer.dao.UserDao;
import com.example.maintainer.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = em.createQuery(SELECT_USER.toString(), User.class);
        return query.getResultList();
    }

    @Override
    public User findById(Long id) {
        TypedQuery<User> query = em.createQuery(SELECT_USER_BY_ID.toString(), User.class)
                .setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public boolean existsById(Long id) {
        TypedQuery<Long> query = em.createQuery(COUNT_USER_BY_ID.toString(), Long.class)
                .setParameter("id", id);
        return query.getSingleResult() > 0;
    }

    @Override
    public Long saveOrUpdate(User user) {
        com.example.maintainer.entity.User userEntity;
        if (user.getId() != null) {
            userEntity = em.find(com.example.maintainer.entity.User.class, user.getId());
            userEntity.setName(user.getName());
            em.merge(userEntity);
            em.flush();
        } else {
            userEntity = new com.example.maintainer.entity.User();
            userEntity.setName(user.getName());
            em.persist(userEntity);
            em.flush();
        }
        return userEntity.getId();
    }

    @Override
    public void deleteById(Long id) {
        com.example.maintainer.entity.User userEntity = em.find(com.example.maintainer.entity.User.class, id);
        em.remove(userEntity);
        em.flush();
    }
}
