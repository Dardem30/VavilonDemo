DROP VIEW IF EXISTS vw_comment_overview;
CREATE VIEW vw_comment_overview AS
SELECT c.commentid
     , c.text
     , c.createtime
     , CONCAT(u.firstname, ' ', u.lastname) AS username
     , c.announcementid
     , arh.rate
     , c.rootcommentid
FROM comment c
INNER JOIN "user" u on c.userid = u.userid
INNER JOIN announcement a on a.announcementid = c.announcementid
LEFT JOIN announcementratehistory arh ON arh.userid = u.userid AND arh.announcementid = a.announcementid