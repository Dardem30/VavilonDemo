package com.vavilon.demo.bo.chat;

import com.vavilon.demo.bo.user.UserLight;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
@Getter
@Setter
public class Message {
    @Id
    @Column(name = "messageid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    @Column(name = "text")
    private String text;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    private UserLight user;
    @Column(name = "createtime")
    private Date createTime;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "conversationsid")
    private Conversation conversation;
}
