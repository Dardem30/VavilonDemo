ALTER TABLE announcement
    ADD rating double precision null;
CREATE TABLE announcementratehistory
(
    announcementratehistoryid serial           NOT NULL,
    userid                    int              not null,
    announcementid            integer          NOT NULL,
    rate                      double precision not null,
    CONSTRAINT PK_announcementratehistory PRIMARY KEY ( announcementratehistoryid ),
    CONSTRAINT FK_announcementratehistory_userid FOREIGN KEY (userid) REFERENCES "user" (userid),
    CONSTRAINT FK_announcementratehistory_announcementid FOREIGN KEY (announcementid) REFERENCES announcement (announcementid)
);
CREATE INDEX ix_announcementratehistory_userid ON announcementratehistory (userid);
CREATE INDEX ix_announcementratehistory_announcementid ON announcementratehistory (announcementid);
CREATE TABLE comment
(
    commentid      serial    not null,
    userid         int       not null,
    rootcommentid  int       null,
    text           text      not null,
    announcementid int   NOT NULL,
    createtime     timestamp not null,
    CONSTRAINT PK_comment PRIMARY KEY ( commentid ),
    CONSTRAINT FK_comment_userid FOREIGN KEY (userid) REFERENCES "user" (userid),
    CONSTRAINT FK_comment_rootcommentid FOREIGN KEY (rootcommentid) REFERENCES comment (commentid),
    CONSTRAINT FK_comment_announcementid FOREIGN KEY (announcementid) REFERENCES announcement (announcementid)
);
CREATE INDEX ix_comment_userid ON comment (userid);
CREATE INDEX ix_comment_announcementid ON comment (announcementid);
CREATE INDEX ix_comment_rootcommentid ON comment (rootcommentid);
ALTER TABLE "user" ADD info text null;
ALTER TABLE "user" ADD photo varchar(50) null;
ALTER TABLE announcement ADD moderationtext text null;
ALTER TABLE announcement ADD readyforreview boolean not null default false;