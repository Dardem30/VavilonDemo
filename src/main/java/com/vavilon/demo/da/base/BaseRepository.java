package com.vavilon.demo.da.base;

import com.vavilon.demo.bo.enums.SortDirection;
import com.vavilon.demo.bo.search.util.ListFilter;
import com.vavilon.demo.bo.search.util.OrderBy;
import com.vavilon.demo.bo.search.util.SearchResult;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class BaseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public <T> SearchResult<T> resolvePredicates(final Class<T> clazz,
                                                 final ListFilter searchCriteria,
                                                 final CriteriaFunction<T> criteriaFunction) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<T> query = builder.createQuery(clazz);
        final Root<T> root = query.from(clazz);
        final List<Predicate> predicates = criteriaFunction.apply(root, builder);
        if (searchCriteria.getSort() != null) {
            final OrderBy ob = searchCriteria.getSort();
            if (SortDirection.DESC.equals(ob.getDirection())) {
                query.orderBy(builder.desc(root.get(ob.getProperty())));
            } else {
                query.orderBy(builder.asc(root.get(ob.getProperty())));
            }
        }
        final Predicate[] varargOfPredicates = predicates.toArray(new javax.persistence.criteria.Predicate[predicates.size()]);
        final List<T> searchResult = entityManager.createQuery(query.select(root).where(varargOfPredicates))
                .setFirstResult(searchCriteria.getStart())
                .setMaxResults(searchCriteria.getLimit())
                .getResultList();

        final CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        final Root<T> countRoot = countQuery.from(clazz);
        final Long totalSize = entityManager.createQuery(countQuery.select(builder.count(countRoot)).where(builder.and(varargOfPredicates))).getSingleResult();

        final SearchResult<T> result = new SearchResult<>();
        result.setResult(searchResult);
        result.setTotalNumberFound(totalSize.intValue());
        return result;
    }
    public <T> List<T> findAll(final Class<T> clazz) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<T> query = builder.createQuery(clazz);
        final Root<T> root = query.from(clazz);
        return entityManager.createQuery(query.select(root)).getResultList();
    }
}
