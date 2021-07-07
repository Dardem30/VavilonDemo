package com.vavilon.demo.controller;

import com.vavilon.demo.bo.bean.ResponseForm;
import com.vavilon.demo.bo.user.AppUser;
import com.vavilon.demo.bo.user.Contact;
import com.vavilon.demo.bo.user.ContactType;
import com.vavilon.demo.service.ContactService;
import com.vavilon.demo.service.security.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/contact")
@AllArgsConstructor
public class ContactController extends CommonController {
    private final ContactService contactService;

    @PostMapping(path = "/save")
    public void saveContact(@RequestBody final Contact contact, final HttpServletResponse response) {
        try {
            final ResponseForm<Contact> form = contactService.saveContact(contact);
            writeResponseAsJSON(form, response, (content, savedContact) -> {
                content.put("contactId", savedContact.getContactId());
            });
        } catch (final Exception e) {
            logger.error("Failed to save contact", e);
            writeResponseAsJSON(new ResponseForm<>("Failed to save contact", false), response, null);
        }
    }

    @GetMapping(path = "/getContactTypes")
    public @ResponseBody List<ContactType> getContactTypes() {
        return readAll(ContactType.class);
    }
    @GetMapping(path = "/getUserContacts")
    public @ResponseBody List<Contact> getUserContacts() {
        final AppUser user = User.getCurrentLoggedInUser();
        return user == null ? new ArrayList<>() : contactService.getUserContacts(user.getUserId());
    }
}