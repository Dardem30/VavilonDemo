package com.vavilon.demo.service;

import com.vavilon.demo.bo.announcment.*;
import com.vavilon.demo.bo.bean.AnnouncementRateForm;
import com.vavilon.demo.bo.bean.ModerationForm;
import com.vavilon.demo.bo.bean.ResponseForm;
import com.vavilon.demo.bo.enums.Role;
import com.vavilon.demo.bo.product.Product;
import com.vavilon.demo.bo.search.AnnouncementListFilter;
import com.vavilon.demo.bo.search.CommentsListFilter;
import com.vavilon.demo.bo.search.util.SearchResult;
import com.vavilon.demo.bo.user.AppUser;
import com.vavilon.demo.bo.user.Contact;
import com.vavilon.demo.bo.user.UserLight;
import com.vavilon.demo.da.AnnouncementRepository;
import com.vavilon.demo.da.ProductRepository;
import com.vavilon.demo.service.security.User;
import com.vavilon.demo.util.IConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ResponseForm<Announcement> saveAnnouncement(final Announcement announcement) {
        final Long userId = User.get().getAppUser().getUserId();
        for (final Contact contact : announcement.getContacts()) {
            contact.setUserId(userId);
        }
        final Product product = announcement.getProduct();
        if (product.getProductId() != null) {
            announcement.setProduct(productRepository.findById(product.getProductId()).get());
        } else {
            product.setUserId(userId);
        }
        for (final Polygon polygon : announcement.getPolygons()) {
            polygon.setAnnouncement(announcement);
        }
        announcement.setUser(new UserLight(userId));
        if (announcement.isReadyForReview()) {
            announcement.setModerationText(null);
            final ModerationStatus toBeReviewedStatus = new ModerationStatus();
            toBeReviewedStatus.setModerationStatusId(IConstants.MODERATION_STATUS_TO_BE_REVIEWED_ID);
            announcement.setModerationStatus(toBeReviewedStatus);
        } else if (announcement.getAnnouncementId() == null) {
            final ModerationStatus toBeReviewedStatus = new ModerationStatus();
            toBeReviewedStatus.setModerationStatusId(IConstants.MODERATION_STATUS_TO_BE_REVIEWED_ID);
            announcement.setModerationStatus(toBeReviewedStatus);
        }
        announcementRepository.save(announcement);
        return new ResponseForm<>("Announcement is successfully saved", true, announcement);
    }

    @Transactional(readOnly = false)
    public ResponseForm<SearchResult<AnnouncementOverviewItem>> listAnnouncements(final AnnouncementListFilter listFilter) {
        final AppUser user = User.getCurrentLoggedInUser();
        if (user == null || !Role.ADMIN.equals(user.getRole())) {
            listFilter.setModerationStatusIds(Collections.singletonList(IConstants.MODERATION_STATUS_APPROVED_ID));
        }
        final SearchResult<AnnouncementOverviewItem> result = announcementRepository.listAnnouncements(listFilter);
        return new ResponseForm<>("Success", true, result);
    }

    @Transactional(readOnly = false)
    public ResponseForm<SearchResult<AnnouncementOverviewItem>> listAnnouncementsForUser(final AnnouncementListFilter listFilter) {
      //  listFilter.setUserId(User.get().getAppUser().getUserId());
        final SearchResult<AnnouncementOverviewItem> result = announcementRepository.listAnnouncements(listFilter);
        return new ResponseForm<>("Success", true, result);
    }

    @Transactional(readOnly = true)
    public Announcement read(final Long announcementId) {
        return announcementRepository.findById(announcementId).get();
    }

    @Transactional
    public void updateModerationStatus(final ModerationForm moderationForm) {
        if (Role.ADMIN.equals(User.get().getAppUser().getRole())) {
            announcementRepository.updateModerationStatus(moderationForm);
        }
    }

    @Transactional(readOnly = true)
    public List<String> gallery(final Long announcementId) {
        return announcementRepository.gallery(announcementId);
    }

    @Transactional
    public Double rateAnnouncement(final AnnouncementRateForm announcementRateForm) {
        return announcementRepository.rateAnnouncement(announcementRateForm.getRate(), announcementRateForm.getAnnouncementId(), User.get().getAppUser().getUserId());
    }

    @Transactional(readOnly = true)
    public ResponseForm<SearchResult<CommentOverviewItem>> listComments(final CommentsListFilter listFilter) {
        return new ResponseForm<>("Success", true, announcementRepository.listComments(listFilter));
    }
}
