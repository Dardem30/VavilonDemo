package com.vavilon.demo.bo.announcment;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentid")
    private Long commentId;
    @Column(name = "userid")
    private Long userId;
    @Column(name = "announcementid")
    private Long announcementId;
    @Column(name = "text")
    private String text;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "rootcommentid")
    private Long rootCommentId;
}
