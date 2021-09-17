package com.vavilon.demo.bo.search;

import com.vavilon.demo.bo.announcment.Coordinate;
import com.vavilon.demo.bo.search.util.ListFilter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnnouncementListFilter extends ListFilter {
    private String content;
    private Long moderationStatusId;
    private String measureCode;
    private Double priceRangeStart;
    private Double priceRangeEnd;
    private String currencySign;
    private Long userId;
    private List<Coordinate> coordinates;
}
