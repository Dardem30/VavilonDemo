DROP VIEW IF EXISTS vw_product_overview;
CREATE VIEW vw_product_overview AS
SELECT p.productid
     , p.name
     , (CASE WHEN length(p.description) > 200 THEN CONCAT(substring(p.description, 0, 200), '...') ELSE p.description END) AS description
     , pc.name              AS category
     , pc.productcategoryid AS categoryid
     , a.fileid             AS image
     , p.userid
FROM product p
         INNER JOIN productcategory pc ON pc.productcategoryid = p.productcategoryid
         LEFT JOIN attachment a ON a.productid = p.productid AND a.main = 'true'