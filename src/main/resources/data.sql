INSERT INTO STATES (STATE_NAME)
VALUES ('UTTAR PRADESH'),
       ('KARNATAKA'),
       ('DELHI');

INSERT INTO CITIES (NAME, STATE_NAME)
VALUES ('GHAZIABAD', 'UTTAR PRADESH'),
       ('BANGALORE', 'KARNATAKA'),
       ('NEW DELHI', 'DELHI');

INSERT INTO USER_DETAILS
(
    USER_NAME,
    EMAIL_ID,
    PHONE_NUMBER,
    FIRST_NAME,
    LAST_NAME,
    ADDRESS,
    LONGITUDE,
    LATITUDE,
    DRIVING_LICENSE_NUMBER,
    AADHAR_NUMBER,
    DRIVING_LICENSE_IMG,
    AADHAR_IMAGE,
    USER_IMAGE
)
VALUES ('Admin User','admin@admin.com','123456789','admin','admin','test address','','','DLX12345','AA1234FG4',null,null,null),
       ('Customer','customer@admin.com','123456788','customer','customer','test address','','','DLX12344','AA1234FG6',null,null,null),
       ('Attendant','attendant@admin.com','123456787','attendant','attendant','test address','','','DLX12343','AA1234FG7',null,null,null);

INSERT INTO USERS
(
 EMAIL_ID,
 PASSWORD,
 ENABLED
 )
 VALUES ('customer@admin.com', 'customer', '1'),
        ('admin@admin.com', 'admin', '1');


INSERT INTO ROLES (NAME)
VALUES ('ADMIN'),
       ('CUSTOMER'),
       ('ATTENDANT');

INSERT INTO USERS_ROLES (USER_EMAIL, ROLE) VALUES ('admin@admin.com', 'ADMIN'),('customer@admin.com', 'CUSTOMER');