ALTER TABLE attachment DROP path;
ALTER TABLE attachment ADD COLUMN fileid varchar(50) not null;
ALTER TABLE contact ADD COLUMN userid integer NOT NULL;
CREATE INDEX fkIdx_contact_userid ON contact
    (
     userid
        );
ALTER TABLE public.announcement DROP COLUMN price;
ALTER TABLE public.announcement ADD COLUMN price decimal not null;
ALTER TABLE public.announcement ADD COLUMN currencysign varchar(30) not null;
alter table userclient alter column clientid drop not null;
alter table announcement alter column orderid drop not null;

ALTER TABLE product ADD COLUMN userid integer NULL;
CREATE INDEX ix_product_userid ON product
    (
     userid
        );

ALTER TABLE "user" ADD COLUMN role varchar(30) NOT NULL DEFAULT 'USER'