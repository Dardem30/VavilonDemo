package com.vavilon.demo.da.extension;

import com.vavilon.demo.bo.chat.Conversation;

import java.util.List;

public interface ConversationRepositoryExtension {
    Conversation getConversation(List<Long> userIds);
    List<Conversation> getUsersConversations(Long userId);
}
