package com.vavilon.demo.da;

import com.vavilon.demo.bo.chat.Conversation;
import com.vavilon.demo.da.extension.ConversationRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long>, ConversationRepositoryExtension {
}
