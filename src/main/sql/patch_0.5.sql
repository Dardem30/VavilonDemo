CREATE TABLE usercard
(
    usercardid  serial       NOT NULL,
    name        varchar(500) NOT NULL,
    actiontype  varchar(100) NOT NULL,
    usertype    varchar(100) NOT NULL,
    username    varchar(500) NULL,
    gender      varchar(100) NULL,
    birthdate   date         NULL,
    inn         varchar(100) NULL,
    description text         NULl,
    userid      integer      NOT NULL,
    CONSTRAINT PK_usercardref PRIMARY KEY (usercardid),
    CONSTRAINT FK_userid FOREIGN KEY (userid) REFERENCES "user" (userid)
);
CREATE INDEX ix_name ON product
    (
     name
        );
CREATE INDEX ix_text ON announcement
    (
     text
        );
CREATE INDEX ix_userid ON usercard
    (
     userid
        );
ALTER TABLE announcement ADD userid integer not null;
CREATE INDEX ix_announcement_userid ON announcement
    (
     userid
        );
CREATE TABLE contactannouncement
(
    contactid         integer NOT NULL,
    announcementid           integer NOT NULL,
    CONSTRAINT FK_contactannouncement_contactid FOREIGN KEY ( contactid ) REFERENCES contact ( contactid ),
    CONSTRAINT FK_contactannouncement_announcementid FOREIGN KEY ( announcementid ) REFERENCES announcement ( announcementid )
);

CREATE INDEX ix_contactannouncement_contactid ON contactannouncement
    (
     contactid
        );
CREATE INDEX ix_contactannouncement_announcementid ON contactannouncement
    (
     announcementid
        );

CREATE TABLE polygon
(
    polygonid serial       NOT NULL,
    announcementid integer      NOT NULL,
    CONSTRAINT PK_polygonref PRIMARY KEY (polygonid),
    CONSTRAINT FK_announcementid FOREIGN KEY (announcementid) REFERENCES "announcement" (announcementid)
);
CREATE INDEX ix_polygon_announcementid ON polygon
    (
     announcementid
        );
CREATE TABLE coordinates(
    coordinatesid serial not null,
    polygonid integer NOT NULL,
    lat decimal not null,
    lng decimal not null,
    CONSTRAINT PK_coordinatesref PRIMARY KEY (coordinatesid),
    CONSTRAINT FK_polygonid FOREIGN KEY (polygonid) REFERENCES "polygon" (polygonid) ON DELETE CASCADE
);

CREATE INDEX ix_coordinates_polygonid ON coordinates
    (
     polygonid
        );
alter table announcement alter column address drop not null;
alter table announcement alter column userclientid drop not null;
alter table announcement DROP column contactId;
alter table announcement alter column params drop not null;

CREATE TABLE conversations(
    conversationsid serial not null,
    CONSTRAINT PK_conversationsref PRIMARY KEY (conversationsid)
);
CREATE TABLE link_conversations_user(
                                        conversationsid         integer NOT NULL,
                                        userid           integer NOT NULL,
                                        CONSTRAINT FK_link_conversations_user_conversationsid FOREIGN KEY ( conversationsid ) REFERENCES conversations ( conversationsid ),
                                        CONSTRAINT FK_link_conversations_user_userid FOREIGN KEY ( userid ) REFERENCES "user" ( userid )
);
CREATE INDEX ix_link_conversations_user_conversationsid ON link_conversations_user
    (
     conversationsid
        );
CREATE INDEX ix_link_conversations_user_userid ON link_conversations_user
    (
     userid
        );
CREATE TABLE message(
    messageid serial not null,
    text varchar(500) not null,
    userid integer NOT NULL,
    conversationsid integer NOT NULL,
    createtime timestamp not null,
    CONSTRAINT PK_messageref PRIMARY KEY (messageid),
    CONSTRAINT FK_message_userid FOREIGN KEY ( userid ) REFERENCES "user" ( userid ),
    CONSTRAINT FK_message_conversationsid FOREIGN KEY ( conversationsid ) REFERENCES conversations ( conversationsid )
);
CREATE INDEX ix_message_userid ON message
    (
     userid
        );
CREATE INDEX ix_message_conversationsid ON message
    (
     conversationsid
        );