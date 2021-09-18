package com.vavilon.demo.da.impl;

import com.vavilon.demo.bo.announcment.AnnouncementOverviewItem;
import com.vavilon.demo.bo.announcment.AnnouncementOverviewItem_;
import com.vavilon.demo.bo.announcment.Coordinate;
import com.vavilon.demo.bo.bean.ModerationForm;
import com.vavilon.demo.bo.search.AnnouncementListFilter;
import com.vavilon.demo.bo.search.util.SearchResult;
import com.vavilon.demo.da.base.BaseRepository;
import com.vavilon.demo.da.base.Parameter;
import com.vavilon.demo.da.extension.AnnouncementRepositoryExtension;
import org.apache.commons.lang.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementRepositoryImpl extends BaseRepository implements AnnouncementRepositoryExtension {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SearchResult<AnnouncementOverviewItem> listAnnouncements(final AnnouncementListFilter listFilter) {
        return resolvePredicates(AnnouncementOverviewItem.class, listFilter, (root, builder, parameters) -> {
            final List<Predicate> predicates = new ArrayList<>(6);
            if (StringUtils.isNotEmpty(listFilter.getContent())) {
                final String searchText = "%" + listFilter.getContent().toLowerCase() + "%";
                predicates.add(builder.or(
                        builder.like(builder.lower(root.get(AnnouncementOverviewItem_.productName)), searchText),
                        builder.like(builder.lower(root.get(AnnouncementOverviewItem_.text)), searchText)
                ));
            }
            if (StringUtils.isNotEmpty(listFilter.getMeasureCode())) {
                predicates.add(builder.equal(root.get(AnnouncementOverviewItem_.measureCode), listFilter.getMeasureCode()));
            }
            if (listFilter.getPriceRangeStart() != null && listFilter.getPriceRangeEnd() != null) {
                predicates.add(builder.between(root.get(AnnouncementOverviewItem_.price), listFilter.getPriceRangeStart(), listFilter.getPriceRangeEnd()));
            } else if (listFilter.getPriceRangeStart() != null) {
                predicates.add(builder.ge(root.get(AnnouncementOverviewItem_.price), listFilter.getPriceRangeStart()));
            } else if (listFilter.getPriceRangeEnd() != null) {
                predicates.add(builder.le(root.get(AnnouncementOverviewItem_.price), listFilter.getPriceRangeEnd()));
            }
            if (StringUtils.isNotEmpty(listFilter.getCurrencySign())) {
                predicates.add(builder.equal(root.get(AnnouncementOverviewItem_.currencySign), listFilter.getCurrencySign()));
            }
            if (listFilter.getModerationStatusId() != null) {
                predicates.add(builder.equal(root.get(AnnouncementOverviewItem_.moderationStatusId), listFilter.getModerationStatusId()));
            }
            if (listFilter.getUserId() != null) {
                predicates.add(builder.equal(root.get(AnnouncementOverviewItem_.userId), listFilter.getUserId()));
            }
            if (listFilter.getCoordinates() != null && !listFilter.getCoordinates().isEmpty()) {
                final List<Coordinate> coordinates = listFilter.getCoordinates();
                final Predicate[] coordinatePredicates = new Predicate[coordinates.size()];
                for (int index = 0; index < coordinates.size(); index++) {
                    final Coordinate coordinate = coordinates.get(index);
                    final ParameterExpression<Double> lat = builder.parameter(Double.class);
                    final ParameterExpression<Double> lng = builder.parameter(Double.class);
                    coordinatePredicates[index] = builder.isTrue(builder.function("fx_ismarkerinsidedeliveryzoneforannouncement", Boolean.class, root.get(AnnouncementOverviewItem_.announcementId), lat, lng));
                    parameters.add(new Parameter<>(lat, coordinate.getLat()));
                    parameters.add(new Parameter<>(lng, coordinate.getLng()));
                }
                predicates.add(builder.or(coordinatePredicates));
            }
            return predicates;
        });
    }

    @Override
    public void updateModerationStatus(final ModerationForm moderationForm) {
        entityManager.createQuery("update Announcement set moderationStatus.moderationStatusId=:moderationStatus WHERE announcementId=:announcementId")
                .setParameter("moderationStatus", moderationForm.getModerationStatusId())
                .setParameter("announcementId", moderationForm.getAnnouncementId())
                .executeUpdate();
    }

    @Override
    public List<String> gallery(final Long announcementId) {
        return entityManager.createQuery("SELECT a.fileId FROM Attachment a WHERE a.productId = (SELECT product.productId FROM Announcement WHERE announcementId=:announcementId)", String.class)
                .setParameter("announcementId", announcementId)
                .getResultList();
    }
}
