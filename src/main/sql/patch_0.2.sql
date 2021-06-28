-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** userClientType

CREATE TABLE userclienttype
(
    userclienttypeid serial NOT NULL,
    name             varchar(30) NOT NULL,
    description      varchar(100) NULL,
    CONSTRAINT PK_userclienttype PRIMARY KEY ( userclienttypeid )
);








-- ************************************** "user"

CREATE TABLE "user"
(
    userid                serial       NOT NULL,
    firstname             varchar(255) NOT NULL,
    lastname              varchar(255) NOT NULL,
    login                 varchar(255) NOT NULL,
    password              varchar(255) NOT NULL,
    active                boolean      NOT NULL,
    activationcode        varchar(255) NULL,
    activationcodedateend date         NULL,
    email                 varchar(255) NOT NULL,
    CONSTRAINT PK_users PRIMARY KEY (userid)
);

CREATE INDEX ind_user_login ON "user"
    (
     login
        );

CREATE INDEX ind_user_firstname ON "user"
    (
     firstname
        );
CREATE INDEX ind_user_lastname ON "user"
    (
     lastname
        );








-- ************************************** productCategory

CREATE TABLE productcategory
(
    productcategoryid serial NOT NULL,
    name              varchar(30) NOT NULL,
    description       varchar(100) NULL,
    CONSTRAINT PK_productcategory PRIMARY KEY ( productcategoryid )
);








-- ************************************** orderStatus

CREATE TABLE orderstatus
(
    orderstatusid serial NOT NULL,
    name          varchar(30) NOT NULL,
    description   varchar(100) NULL,
    CONSTRAINT PK_orderstatus PRIMARY KEY ( orderstatusid )
);








-- ************************************** moderationStatus

CREATE TABLE moderationstatus
(
    moderationstatusid serial NOT NULL,
    name               varchar(30) NOT NULL,
    description        varchar(100) NULL,
    CONSTRAINT PK_moderationstatus PRIMARY KEY ( moderationstatusid )
);








-- ************************************** measure

CREATE TABLE measure
(
    measurecode varchar(50) NOT NULL,
    name        varchar(30) NOT NULL,
    description varchar(100) NULL,
    CONSTRAINT PK_productcategory_clone PRIMARY KEY ( measurecode )
);








-- ************************************** contactType

CREATE TABLE contacttype
(
    contacttypeid serial NOT NULL,
    name          varchar(30) NOT NULL,
    description   varchar(100) NULL,
    CONSTRAINT PK_contacttype PRIMARY KEY ( contacttypeid )
);








-- ************************************** clientType

CREATE TABLE clienttype
(
    clienttypeid serial NOT NULL,
    name         varchar(30) NOT NULL,
    description  varchar(100) NULL,
    CONSTRAINT PK_clienttype PRIMARY KEY ( clienttypeid )
);








-- ************************************** clientStatus

CREATE TABLE clientstatus
(
    clientstatusid serial NOT NULL,
    name           varchar(30) NOT NULL,
    description    varchar(100) NULL,
    CONSTRAINT PK_clientstatus PRIMARY KEY ( clientstatusid )
);








-- ************************************** announcementType

CREATE TABLE announcementtype
(
    announcementtypeid serial NOT NULL,
    name               varchar(30) NOT NULL,
    description        varchar(100) NULL,
    CONSTRAINT PK_announcementtype PRIMARY KEY ( announcementtypeid )
);








-- ************************************** product

CREATE TABLE product
(
    productid         serial NOT NULL,
    name              varchar(30) NOT NULL,
    description       varchar(100) NOT NULL,
    productcategoryid integer NOT NULL,
    image             varchar(100) NULL,
    CONSTRAINT PK_product PRIMARY KEY ( productid ),
    CONSTRAINT FK_110 FOREIGN KEY ( productcategoryid ) REFERENCES productCategory ( productcategoryid )
);

CREATE INDEX fkIdx_111 ON product
    (
     productcategoryid
        );








-- ************************************** "order"

CREATE TABLE "order"
(
    orderid       serial NOT NULL,
    orderdate     date NOT NULL,
    orderstatusid integer NOT NULL,
    CONSTRAINT PK_order PRIMARY KEY ( orderid ),
    CONSTRAINT FK_156 FOREIGN KEY ( orderstatusid ) REFERENCES orderStatus ( orderstatusid )
);

CREATE INDEX fkIdx_157 ON "order"
    (
     orderstatusid
        );

CREATE INDEX ind_order_date ON "order"
    (
     orderdate
        );








-- ************************************** contact

CREATE TABLE contact
(
    contactid     serial NOT NULL,
    name          varchar(100) NOT NULL,
    contacttypeid integer NOT NULL,
    value         varchar(50) NOT NULL,
    CONSTRAINT PK_contact PRIMARY KEY ( contactid ),
    CONSTRAINT FK_152 FOREIGN KEY ( contacttypeid ) REFERENCES contactType ( contacttypeid )
);

CREATE INDEX fkIdx_153 ON contact
    (
     contacttypeid
        );

CREATE INDEX ind_contact_value ON contact
    (
     value
        );








-- ************************************** client

