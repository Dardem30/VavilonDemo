DROP VIEW IF EXISTS vw_profile_info;
CREATE VIEW vw_profile_info AS
SELECT u.userid
      , u.info
      , CONCAT(u.firstname, ' ', u.lastname) AS username
      , (SELECT COUNT(*) FROM announcement a WHERE a.userid = u.userid) AS countannouncements
      , (SELECT SUM(a.rating) / COUNT(*) FROM announcement a WHERE a.userid = u.userid) AS averagerate
      , (SELECT COUNT(*) FROM comment c WHERE c.userid = u.userid) AS countcomments
FROM "user" u