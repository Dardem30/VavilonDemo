package com.vavilon.demo.da.extension;

import com.vavilon.demo.bo.chat.Message;
import com.vavilon.demo.bo.search.MessageListFilter;
import com.vavilon.demo.bo.search.util.SearchResult;

public interface MessageRepositoryExtension {
    SearchResult<Message> getMessagesOfConversation(MessageListFilter listFilter);
}
