package com.vavilon.demo.controller;


import com.vavilon.demo.bo.bean.ResponseForm;
import com.vavilon.demo.bo.userCard.UserCard;
import com.vavilon.demo.bo.userCard.UserCardLight;
import com.vavilon.demo.service.UserCardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/userCard/")
public class UserCardController extends CommonController {
    private final UserCardService userCardService;

    @GetMapping(value = "list")
    public @ResponseBody
    List<UserCardLight> list() {
        return userCardService.list();
    }

    @PostMapping(value = "save")
    public void save(@RequestBody final UserCard userCard, final HttpServletResponse response) {
        try {
            final ResponseForm<UserCard> form = userCardService.save(userCard);
            writeResponseAsJSON(form, response, (content, savedCard) -> content.put("userCardId", savedCard.getUserCardId()));
        } catch (final Exception e) {
            logger.error("Failed to save card", e);
            writeResponseAsJSON(new ResponseForm<>("Failed to save card", false), response, null);
        }
    }
    @GetMapping(value = "read")
    public @ResponseBody UserCard readUserCard(@RequestParam final Long userCardId) {
        return userCardService.readUserCard(userCardId);
    }
    @DeleteMapping(path = "/deleteUserCards")
    public void deleteAnnouncements(@RequestParam final List<Long> ids) {
        bulkDelete(UserCard.class, ids, "userCardId");
    }
}
