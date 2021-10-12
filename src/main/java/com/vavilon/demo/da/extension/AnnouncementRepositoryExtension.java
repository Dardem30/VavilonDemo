package com.vavilon.demo.da.extension;

import com.vavilon.demo.bo.announcment.AnnouncementOverviewItem;
import com.vavilon.demo.bo.announcment.CommentOverviewItem;
import com.vavilon.demo.bo.bean.ModerationForm;
import com.vavilon.demo.bo.search.AnnouncementListFilter;
import com.vavilon.demo.bo.search.CommentsListFilter;
import com.vavilon.demo.bo.search.util.SearchResult;

import java.util.List;

public interface AnnouncementRepositoryExtension {
    SearchResult<AnnouncementOverviewItem> listAnnouncements(AnnouncementListFilter listFilter);
    void updateModerationStatus(ModerationForm moderationForm);
    List<String> gallery(Long announcementId);
    Double rateAnnouncement(Double rate, Long announcementId, Long userId);
    SearchResult<CommentOverviewItem> listComments(CommentsListFilter listFilter);
}
