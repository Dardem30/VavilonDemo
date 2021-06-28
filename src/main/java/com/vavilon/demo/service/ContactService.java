package com.vavilon.demo.service;


import com.vavilon.demo.bo.bean.ResponseForm;
import com.vavilon.demo.bo.user.Contact;
import com.vavilon.demo.da.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    @Transactional
    public ResponseForm<Contact> saveContact(final Contact contact) {
        contactRepository.save(contact);
        return new ResponseForm<>("Contact is successfully saved", true, contact);
    }

    @Transactional(readOnly = true)
    public List<Contact> getUserContacts(final Long userId) {
        return contactRepository.findAllByUserId(userId);
    }
}