CREATE TABLE client
(
    clientid       serial NOT NULL,
    name           varchar(255) NOT NULL,
    inn            varchar(50) NOT NULL,
    address        jsonb NOT NULL,
    clientdata     jsonb NOT NULL,
    clientstatusid integer NOT NULL,
    clienttypeid   integer NOT NULL,
    CONSTRAINT PK_person PRIMARY KEY ( clientid ),
    CONSTRAINT FK_49 FOREIGN KEY ( clientstatusid ) REFERENCES clientStatus ( clientstatusid ),
    CONSTRAINT FK_57 FOREIGN KEY ( clienttypeid ) REFERENCES clientType ( clienttypeid )
);

CREATE INDEX fkIdx_50 ON client
    (
     clientstatusid
        );

CREATE INDEX fkIdx_58 ON client
    (
     clienttypeid
        );

CREATE INDEX ind_client_inn ON client
    (
     inn
        );

CREATE INDEX ind_client_name ON client
    (
     name
        );








-- ************************************** userContact

CREATE TABLE usercontact
(
    userid    integer NOT NULL,
    contactid integer NOT NULL,
    CONSTRAINT FK_82 FOREIGN KEY ( userid ) REFERENCES "user" ( userid ),
    CONSTRAINT FK_85 FOREIGN KEY ( contactid ) REFERENCES contact ( contactid )
);

CREATE INDEX fkIdx_83 ON usercontact
    (
     userid
        );

CREATE INDEX fkIdx_86 ON usercontact
    (
     contactid
        );








-- ************************************** userClient

CREATE TABLE userclient
(
    userclientid     serial NOT NULL,
    clientid         integer NOT NULL,
    userid           integer NOT NULL,
    userclienttypeid integer NOT NULL,
    CONSTRAINT PK_userclientref PRIMARY KEY ( userclientid ),
    CONSTRAINT FK_28 FOREIGN KEY ( clientid ) REFERENCES client ( clientid ),
    CONSTRAINT FK_31 FOREIGN KEY ( userid ) REFERENCES "user" ( userid ),
    CONSTRAINT FK_65 FOREIGN KEY ( userclienttypeid ) REFERENCES userClientType ( userclienttypeid )
);

CREATE INDEX fkIdx_29 ON userclient
    (
     clientid
        );

CREATE INDEX fkIdx_32 ON userclient
    (
     userid
        );

CREATE INDEX fkIdx_66 ON userclient
    (
     userclienttypeid
        );








-- ************************************** announcement

CREATE TABLE announcement
(
    announcementId     serial NOT NULL,
    userClientId       integer NOT NULL,
    address            jsonb NOT NULL,
    contactId          integer NOT NULL,
    announcementTypeId integer NOT NULL,
    productId          integer NOT NULL,
    measureCode        varchar(50) NOT NULL,
    price              money NOT NULL,
    text               varchar(8000) NOT NULL,
    params             jsonb NOT NULL,
    announcementDate   date NOT NULL,
    moderationStatusId integer NOT NULL,
    orderId            integer NOT NULL,
    CONSTRAINT PK_announcement PRIMARY KEY ( announcementId ),
    CONSTRAINT FK_113 FOREIGN KEY ( productId ) REFERENCES product ( productId ),
    CONSTRAINT FK_118 FOREIGN KEY ( measureCode ) REFERENCES measure ( measureCode ),
    CONSTRAINT FK_132 FOREIGN KEY ( moderationStatusId ) REFERENCES moderationStatus ( moderationStatusId ),
    CONSTRAINT FK_139 FOREIGN KEY ( orderId ) REFERENCES "order" ( orderId ),
    CONSTRAINT FK_71 FOREIGN KEY ( userClientId ) REFERENCES userClient ( userClientId ),
    CONSTRAINT FK_89 FOREIGN KEY ( contactId ) REFERENCES contact ( contactId ),
    CONSTRAINT FK_97 FOREIGN KEY ( announcementTypeId ) REFERENCES announcementType ( announcementTypeId )
);

CREATE INDEX fkIdx_114 ON announcement
    (
     productId
        );

CREATE INDEX fkIdx_119 ON announcement
    (
     measureCode
        );

CREATE INDEX fkIdx_133 ON announcement
    (
     moderationStatusId
        );

CREATE INDEX fkIdx_140 ON announcement
    (
     orderId
        );

CREATE INDEX fkIdx_72 ON announcement
    (
     userClientId
        );

CREATE INDEX fkIdx_90 ON announcement
    (
     contactId
        );

CREATE INDEX fkIdx_98 ON announcement
    (
     announcementTypeId
        );

CREATE INDEX ind_announcement_date ON announcement
    (
     announcementDate
        );


-- ************************************** passwordResetToken
CREATE TABLE passwordresettoken
(
    passwordresettokenid serial       NOT NULL,
    token                varchar(60)  NOT NULL,
    url                  varchar(500) NOT NULL,
    expirydate           date         NOT NULL,
    userid               integer      NOT NULL,
    CONSTRAINT PK_passwordResetTokenId PRIMARY KEY (passwordresettokenid),
    CONSTRAINT FK_31 FOREIGN KEY (userid) REFERENCES "user" (userid)
);

CREATE INDEX fkIdx_userid ON passwordresettoken
    (
     userid
        );




