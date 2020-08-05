DROP ALL OBJECTS;

CREATE TABLE contacts (
    CONTACT_ID INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE applications (
    APPLICATION_ID INT AUTO_INCREMENT PRIMARY KEY,
    PRODUCT_NAME VARCHAR,
    DT_CREATED TIMESTAMP WITH TIME ZONE
);

CREATE TABLE contacts_applications (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    CONTACT_ID INT,
    APPLICATION_ID INT,
    FOREIGN KEY (CONTACT_ID) references contacts(CONTACT_ID),
    FOREIGN KEY (APPLICATION_ID) references applications(APPLICATION_ID)
);

---------------------------------------------------------------------
---------------------------------------------------------------------
---------------------------------------------------------------------

INSERT INTO contacts () VALUES (), (), ();
INSERT INTO applications (PRODUCT_NAME, DT_CREATED)
    VALUES ('iphone 6', '2020-08-03 11:00:00+03'),
            ('iphone 6s', '2020-08-01 12:00:00+03'),
            ('samsung galaxy s10', '2020-08-02 10:00:00+03');
INSERT INTO contacts_applications (CONTACT_ID, APPLICATION_ID)
    VALUES (1, 1), (1, 2), (2, 3);

--
--select contracts.contact_id, applications.PRODUCT_NAME, applications.DT_CREATED  from contracts
--left join contracts_applications on contracts.contact_id = contracts_applications.contact_id
--left join applications on contracts_applications.application_id = applications.application_id
--where contracts.contact_id = 1 order by dt_created desc limit 1;