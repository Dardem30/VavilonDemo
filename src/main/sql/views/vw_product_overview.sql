DROP VIEW IF EXISTS vw_product_overview;
CREATE VIEW vw_product_overview AS
SELECT p.productid
     , p.name
     , p.description
     , pc.name              AS category
     , pc.productcategoryid AS categoryid
FROM product p
         INNER JOIN productcategory pc ON pc.productcategoryid = p.productcategoryid