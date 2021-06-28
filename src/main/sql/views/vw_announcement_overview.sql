DROP VIEW IF EXISTS vw_announcement_overview;
CREATE VIEW vw_announcement_overview AS
SELECT a.announcementid
     , (SELECT fileid FROM attachment att WHERE att.productid = a.productid AND main = 'true') AS image
     , p.name                                                                                  AS productName
     , a.text
FROM announcement a
         INNER JOIN product p on a.productid = p.productid