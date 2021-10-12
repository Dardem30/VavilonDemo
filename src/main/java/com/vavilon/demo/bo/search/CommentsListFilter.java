package com.vavilon.demo.bo.search;

import com.vavilon.demo.bo.search.util.ListFilter;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CommentsListFilter extends ListFilter {
    private Long announcementId;
}
