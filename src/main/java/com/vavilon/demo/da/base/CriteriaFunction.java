package com.vavilon.demo.da.base;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@FunctionalInterface
public interface CriteriaFunction<T> {
    List<Predicate> apply(Root<T> root, CriteriaBuilder builder);
}
