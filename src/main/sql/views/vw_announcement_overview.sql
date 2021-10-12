DROP VIEW IF EXISTS vw_announcement_overview;
CREATE VIEW vw_announcement_overview AS
SELECT a.announcementid
     , att.fileid                     AS image
     , p.name                         AS productName
     , (CASE WHEN length(text) > 200 THEN CONCAT(substring(text, 0, 200), '...') ELSE text END) AS text
     , a.moderationstatusid
     , a.price
     , a.currencysign
     , m.measurecode
     , m.name                         AS measure
     , a.userid
     , ms.name                        AS moderationstatusname
     , CONCAT(u.firstname, ' ', u.lastname) AS username
     , a.rating
FROM announcement a
         INNER JOIN product p on a.productid = p.productid
         INNER JOIN moderationstatus ms ON ms.moderationstatusid = a.moderationstatusid
         INNER JOIN measure m on a.measurecode = m.measurecode
         INNER JOIN "user" u ON u.userid = a.userid
         LEFT JOIN attachment att ON att.productid = p.productid AND att.main = 'true'