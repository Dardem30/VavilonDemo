package com.vavilon.demo.da;

import java.util.List;

public interface UtilityDao {
    Long getLastAttachmentId();
    void persistObject(Object object);
    void mergeObject(Object object);
    <T> List<T> readAll(Class<T> clazz);
}
