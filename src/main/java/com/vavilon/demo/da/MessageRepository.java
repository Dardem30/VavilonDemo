package com.vavilon.demo.da;

import com.vavilon.demo.bo.chat.Message;
import com.vavilon.demo.da.extension.MessageRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long>, MessageRepositoryExtension {
}
