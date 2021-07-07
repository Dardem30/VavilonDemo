package com.vavilon.demo.service;

import com.vavilon.demo.bo.announcment.Announcement;
import com.vavilon.demo.bo.announcment.AnnouncementOverviewItem;
import com.vavilon.demo.bo.announcment.ModerationStatus;
import com.vavilon.demo.bo.bean.ModerationForm;
import com.vavilon.demo.bo.bean.ResponseForm;
import com.vavilon.demo.bo.enums.Role;
import com.vavilon.demo.bo.search.AnnouncementListFilter;
import com.vavilon.demo.bo.search.util.SearchResult;
import com.vavilon.demo.bo.user.AppUser;
import com.vavilon.demo.da.AnnouncementRepository;
import com.vavilon.demo.service.security.User;
import com.vavilon.demo.util.IConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    @Transactional
    public ResponseForm<Announcement> saveAnnouncement(final Announcement announcement) {
        final ModerationStatus toBeReviewedStatus = new ModerationStatus();
        toBeReviewedStatus.setModerationStatusId(IConstants.MODERATION_STATUS_TO_BE_REVIEWED_ID);
        announcement.setModerationStatus(toBeReviewedStatus);
        announcement.getProduct().setUserId(User.getCurrentLoggedInUser().getUserId());
        announcementRepository.save(announcement);
        return new ResponseForm<>("Announcement is successfully saved", true, announcement);
    }

    @Transactional(readOnly = true)
    public ResponseForm<SearchResult<AnnouncementOverviewItem>> listAnnouncements(final AnnouncementListFilter listFilter) {
        final AppUser user = User.getCurrentLoggedInUser();
        if (user == null || !Role.ADMIN.equals(user.getRole())) {
            listFilter.setModerationStatusId(IConstants.MODERATION_STATUS_APPROVED_ID);
        }
        final SearchResult<AnnouncementOverviewItem> result = announcementRepository.listAnnouncements(listFilter);
        return new ResponseForm<>("Success", true, result);
    }

    @Transactional(readOnly = true)
    public Announcement read(final Long announcementId) {
        return announcementRepository.findById(announcementId).get();
    }

    @Transactional
    public void updateModerationStatus(final ModerationForm moderationForm) {
        announcementRepository.updateModerationStatus(moderationForm);
    }
}
