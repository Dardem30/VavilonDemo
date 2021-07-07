package com.vavilon.demo.da.impl;

import com.vavilon.demo.bo.announcment.AnnouncementOverviewItem;
import com.vavilon.demo.bo.announcment.AnnouncementOverviewItem_;
import com.vavilon.demo.bo.bean.ModerationForm;
import com.vavilon.demo.bo.search.AnnouncementListFilter;
import com.vavilon.demo.bo.search.util.SearchResult;
import com.vavilon.demo.da.base.BaseRepository;
import com.vavilon.demo.da.extension.AnnouncementRepositoryExtension;
import org.apache.commons.lang.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementRepositoryImpl extends BaseRepository implements AnnouncementRepositoryExtension {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SearchResult<AnnouncementOverviewItem> listAnnouncements(AnnouncementListFilter listFilter) {
        return resolvePredicates(AnnouncementOverviewItem.class, listFilter, (root, builder) -> {
            final List<Predicate> predicates = new ArrayList<>(12);
            if (StringUtils.isNotEmpty(listFilter.getContent())) {
                for (String keyword : listFilter.getContent().split(" ")) {
                    keyword = "%" + keyword.toLowerCase() + "%";
                    predicates.add(builder.or(
                            builder.like(builder.lower(root.get(AnnouncementOverviewItem_.productName)), keyword),
                            builder.like(builder.lower(root.get(AnnouncementOverviewItem_.text)), keyword)
                    ));
                }
            }
            if (listFilter.getModerationStatusId() != null) {
                predicates.add(builder.equal(root.get(AnnouncementOverviewItem_.moderationStatusId), listFilter.getModerationStatusId()));
            }
            return predicates;
        });
    }

    @Override
    public void updateModerationStatus(final ModerationForm moderationForm) {
        entityManager.createQuery("update Announcement set moderationStatus=:moderationStatus WHERE announcementId=:announcementId")
                .setParameter("moderationStatus", moderationForm.getModerationStatusId())
                .setParameter("announcementId", moderationForm.getAnnouncementId())
                .executeUpdate();
    }
}
