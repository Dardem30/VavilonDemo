package com.vavilon.demo.service;

import com.vavilon.demo.bo.bean.ResponseForm;
import com.vavilon.demo.bo.chat.Message;
import com.vavilon.demo.bo.search.MessageListFilter;
import com.vavilon.demo.bo.search.util.SearchResult;
import com.vavilon.demo.da.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    @Transactional
    public void save(final Message message) {
        messageRepository.save(message);
    }

    @Transactional(readOnly = true)
    public ResponseForm<SearchResult<Message>> getMessagesOfConversation(final MessageListFilter listFilter) {
        final SearchResult<Message> result = messageRepository.getMessagesOfConversation(listFilter);
        result.setResult(result.getResult().stream().sorted((message1, message2) -> message1.getCreateTime().getTime() > message2.getCreateTime().getTime() ? 1 : -1).collect(Collectors.toList()));
        return new ResponseForm<>("Success", true, result);
    }
}
