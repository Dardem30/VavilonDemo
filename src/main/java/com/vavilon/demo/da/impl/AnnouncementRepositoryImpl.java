package com.vavilon.demo.da.impl;

import com.vavilon.demo.bo.announcment.AnnouncementOverviewItem;
import com.vavilon.demo.bo.announcment.AnnouncementOverviewItem_;
import com.vavilon.demo.bo.search.AnnouncementListFilter;
import com.vavilon.demo.bo.search.util.SearchResult;
import com.vavilon.demo.da.base.BaseRepository;
import com.vavilon.demo.da.extension.AnnouncementRepositoryExtension;
import org.apache.commons.lang.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementRepositoryImpl extends BaseRepository implements AnnouncementRepositoryExtension {

    @Override
    public SearchResult<AnnouncementOverviewItem> listAnnouncements(AnnouncementListFilter listFilter) {
        return resolvePredicates(AnnouncementOverviewItem.class, listFilter, (root, builder) -> {
            final List<Predicate> predicates = new ArrayList<>(10);
            if (StringUtils.isNotEmpty(listFilter.getContent())) {
                for (String keyword : listFilter.getContent().split(" ")) {
                    keyword = "%" + keyword.toLowerCase() + "%";
                    predicates.add(builder.or(
                            builder.like(builder.lower(root.get(AnnouncementOverviewItem_.productName)), keyword),
                            builder.like(builder.lower(root.get(AnnouncementOverviewItem_.text)), keyword)
                    ));
                }
            }
            return predicates;
        });
    }
}
