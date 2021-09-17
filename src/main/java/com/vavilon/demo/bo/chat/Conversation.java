package com.vavilon.demo.bo.chat;

import com.vavilon.demo.bo.user.UserLight;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "conversations")
@Getter
@Setter
public class Conversation {
    @Id
    @Column(name = "conversationsid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conversationId;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "link_conversations_user", joinColumns = {@JoinColumn(name = "conversationsid")}, inverseJoinColumns = {@JoinColumn(name = "userid")})
    private Set<UserLight> users;

}
