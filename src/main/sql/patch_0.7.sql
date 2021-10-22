alter table polygon
    drop constraint fk_announcementid;


ALTER TABLE contactannouncement
    ADD CONSTRAINT fk_contactid
        FOREIGN KEY (contactid)
            REFERENCES contact (contactid)
            ON DELETE CASCADE;
ALTER TABLE contactannouncement
    ADD CONSTRAINT fk_announcementid
        FOREIGN KEY (announcementid)
            REFERENCES announcement (announcementid)
            ON DELETE CASCADE;
ALTER TABLE comment
    ADD CONSTRAINT fk_announcementid
        FOREIGN KEY (announcementid)
            REFERENCES announcement (announcementid)
            ON DELETE CASCADE;
ALTER TABLE polygon
    ADD CONSTRAINT fk_announcementid
        FOREIGN KEY (announcementid)
            REFERENCES announcement (announcementid)
            ON DELETE CASCADE;
ALTER TABLE announcement
    ADD CONSTRAINT fk_productid
        FOREIGN KEY (productid)
            REFERENCES product (productid)
            ON DELETE CASCADE;

alter table contactannouncement
    drop constraint fk_contactannouncement_contactid;
alter table contactannouncement
    drop constraint fk_contactannouncement_announcementid;
alter table attachment
    drop constraint fk_productid;
alter table announcement
    drop constraint fk_113;

