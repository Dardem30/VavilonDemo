package com.vavilon.demo.da.impl;

import com.vavilon.demo.bo.announcment.*;
import com.vavilon.demo.bo.bean.ModerationForm;
import com.vavilon.demo.bo.search.AnnouncementListFilter;
import com.vavilon.demo.bo.search.CommentsListFilter;
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
    public SearchResult<CommentOverviewItem> listComments(final CommentsListFilter listFilter) {
        return resolvePredicates(CommentOverviewItem.class, listFilter, (root, builder, parameters) -> {
            final List<Predicate> predicates = new ArrayList<>(2);
            if (listFilter.getAnnouncementId() != null) {
                predicates.add(builder.equal(root.get(CommentOverviewItem_.announcementId), listFilter.getAnnouncementId()));
            }
            predicates.add(builder.isNull(root.get(CommentOverviewItem_.rootCommentId)));
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

    @Override
    public Double rateAnnouncement(final Double rate, final Long announcementId, final Long userId) {
        final List<AnnouncementRateHistory> announcementRateHistoryList = entityManager.createQuery("SELECT r FROM AnnouncementRateHistory r WHERE r.announcementId=:announcementId", AnnouncementRateHistory.class)
                .setParameter("announcementId", announcementId)
                .getResultList();
        boolean createNewRecord = true;
        int count = 0;
        double sum = 0;
        for (final AnnouncementRateHistory announcementRateHistory : announcementRateHistoryList) {
            if (announcementRateHistory.getUserId().equals(userId)) {
                createNewRecord = false;
            }
            count++;
            sum += announcementRateHistory.getRate();
        }
        if (createNewRecord) {
            final AnnouncementRateHistory announcementRateHistory = new AnnouncementRateHistory();
            announcementRateHistory.setAnnouncementId(announcementId);
            announcementRateHistory.setUserId(userId);
            announcementRateHistory.setRate(rate);
            count++;
            sum += rate;
            entityManager.merge(announcementRateHistory);
        }
        final Double result = sum / count;
        entityManager.createQuery("UPDATE Announcement SET rating=:rate WHERE announcementId=:announcementId")
                .setParameter("announcementId", announcementId)
                .setParameter("rate", result)
                .executeUpdate();
        return result;
    }
}
