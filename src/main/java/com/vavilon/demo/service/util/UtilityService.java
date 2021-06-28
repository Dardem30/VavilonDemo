package com.vavilon.demo.service.util;

import com.vavilon.demo.da.UtilityDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UtilityService {
    private final UtilityDao utilityDao;

    public Long getLastAttachmentId() {
        return utilityDao.getLastAttachmentId();
    }

    public void persistObject(final Object object) {
        utilityDao.persistObject(object);
    }

    @Transactional
    public void mergeObject(final Object object) {
        utilityDao.mergeObject(object);
    }

    @Transactional(readOnly = true)
    public <T> List<T> readAll(final Class<T> clazz) {
        return utilityDao.readAll(clazz);
    }
}
