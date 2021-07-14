package com.vavilon.demo.bo.search;

import com.vavilon.demo.bo.search.util.ListFilter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnnouncementListFilter extends ListFilter {
    private String content;
    private Long moderationStatusId;
    private String measureCode;
    private Double priceRangeStart;
    private Double priceRangeEnd;
    private String currencySign;
}
