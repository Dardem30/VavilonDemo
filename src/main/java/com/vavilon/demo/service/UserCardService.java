package com.vavilon.demo.service;

import com.vavilon.demo.bo.bean.ResponseForm;
import com.vavilon.demo.bo.userCard.UserCard;
import com.vavilon.demo.bo.userCard.UserCardLight;
import com.vavilon.demo.da.UserCardRepository;
import com.vavilon.demo.service.security.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserCardService {
    private final UserCardRepository userCardRepository;

    @Transactional(readOnly = true)
    public List<UserCardLight> list() {
        return userCardRepository.list(User.get().getAppUser().getUserId());
    }

    @Transactional
    public ResponseForm<UserCard> save(final UserCard userCard) {
        userCard.setUserId(User.get().getAppUser().getUserId());
        userCardRepository.save(userCard);
        return new ResponseForm<>("Card is successfully saved", true, userCard);
    }

    @Transactional(readOnly = true)
    public UserCard readUserCard(final Long userCardId) {
        return userCardRepository.findById(userCardId).get();
    }
}
