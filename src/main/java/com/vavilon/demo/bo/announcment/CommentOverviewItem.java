package com.vavilon.demo.bo.announcment;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "vw_comment_overview")
@Getter
@Setter
public class CommentOverviewItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentid")
    private Long commentId;
    @Column(name = "announcementid")
    private Long announcementId;
    @Column(name = "userid")
    private Long userId;
    @Column(name = "text")
    private String text;
    @Column(name = "username")
    private String username;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "rate")
    private Double rate;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "rootcommentid")
    private Set<CommentOverviewItem> childComments;
    @Column(name = "rootcommentid")
    private Long rootCommentId;

}
