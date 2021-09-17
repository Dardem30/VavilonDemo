package com.vavilon.demo.da.impl;

import com.vavilon.demo.bo.chat.Conversation;
import com.vavilon.demo.da.extension.ConversationRepositoryExtension;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ConversationRepositoryImpl implements ConversationRepositoryExtension {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Conversation getConversation(final List<Long> userIds) {
        try {
            return (Conversation) entityManager.createNativeQuery("SELECT c.* FROM conversations c WHERE (SELECT COUNT(*) FROM link_conversations_user l WHERE l.userid IN (:ids) AND l.conversationsid = c.conversationsid) = :length", Conversation.class)
                    .setParameter("ids", userIds)
                    .setParameter("length", userIds.size())
                    .getSingleResult();
        } catch (final NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Conversation> getUsersConversations(Long userId) {
        return entityManager.createNativeQuery("SELECT c.* FROM conversations c WHERE :userId IN (SELECT l.userid FROM link_conversations_user l WHERE c.conversationsid = l.conversationsid)", Conversation.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
