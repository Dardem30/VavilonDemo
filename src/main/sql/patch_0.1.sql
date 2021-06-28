CREATE TABLE users
(
    userspk  SERIAL PRIMARY KEY,
    name     varchar(255) NULL,
    login    varchar(255) NULL,
    password varchar(255) NULL
);

ALTER TABLE users ADD COLUMN active boolean not null default '1';
ALTER TABLE users ADD COLUMN activationcode varchar(255) null;
ALTER TABLE users ADD COLUMN email varchar(255) null;