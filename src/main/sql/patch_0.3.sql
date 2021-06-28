CREATE TABLE attachment
(
    attachmentid serial       NOT NULL,
    path         varchar(500) NOT NULL,
    main         boolean      NOT NULL,
    productid    integer      NOT NULL,
    CONSTRAINT PK_attachmentref PRIMARY KEY (attachmentid),
    CONSTRAINT FK_productid FOREIGN KEY ( productid ) REFERENCES product ( productid )
);

CREATE INDEX fkIdx_productid ON attachment
    (
     productid
        );