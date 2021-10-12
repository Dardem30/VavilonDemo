package com.vavilon.demo.da.impl;

import com.vavilon.demo.da.extension.AppUserRepositoryExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AppUserRepositoryImpl implements AppUserRepositoryExtension {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void updateProfileInfo(final Long userId, final String info) {
        entityManager.createQuery("UPDATE AppUser u SET u.info=:info WHERE u.userId=:userId")
                .setParameter("userId", userId)
                .setParameter("info", info)
                .executeUpdate();
    }
}
