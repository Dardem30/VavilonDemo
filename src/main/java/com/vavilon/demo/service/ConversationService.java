package com.vavilon.demo.service;

import com.vavilon.demo.bo.chat.Conversation;
import com.vavilon.demo.bo.user.UserLight;
import com.vavilon.demo.da.ConversationRepository;
import com.vavilon.demo.service.security.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConversationService {
    private ConversationRepository conversationRepository;

    @Transactional
    public Conversation startConversation(final List<Long> userIds) {
        final Conversation conversation = new Conversation();
        conversation.setUsers(convertUserIdsToUserLightObjects(userIds));
        conversationRepository.save(conversation);
        return conversation;
    }

    @Transactional(readOnly = true)
    public Conversation getConversation(final List<Long> userIds) {
        return conversationRepository.getConversation(userIds);
    }

    private Set<UserLight> convertUserIdsToUserLightObjects(final List<Long> userIds) {
        return userIds.stream().map(id -> {
            final UserLight user = new UserLight();
            user.setUserId(id);
            return user;
        }).collect(Collectors.toSet());
    }

    @Transactional(readOnly = true)
    public List<Conversation> getUsersConversations() {
        return conversationRepository.getUsersConversations(User.get().getAppUser().getUserId());
    }
}
