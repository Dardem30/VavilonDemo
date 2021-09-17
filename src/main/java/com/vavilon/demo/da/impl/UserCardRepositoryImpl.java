package com.vavilon.demo.da.impl;

import com.vavilon.demo.bo.userCard.UserCardLight;
import com.vavilon.demo.da.extension.UserCardRepositoryExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserCardRepositoryImpl implements UserCardRepositoryExtension {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<UserCardLight> list(final Long userId) {
        return entityManager.createQuery("FROM UserCardLight WHERE userId=:userId", UserCardLight.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
