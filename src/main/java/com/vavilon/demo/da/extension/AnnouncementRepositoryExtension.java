package com.vavilon.demo.da.extension;

import com.vavilon.demo.bo.announcment.AnnouncementOverviewItem;
import com.vavilon.demo.bo.search.AnnouncementListFilter;
import com.vavilon.demo.bo.search.ProductListFilter;
import com.vavilon.demo.bo.search.util.SearchResult;

public interface AnnouncementRepositoryExtension {
    SearchResult<AnnouncementOverviewItem> listAnnouncements(AnnouncementListFilter listFilter);
}
