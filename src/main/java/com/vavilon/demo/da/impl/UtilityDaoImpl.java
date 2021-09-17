package com.vavilon.demo.da.impl;

import com.vavilon.demo.da.UtilityDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UtilityDaoImpl implements UtilityDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long getLastAttachmentId() {
        try {
            final Integer lastAttachmentId = (Integer) entityManager.createNativeQuery("SELECT attachmentid FROM attachment ORDER BY attachmentid DESC LIMIT 1").getSingleResult();
            return lastAttachmentId.longValue();
        } catch (final NoResultException e) {
            return null;
        }
    }

    @Override
    public void persistObject(final Object object) {
        entityManager.persist(object);
    }

    @Override
    public void mergeObject(final Object object) {
        entityManager.merge(object);
    }

    @Override
    public <T> List<T> readAll(final Class<T> clazz) {
        return entityManager.createQuery("FROM " + clazz.getSimpleName(), clazz).getResultList();
    }

    @Override
    public <T> T readObject(final Class<T> clazz, final Long id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public <T> void bulkDelete(final Class<T> clazz, final List<Long> ids, final String idField) {
        entityManager.createQuery("DELETE FROM " + clazz.getSimpleName() + " WHERE " + idField + " IN(:ids)").setParameter("ids", ids).executeUpdate();
    }
}
