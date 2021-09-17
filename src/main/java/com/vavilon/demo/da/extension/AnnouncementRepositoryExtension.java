package com.vavilon.demo.da.extension;

import com.vavilon.demo.bo.announcment.AnnouncementOverviewItem;
import com.vavilon.demo.bo.bean.ModerationForm;
import com.vavilon.demo.bo.search.AnnouncementListFilter;
import com.vavilon.demo.bo.search.util.SearchResult;

import java.util.List;

public interface AnnouncementRepositoryExtension {
    SearchResult<AnnouncementOverviewItem> listAnnouncements(AnnouncementListFilter listFilter);
    void updateModerationStatus(ModerationForm moderationForm);
    List<String> gallery(Long announcementId);
}
