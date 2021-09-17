package com.vavilon.demo.da;

import java.util.List;

public interface UtilityDao {
    Long getLastAttachmentId();
    void persistObject(Object object);
    void mergeObject(Object object);
    <T> List<T> readAll(Class<T> clazz);
    <T> T readObject(Class<T> clazz, Long id);
    <T> void bulkDelete(Class<T> clazz, List<Long> ids, String idField);
}
