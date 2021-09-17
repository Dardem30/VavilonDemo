package com.vavilon.demo.da.impl;

import com.vavilon.demo.bo.chat.Message;
import com.vavilon.demo.bo.search.MessageListFilter;
import com.vavilon.demo.bo.search.util.SearchResult;
import com.vavilon.demo.da.extension.MessageRepositoryExtension;
import com.vavilon.demo.service.security.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;

public class MessageRepositoryImpl implements MessageRepositoryExtension {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SearchResult<Message> getMessagesOfConversation(final MessageListFilter listFilter) {
        final List result = entityManager.createNativeQuery("SELECT m.* FROM message m\n" +
                "INNER JOIN conversations c on m.conversationsid = c.conversationsid\n" +
                "WHERE c.conversationsid=:conversationId AND :userId IN (SELECT l.userid FROM link_conversations_user l WHERE c.conversationsid = l.conversationsid)\n" +
                "ORDER BY m.createtime DESC\n" +
                "LIMIT :limit\n" +
                "OFFSET :offset", Message.class)
                .setParameter("conversationId", listFilter.getConversationId())
                .setParameter("userId", User.get().getAppUser().getUserId())
                .setParameter("limit", listFilter.getLimit())
                .setParameter("offset", listFilter.getStart())
                .getResultList();
        final BigInteger total = (BigInteger) entityManager.createNativeQuery("SELECT COUNT(*) FROM message m\n" +
                "INNER JOIN conversations c on m.conversationsid = c.conversationsid\n" +
                "WHERE c.conversationsid=:conversationId AND :userId IN (SELECT l.userid FROM link_conversations_user l WHERE c.conversationsid = l.conversationsid)")
                .setParameter("conversationId", listFilter.getConversationId())
                .setParameter("userId", User.get().getAppUser().getUserId())
                .getSingleResult();
        final SearchResult<Message> searchResult = new SearchResult<>();
        searchResult.setResult(result);
        searchResult.setTotalNumberFound(total.intValue());
        return searchResult;
    }
}
