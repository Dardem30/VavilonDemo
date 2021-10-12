package com.vavilon.demo.controller;

import com.vavilon.demo.bo.announcment.*;
import com.vavilon.demo.bo.bean.AnnouncementRateForm;
import com.vavilon.demo.bo.bean.ModerationForm;
import com.vavilon.demo.bo.bean.ResponseForm;
import com.vavilon.demo.bo.client.UserClientType;
import com.vavilon.demo.bo.search.AnnouncementListFilter;
import com.vavilon.demo.bo.search.CommentsListFilter;
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
    @PostMapping(path = "/listAnnouncementsForUser")
    public void listAnnouncementsForUser(@RequestBody final AnnouncementListFilter listFilter, final HttpServletResponse response) {
        try {
            final ResponseForm<SearchResult<AnnouncementOverviewItem>> form = announcementService.listAnnouncementsForUser(listFilter);
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
    @GetMapping(path = "/gallery")
    public @ResponseBody List<String> gallery(@RequestParam final Long announcementId) {
        return announcementService.gallery(announcementId);
    }
    @PostMapping(path = "/updateModerationStatus")
    public void updateModerationStatus(@RequestBody final ModerationForm moderationForm) {
        announcementService.updateModerationStatus(moderationForm);
    }

    @GetMapping(path = "/getModerationStatuses")
    public @ResponseBody
    List<ModerationStatus> getModerationStatuses() {
        return readAll(ModerationStatus.class);
    }
    @PostMapping(path = "/rateAnnouncement")
    public @ResponseBody Double rateAnnouncement(@RequestBody final AnnouncementRateForm announcementRateForm) {
        return announcementService.rateAnnouncement(announcementRateForm);
    }
    @PostMapping(path = "/createComment")
    public @ResponseBody void createComment(@RequestBody final Comment comment) {
        mergeObject(comment);
    }
    @PostMapping(path = "/listComments")
    public void listComments(@RequestBody final CommentsListFilter listFilter, final HttpServletResponse response) {
        try {
            final ResponseForm<SearchResult<CommentOverviewItem>> form = announcementService.listComments(listFilter);
            writeResponseAsJSON(form, response, (content, result) -> {
                content.put("result", result.getResult());
                content.put("total", result.getTotalNumberFound());
            });
        } catch (final Exception e) {
            logger.error("Failed to search comments", e);
            writeResponseAsJSON(new ResponseForm<>("Failed to search comments", false), response, null);
        }
    }
}
