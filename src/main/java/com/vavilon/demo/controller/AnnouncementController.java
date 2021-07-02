package com.vavilon.demo.controller;

import com.vavilon.demo.bo.announcment.Announcement;
import com.vavilon.demo.bo.announcment.AnnouncementOverviewItem;
import com.vavilon.demo.bo.announcment.AnnouncementType;
import com.vavilon.demo.bo.announcment.Measure;
import com.vavilon.demo.bo.bean.ResponseForm;
import com.vavilon.demo.bo.client.UserClientType;
import com.vavilon.demo.bo.search.AnnouncementListFilter;
import com.vavilon.demo.bo.search.util.SearchResult;
import com.vavilon.demo.service.AnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/announcement")
@AllArgsConstructor
public class AnnouncementController extends CommonController {
    private final AnnouncementService announcementService;

    @PostMapping(path = "/save")
    public void save(@RequestBody final Announcement announcement, final HttpServletResponse response) {
        try {
            final ResponseForm<Announcement> form = announcementService.saveAnnouncement(announcement);
            writeResponseAsJSON(form, response, (content, savedAnnouncement) -> {
                content.put("announcementId", savedAnnouncement.getAnnouncementId());
                content.put("productId", savedAnnouncement.getProduct().getProductId());
                content.put("contactId", savedAnnouncement.getContact().getContactId());
            });
        } catch (final Exception e) {
            logger.error("Failed to save announcement", e);
            writeResponseAsJSON(new ResponseForm<>("Failed to save announcement", false), response, null);
        }
    }

    @GetMapping(path = "/getUserClientTypes")
    public @ResponseBody
    List<UserClientType> getUserClientTypes() {
        return readAll(UserClientType.class);
    }

    @GetMapping(path = "/getAnnouncementTypes")
    public @ResponseBody
    List<AnnouncementType> getAnnouncementTypes() {
        return readAll(AnnouncementType.class);
    }

    @GetMapping(path = "/getMeasures")
    public @ResponseBody
    List<Measure> getMeasures() {
        return readAll(Measure.class);
    }


    @PostMapping(path = "/listAnnouncements")
    public void listAnnouncements(@RequestBody final AnnouncementListFilter listFilter, final HttpServletResponse response) {
        try {
            final ResponseForm<SearchResult<AnnouncementOverviewItem>> form = announcementService.listAnnouncements(listFilter);
            writeResponseAsJSON(form, response, (content, result) -> {
                content.put("result", result.getResult());
                content.put("total", result.getTotalNumberFound());
            });
        } catch (final Exception e) {
            logger.error("Failed to search announcements", e);
            writeResponseAsJSON(new ResponseForm<>("Failed to search announcements", false), response, null);
        }
    }
    @GetMapping(path = "/read")
    public @ResponseBody Announcement read(@RequestParam final Long announcementId) {
        return announcementService.read(announcementId);
    }
}
