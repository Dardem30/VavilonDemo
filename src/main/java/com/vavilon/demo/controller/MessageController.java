package com.vavilon.demo.controller;

import com.vavilon.demo.bo.bean.ResponseForm;
import com.vavilon.demo.bo.chat.Conversation;
import com.vavilon.demo.bo.chat.Message;
import com.vavilon.demo.bo.search.MessageListFilter;
import com.vavilon.demo.bo.search.util.SearchResult;
import com.vavilon.demo.bo.user.UserLight;
import com.vavilon.demo.controller.util.RequestBodyWithConfidentialInfo;
import com.vavilon.demo.service.ConversationService;
import com.vavilon.demo.service.MessageService;
import com.vavilon.demo.service.security.User;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/message/")
public class MessageController extends CommonController{
    private final MessageService messageService;
    private final ConversationService conversationService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("getConversation")
    public @ResponseBody Conversation getConversation(@RequestBody final RequestBodyWithConfidentialInfo<Object> requestBody) {
        requestBody.getUserIds().add(User.get().getAppUser().getUserId());
        return conversationService.getConversation(requestBody.getUserIds());
    }
    @PostMapping(path = "getMessagesOfConversation")
    public void getMessagesOfConversation(@RequestBody final MessageListFilter listFilter, final HttpServletResponse response) {
        try {
            final ResponseForm<SearchResult<Message>> form = messageService.getMessagesOfConversation(listFilter);
            writeResponseAsJSON(form, response, (content, result) -> {
                content.put("result", result.getResult());
                content.put("total", result.getTotalNumberFound());
            });
        } catch (final Exception e) {
            logger.error("Failed to get messages for conversation: " + listFilter.getConversationId(), e);
            writeResponseAsJSON(new ResponseForm<>("Failed to get messages for conversation", false), response, null);
        }
    }
    @PostMapping("startConversation")
    public @ResponseBody Conversation startConversation(@RequestBody final RequestBodyWithConfidentialInfo<Message> requestBody) {
        requestBody.getUserIds().add(User.get().getAppUser().getUserId());
        final Conversation conversation = conversationService.startConversation(requestBody.getUserIds());
        final Message message = requestBody.getBody();
        message.setConversation(conversation);
        messageService.save(message);
        this.simpMessagingTemplate.convertAndSend("/socket-publisher/conversation/" + conversation.getConversationId(), message);
        return conversation;
    }
    @PostMapping("sendMessage")// TO BE PROTECTED: INTRODUCE CHECK THAT THE USER IS A PART OF CONVERSATION
    public @ResponseBody void sendMessage(@RequestBody final Message message) {
        messageService.save(message);
        message.setUser(readObject(UserLight.class, message.getUser().getUserId()));
        this.simpMessagingTemplate.convertAndSend("/socket-publisher/conversation/" + message.getConversation().getConversationId(), message);
    }
    @GetMapping("getUsersConversations")
    public @ResponseBody
    List<Conversation> getUsersConversations() {
        return conversationService.getUsersConversations();
    }
}
